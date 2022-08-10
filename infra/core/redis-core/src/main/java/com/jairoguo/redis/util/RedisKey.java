package com.jairoguo.redis.util;

import com.jairoguo.exception.redis.RedisKeyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Optional;

/**
 * @author Jairo Guo
 */
@Component
public class RedisKey {
    private String projectName;
    @Value("${spring.application.name}")
    private String moduleName;

    public String getKey(String businessName ,String... fields) {
        fields = Optional.ofNullable(fields).orElseThrow(() -> {
            throw new RedisKeyException("fields不能为null");
        });
        if (fields.length == 0) throw new RedisKeyException("fields内容不能为空");

        int index = 0;
        StringBuilder param = new StringBuilder();
        for (String field : fields) {
            field = Optional.ofNullable(field).orElseThrow(() -> {
                throw new RedisKeyException("fields中的选项不能为null");
            });
            if (field.isEmpty()) throw new RedisKeyException("fields中的选项不能为空字符串");
            if (param.isEmpty()) param.append(field);
            else {
                if (index % 2 == 1) param.append(":").append(field);
                else param.append(".").append(field);
            }
            index++;
        }

        /*
            [{project_name}]:{modules}:{businessName}:{{business_uid}[:{uid_value}][.{{business_uid2}[:{uid_value2}]]
         */
        if (Optional.ofNullable(projectName).isPresent()) {
            return MessageFormat.format("{0}:{1}:{2}:{3}", projectName, moduleName, businessName, param).toUpperCase();
        } else {
            return MessageFormat.format("{0}:{1}:{2}", moduleName, businessName, param).toUpperCase();
        }
    }

    public void setModule(String moduleName) {
        if (Optional.ofNullable(moduleName).isPresent()) {
            this.moduleName = moduleName;
        }
    }

    public void setAttributes(String projectName) {
        if (Optional.ofNullable(projectName).isPresent()) {
            this.projectName = projectName;
        }
    }
    public void setAttributes(String projectName, String moduleName) {
        this.setAttributes(projectName);
        if (Optional.ofNullable(moduleName).isPresent()) {
            this.moduleName = moduleName;
        }
    }
}
