package com.jairoguo.common;

import com.jairoguo.common.result.ResultBody;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResultBodyTest {
    @Test
    void testDataNull() {
        ResultBody<String> build = ResultBody.<String>builder()
                .code("200")
                .msg("OK")
                .success(true)
                .data(null)
                .info(null)
                .build();

        assertInstanceOf(ResultBody.class, build);
        assertNull(build.getData());
        System.out.println(build);
    }

    @Test
    void testData() {
        String s = "";
        ResultBody<String> build = ResultBody.<String>builder()
                .code("200")
                .msg("OK")
                .success(true)
                .data(s)
                .info(null)
                .build();

        assertInstanceOf(ResultBody.class, build);
        assertEquals(build.getData(), s);


        System.out.println(build);
    }
}
