package com.jairoguo.storage.infra.util;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author Jairo Guo
 */
@Component
public class AvatarUtil {



    public static String generateAvatar(Long id) throws IOException, ScriptException, NoSuchMethodException {
        ClassPathResource classPathResource = new ClassPathResource("multiavatar.js");
        FileReader fileReader = new FileReader(classPathResource.getFile());
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");

        engine.eval(fileReader);
        Invocable inv = (Invocable) engine;
        return (String) inv.invokeFunction("multiavatar", id.toString());
    }

    public static InputStream toPng(String svgCode) throws IOException, TranscoderException {
        PNGTranscoder pngTranscoder = new PNGTranscoder();
        TranscoderInput transcoderInput = new TranscoderInput(
                new ByteArrayInputStream(svgCode.getBytes(StandardCharsets.UTF_8)));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();


        TranscoderOutput transcoderOutput = new TranscoderOutput(outputStream);

        pngTranscoder.transcode(transcoderInput, transcoderOutput);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

        outputStream.flush();
        outputStream.close();

        return inputStream;

    }

    private AvatarUtil() {

    }
}
