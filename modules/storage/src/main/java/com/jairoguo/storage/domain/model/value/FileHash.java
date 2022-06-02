package com.jairoguo.storage.domain.model.value;

import com.jairoguo.common.base.ValueObject;
import lombok.Getter;

import java.util.Objects;

/**
 * @author Jairo Guo
 */

@Getter
public class FileHash implements ValueObject {

    private String hashValue;


    private FileHash() {
    }

    public static FileHash create() {
        return new FileHash();
    }

    public void bindHash(String hash) {
        if (this.hashValue == null || !this.comparisonHash(hash)) {
            this.hashValue = hash;
        }
    }

    public Boolean comparisonHash(String hash) {
        return Objects.equals(this.hashValue, hash);
    }

}
