package com.jairoguo.storage.interfaces.rest;

import com.jairoguo.common.result.Result;
import com.jairoguo.common.result.ResultBody;
import com.jairoguo.storage.application.service.AvatarApplicationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author Jairo Guo
 */
@RestController
@RequestMapping("avatar")
public class AvatarController {
    @Resource
    private AvatarApplicationService avatarApplicationService;

    @PostMapping("upload")
    public ResultBody<Object> uploadAvatar(@RequestParam String userId, @RequestParam MultipartFile file) throws IOException {
        avatarApplicationService.upLoadAvatar(Long.parseLong(userId), file.getInputStream());
        return Result.success();
    }
}
