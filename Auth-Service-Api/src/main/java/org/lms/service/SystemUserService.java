package org.lms.service;

import org.lms.dto.request.SystemUserRequestDto;

import java.io.IOException;

public interface SystemUserService {
    public void createUser(SystemUserRequestDto userRequestDto) throws IOException;
}
