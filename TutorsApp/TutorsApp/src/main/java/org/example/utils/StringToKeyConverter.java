package org.example.utils;

import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@UtilityClass
@Slf4j
public class StringToKeyConverter {

    public static SecretKey convertStringToSecretKey(String encodedKey) {
        try {
            return Keys.hmacShaKeyFor(encodedKey.getBytes(StandardCharsets.UTF_8));
        }
        catch (InvalidKeyException ex){
            log.error("invalid signing key", ex);
            throw ex;
        }
    }
}
