package com.jairoguo.storage.application.event.subscribe;

import com.jairoguo.storage.application.service.AvatarApplicationService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author Jairo Guo
 */
@Service
public class AvatarSubscribe {

    @Resource
    private AvatarApplicationService avatarApplicationService;

    @Bean
    Consumer<Map<String, Object>> initAvatar() {

        return avatarIntiEventDTO -> {
            try {
                avatarApplicationService.upLoadAvatar((Long) avatarIntiEventDTO.get("userId"), null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

    }


}
