package com.stupid.dev.service.impl;

import com.stupid.common.mq.producer.MsgProducer;
import com.stupid.dev.dao.repository.DoorRepository;
import com.stupid.dev.entity.door.po.DoorInfo;
import com.stupid.common.api.dev.vo.DoorVO;
import com.stupid.dev.feign.FeignReposity;
import com.stupid.dev.infrastructure.toolkit.IBeanConvert;
import com.stupid.dev.service.IDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Tags;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 门禁机服务
 */
@Service
@Slf4j
public class DeviceServiceImpl implements IDeviceService {

    @Autowired
    private DoorRepository doorRepository;

    @Autowired
    private IBeanConvert beanConvert;

    @Autowired
    private FeignReposity feignReposity;

    @Autowired
    private ThreadService threadService;

    /**
     * 添加门禁机
     */
    @Override
    @Trace
    @Tags({
            @Tag(key = "saveDoorParam",value = "arg[0]"),
            @Tag(key = "saveDoorReturn",value = "returnedObj")
    })
    public String saveDoor(DoorVO doorVO) {
        DoorInfo doorInfo = beanConvert.doorVO2PO(doorVO);
        int row = doorRepository.saveDoor(doorInfo);
        return row==1?"success":"fail";
    }

    /**
     * 查询门禁详情
     */
    @Override
    public DoorVO queryDoorInfo(Long id) {

        //推送mq消息
        MsgProducer.sendMsgSync("Topic001","Tag0610","AO202206101234","hello");
        threadService.getStrAsync();
        System.out.println(Thread.currentThread().getName());
        DoorInfo doorInfo = doorRepository.queryDoorInfo(id);
        DoorVO doorVO = beanConvert.doorPO2VO(doorInfo);
        //查询用户信息
//        UserVO userVO = feignReposity.queryUserInfoFromUserServer(doorInfo.getCreateUser());
//        doorVO.setCreateUser(userVO.getName());
        return doorVO;
    }

    /**
     * 查看设备号
     */
    @Override
    public String queryDevCode(Long id) {
        String devCode = doorRepository.queryDevCode(id);
        return devCode;
    }
}
