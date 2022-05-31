-- 领取令牌
-- 获得参数key
local key = 'bucketKey'..KEYS[1]
-- 获取当前需要领取的令牌数
local getTokens = tonumber(ARGV[1])
-- 获取令牌桶中现有的令牌数
local hasTokens = tonumber(redis.call('hget',key,'hasTokens'))
-- 获取最大令牌数
local maxTokens = tonumber(redis.call('hget',key,'maxTokens'))
-- 获取每秒生成令牌的数量
local secTokens = tonumber(redis.call('hget',key,'secTokens'))
-- 获取下次生成令牌的时间（微秒）
local nextTime = tonumber(redis.call('hget',key,'nextTime'))
-- 获取当前时间（微秒）
local nowArray = redis.call('time')
local nowTime = nowArray[1] * 1000000 + nowArray[2]
-- 计算单个令牌生成的耗时
local singleTokenTime = 1000000 / secTokens
-- 获取超时时间
local timeout = tonumber(ARGV[2] or -1)
-- 判断超时时间
if timeout ~= -1 then
    -- 有超时时间
    if timeout < nextTime - nowTime then
        -- 需要等待的时间大于超时时间
        return -1
    end
end

-- 重新计算令牌桶
if nowTime > nextTime then
    -- 计算上一次生成令牌的时间到现在的时差
    local hasTime = nowTime - nextTime
    -- 计算可以生成的令牌数
    local createTokens = math.modf(hasTime / singleTokenTime)
    -- 计算当前总令牌数
    hasTokens = math.min(hasTokens + createTokens,maxTokens)
    -- 重新设置下一次生成令牌的时间
    nextTime = nowTime
end

-- 获取令牌
-- 计算当前能拿走的令牌数
local canGetTokens = math.min(hasTokens,getTokens)
-- 计算需要预支的令牌数
local adviceTokens = getTokens - canGetTokens
-- 计算预支令牌所需要的时间（微妙值）
local adviceTime = adviceTokens * singleTokenTime
-- 计算需要等待的时间
local waitTime = adviceTime
-- 重新设置令牌桶中的值
hasTokens = hasTokens - canGetTokens
-- 更新令牌桶
redis.call('hmset',key,'hasTokens',hasTokens,'nextTime',nextTime + adviceTime)

-- 返回当前请求需要等待的时间
return waitTime