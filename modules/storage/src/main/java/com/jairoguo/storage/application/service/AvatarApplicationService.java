package com.jairoguo.storage.application.service;

import com.jairoguo.storage.application.event.publish.AvatarPublish;
import com.jairoguo.storage.domain.service.AvatarDomainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 * @author Jairo Guo
 */
@Service
public class AvatarApplicationService {

    @Resource
    private AvatarDomainService avatarDomainService;
    @Resource
    private AvatarPublish avatarPublish;

    public void upLoadAvatar(Long userId, InputStream inputStream) throws IOException {
        String key;
        if (Optional.ofNullable(inputStream).isPresent()) {
            avatarDomainService.upLoadAvatar(userId, inputStream);
        } else {
            key = avatarDomainService.upLoadAvatar(userId);
            avatarPublish.backToAvatar(userId, key);
        }

    }
}
