package com.jairoguo.storage.application.event.publish;

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

    public void backToAvatar(Long userId, String key) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("key", key);
        streamBridge.send("backToAvatar-out-0", map);
    }
}

