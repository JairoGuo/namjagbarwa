package com.jairoguo.storage.domain.service.impl;

import com.jairoguo.common.result.Result;
import com.jairoguo.storage.domain.model.aggregate.Avatar;
import com.jairoguo.storage.domain.repository.AvatarRepository;
import com.jairoguo.storage.domain.service.AvatarDomainService;
import com.jairoguo.storage.infra.util.AvatarUtil;
import com.jairoguo.storage.infra.util.ObsUtil;
import org.apache.batik.transcoder.TranscoderException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.script.ScriptException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 * @author Jairo Guo
 */
@Service
public class AvatarDomainServiceImpl implements AvatarDomainService {

    @Resource
    private ObsUtil obsUtil;

    @Resource
    private AvatarRepository avatarRepository;

    @Override
    public String upLoadAvatar(Long userId) {
        String svg;
        String key = null;
        InputStream inputStream = null;
        try {
            svg = AvatarUtil.generateAvatar(userId);
            inputStream = AvatarUtil.toPng(svg);
            key = obsUtil.uploadFile(inputStream, userId.toString());
        } catch (IOException | ScriptException | NoSuchMethodException | TranscoderException e) {
            e.printStackTrace();
        }

        Avatar avatar = Avatar.create();
        avatar.bindFile(inputStream);
        avatar.bindUserId(userId);
        avatarRepository.save(avatar);
        return key;

    }

    @Override
    public String upLoadAvatar(Long userId, InputStream inputStream) throws IOException {
        Avatar avatar =Avatar.create();
        inputStream = new ByteArrayInputStream(inputStream.readAllBytes());
        inputStream.mark(0);
        avatar.bindFile(inputStream);
        avatar.bindUserId(userId);
        inputStream.reset();
        Avatar avatarMap = avatarRepository.findById(avatar.getUserId());
        if (Optional.ofNullable(avatarMap).isPresent()) {
            Boolean isConsistent = avatar.getHash().comparisonHash(avatarMap.getHash().getHashValue());
            if (Boolean.FALSE.equals(isConsistent)) {
                obsUtil.uploadFile(inputStream, avatar.getObjectKey());
                avatarRepository.update(avatar);
            }
        } else {
            Result.fail("用户不存在");
        }

        return avatarMap.getObjectKey();

    }
}
