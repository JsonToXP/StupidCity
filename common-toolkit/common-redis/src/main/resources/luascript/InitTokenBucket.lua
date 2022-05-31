-- 初始化令牌桶
-- 获得参数key
local key = 'bucketKey'..KEYS[1]
-- 令牌桶的最大容量
local maxTokens = tonumber(ARGV[1])
-- 每秒生成的令牌数量
local secTokens = tonumber(ARGV[2])
-- 获取当前时间（微秒）
local nowArray = redis.call('time')
local nowTime = nowArray[1] * 1000000 + nowArray[2]
-- 判断令牌桶是否存在
local isExist = redis.call('exists',key)
if isExist == 0 then
    -- 不存在，初始化
    redis.call('hmset',key,'hasTokens',maxTokens,'maxTokens',maxTokens,'secTokens',secTokens,'nextTime',nowTime)
end
