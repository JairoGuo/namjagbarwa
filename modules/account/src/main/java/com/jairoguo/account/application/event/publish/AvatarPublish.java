package com.jairoguo.account.application.event.publish;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author Jairo Guo
 */
@Service
public class AvatarPublish {

    @Resource
    private StreamBridge streamBridge;

    public void initAvatar(Long userId) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        streamBridge.send("initAvatar-out-0", map);
    }
}
