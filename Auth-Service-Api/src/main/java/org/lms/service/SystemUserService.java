package org.lms.service;

import org.lms.dto.request.SystemUserRequestDto;

public interface SystemUserService {
    public void createUser(SystemUserRequestDto userRequestDto);
}
