package com.jairoguo.handler.decoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jairoguo.common.result.ResultBody;
import com.jairoguo.exception.result.ResultException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author Jairo Guo
 */

@Component
public class OpenFeignErrorDecoder implements ErrorDecoder {
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Exception decode(String s, Response response) {

        try {
            String body = Util.toString(response.body().asReader(Charset.defaultCharset()));
            ResultBody<?> resultBody = objectMapper.readValue(body, ResultBody.class);
            if (Boolean.FALSE.equals(resultBody.getSuccess())) {
                return new ResultException(resultBody.getCode(), resultBody.getMsg(), resultBody.getInfo());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Exception("Feign client 调用异常");
    }
}
