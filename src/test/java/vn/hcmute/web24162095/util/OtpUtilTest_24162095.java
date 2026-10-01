package vn.hcmute.web24162095.util;

import org.junit.jupiter.api.Test;
import vn.hcmute.web24162095.model.PendingRegistration_24162095;
import vn.hcmute.web24162095.model.User_24162095;
import static org.junit.jupiter.api.Assertions.*;

public class OtpUtilTest_24162095 {
    @Test void otpHasSixDigits(){String otp=OtpUtil_24162095.generate();assertTrue(otp.matches("\\d{6}"));}
    @Test void pendingRegistrationChecksValueAndExpiry(){User_24162095 user=new User_24162095();PendingRegistration_24162095 valid=new PendingRegistration_24162095(user,"123456",System.currentTimeMillis()+10_000);assertTrue(valid.verify("123456"));assertFalse(valid.verify("654321"));PendingRegistration_24162095 expired=new PendingRegistration_24162095(user,"123456",System.currentTimeMillis()-1);assertTrue(expired.isExpired());assertFalse(expired.verify("123456"));}
}
