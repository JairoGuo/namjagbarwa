package com.jairoguo.account.application.event.subscribe;


import com.jairoguo.account.domain.model.entity.UserInfo;
import com.jairoguo.account.domain.model.entity.id.UserId;
import com.jairoguo.account.domain.service.AccountDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author Jairo Guo
 */
@Service
public class AvatarSubscribe {

    @Resource
    private AccountDomainService accountDomainService;

    @Bean
    Consumer<Map<String, Object>> backToAvatar() {

        return avatarResultEventDTO -> {
            UserInfo userInfo = UserInfo.create();
            UserId userId = UserId.create();
            userId.setId((Long) avatarResultEventDTO.get("userId"));
            userInfo.setUserId(userId);
            userInfo.bindAvatarUrl((String) avatarResultEventDTO.get("key"));
            accountDomainService.updateUserInfo(userInfo);

        };

    }


}
