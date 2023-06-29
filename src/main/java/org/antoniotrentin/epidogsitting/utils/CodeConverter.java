package org.antoniotrentin.epidogsitting.utils;

import jakarta.persistence.AttributeConverter;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

//@Component
public class CodeConverter implements AttributeConverter<String, String> {
    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";
    private static String secret;

    @Value("${spring.application.bcrypt.secret}")
    public void setSecret(String cryptKey) {
        secret = cryptKey;
    }

    @Override
    public String convertToDatabaseColumn(String str) {

        try {
            Key key = new SecretKeySpec(secret.getBytes(), "AES");
            Cipher c = Cipher.getInstance(ALGORITHM);

            c.init(Cipher.ENCRYPT_MODE, key);

            return Base64.getEncoder().encodeToString(c.doFinal(str.getBytes()));

        } catch (RuntimeException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                 | IllegalBlockSizeException | BadPaddingException e) {
            System.out.println(e);
            throw new RuntimeException();
        }

    }

    @Override
    public String convertToEntityAttribute(String encryptedStr) {
        Key key = new SecretKeySpec(secret.getBytes(), "AES");
        try {
            Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(Cipher.DECRYPT_MODE, key);

            return new String(c.doFinal(Base64.getDecoder().decode(encryptedStr)));

        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException();
        }

    }


}
