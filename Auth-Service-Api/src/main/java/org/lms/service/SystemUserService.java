package org.lms.service;

import org.lms.dto.request.SystemUserRequestDto;

import java.io.IOException;
import java.util.List;

public interface SystemUserService {
    public void createUser(SystemUserRequestDto userRequestDto) throws IOException;
    public void initializeHosts(List<SystemUserRequestDto> users) throws IOException;
    public void resend(String email, String type);
    public void forgotPasswordSendVerificationCode(String email);
    public boolean verifyReset(String otp, String email);
}
