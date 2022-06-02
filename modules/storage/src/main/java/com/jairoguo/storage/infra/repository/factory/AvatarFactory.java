package com.jairoguo.storage.infra.repository.factory;

import com.jairoguo.storage.domain.model.aggregate.Avatar;
import com.jairoguo.storage.domain.model.value.FileHash;
import com.jairoguo.storage.infra.repository.po.AvatarPO;

/**
 * @author Jairo Guo
 */
public class AvatarFactory {

    private AvatarFactory() {
    }

    public static Avatar toAvatar(AvatarPO avatarPO) {
        Avatar avatar = Avatar.create();
        avatar.bindUserId(avatarPO.getUserId());

        FileHash fileHash = FileHash.create();
        fileHash.bindHash(avatarPO.getHash());
        avatar.bindHash(fileHash);

        return avatar;
    }
}
