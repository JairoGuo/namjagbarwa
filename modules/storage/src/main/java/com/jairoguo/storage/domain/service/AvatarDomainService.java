package com.jairoguo.storage.domain.service;

import com.jairoguo.common.base.DomainService;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Jairo Guo
 */
public interface AvatarDomainService extends DomainService {

    String upLoadAvatar(Long userId);

    String upLoadAvatar(Long userId, InputStream inputStream) throws IOException;
}
