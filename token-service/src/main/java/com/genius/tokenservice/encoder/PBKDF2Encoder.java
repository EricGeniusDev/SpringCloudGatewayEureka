package com.genius.tokenservice.encoder;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.genius.tokenservice.property.PBKDF2Property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PBKDF2Encoder implements PasswordEncoder {

    @Autowired
    PBKDF2Property property;

    @Override
    public String encode(CharSequence rawPassword) {
        try {
            byte[] result = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
                    .generateSecret(
                            new PBEKeySpec(rawPassword.toString().toCharArray(),
                                    property.getSecret().getBytes(),
                                    property.getIterations(),
                                    property.getKeylength()))
                    .getEncoded();
            var retorno = Base64.getEncoder().encodeToString(result);
            return retorno;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).matches(encodedPassword);
    }

}
