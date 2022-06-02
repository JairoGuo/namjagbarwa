package com.jairoguo.storage.domain.model.aggregate;

import cn.hutool.crypto.digest.DigestUtil;
import com.jairoguo.common.base.AggregateRoot;
import com.jairoguo.storage.domain.model.entity.id.UserId;
import com.jairoguo.storage.domain.model.value.FileHash;
import lombok.Getter;

import java.io.InputStream;

/**
 * @author Jairo Guo
 */
@Getter
public class Avatar implements AggregateRoot<UserId> {

    private UserId userId;
    private FileHash hash;
    private String objectKey;

    private Avatar() {
    }

    public static Avatar create() {
        return new Avatar();
    }

    public void bindFile(InputStream inputStream) {
        FileHash fileHash = null;

        if (inputStream != null) {
            if (this.hash == null) {
                fileHash = FileHash.create();
            }
            assert fileHash != null;
            fileHash.bindHash(DigestUtil.md5Hex(inputStream));
            this.hash = fileHash;

        }
    }

    public void bindHash(FileHash hash) {
        this.hash = hash;
    }

    public void bindUserId(Long userId) {
        UserId userId1 = UserId.create();
        userId1.setId(userId);
        this.userId = userId1;
        this.objectKey = userId.toString();
    }

}
