package com.jairoguo.common;

import com.jairoguo.common.result.Result;
import com.jairoguo.common.result.ResultBody;
import com.jairoguo.exception.result.ResultException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResultTest {
    @Test
    void success() {
        ResultBody<Object> success = Result.success();
        assertInstanceOf(ResultBody.class, success);
        System.out.println(success);

        String s = "";
        ResultBody<String> success1 = Result.success(s);
        assertEquals(success1.getData(), s);
        System.out.println(success1);
    }

    @Test
    void fail() {
        // Result.fail("ERR");

        assertThrows(ResultException.class, () -> Result.fail("ERR", "Error"));
    }
}
