package org.lms.service.Impl;

import lombok.RequiredArgsConstructor;
import org.lms.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Value("${fromEmail}")
    private String senderEmail;

    @Value("${emailKey}")
    private String apiKey;

    @Override
    public boolean sendUserSignupVerificationCode(String toEmail, String subject, String otp, String firstName) throws IOException {

    }
}
