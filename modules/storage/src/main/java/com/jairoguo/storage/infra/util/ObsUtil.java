package com.jairoguo.storage.infra.util;

import com.obs.services.ObsClient;
import com.obs.services.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;

/**
 * @author Jairo Guo
 */
@Slf4j
@Component
public class ObsUtil {

    @Resource
    private ObsClient obsClient;

    @Value("${storage.huawei.obs.bucketName}")
    private String bucketName;

    @Value("${storage.suffix}")
    private String suffix;

    public String uploadFile(InputStream inputStream, String objectKey) throws IOException {

        String key = MessageFormat.format("{0}.{1}", objectKey, suffix);
        PutObjectResult putObjectResult = obsClient.putObject(bucketName, key, inputStream);
        if (putObjectResult.getStatusCode() != 200) {
            log.error("upload error");
        }
        inputStream.close();
        return key;
    }

    public void deleteFile(String objectKey) throws Exception {
        obsClient.deleteObject(bucketName, objectKey);
        obsClient.close();
    }
}
