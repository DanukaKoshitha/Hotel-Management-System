package org.lms.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OtpGenarater {
    public String generateOtp(int length){
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();

        for (int i=0; i<length; i++){
            stringBuffer.append(random.nextInt(10));
        }

        //01234
        while (stringBuffer.charAt(0) == 0){
            stringBuffer.setCharAt(0,(char) ('1'+random.nextInt(10)));
        }
        return stringBuffer.toString();
    }
}
