package org.lms.service;

import java.io.IOException;

public interface EmailService {
    public boolean sendUserSignupVerificationCode(String toEmail, String subject, String otp, String firstName) throws IOException;
    public boolean sendHostPassword(String toEmail, String subject, String password, String firstName) throws IOException;
}
