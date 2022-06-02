package com.jairoguo.storage.infra.config;

import com.obs.services.ObsClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author Jairo Guo
 */

@ConfigurationProperties("storage.huawei.obs")
public class ObsConfig {

    private String endPoint;
    private String ak;
    private String sk;

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }

    public String getSk() {
        return sk;
    }

    public void setSk(String sk) {
        this.sk = sk;
    }

    @Bean
    public ObsClient getObsClient(){
        return new ObsClient(ak, sk, endPoint);
    }

}
