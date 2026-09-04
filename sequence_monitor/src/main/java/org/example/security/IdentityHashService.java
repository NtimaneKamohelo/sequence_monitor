package org.example.security;

import org.springframework.beans.factory.annotation.Value;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class IdentityHashService {

    private static final String HMAC_ALGORITHM = "HmacSHA256";

    private final String secret;

    public IdentityHashService(
            @Value("${identity.hash.secret}")
            String secret
    ){
        if (secret == null || secret.length() < 32) {

            throw new IllegalArgumentException(
                    "Identity hash secret must be at least 32 characters"
            );

        }
        this.secret = secret;
    }

    public String hash(String idNumber) {

        try {
            Mac mac = Mac.getInstance(HMAC_ALGORITHM);

            SecretKeySpec key = new SecretKeySpec(
                    secret.getBytes(
                            StandardCharsets.UTF_8
                    ),
                    HMAC_ALGORITHM
            );

            mac.init(key);

            byte[] result = mac.doFinal(
                    idNumber.getBytes(
                            StandardCharsets.UTF_8
                    )
            );

            return toHex(result);

        } catch (Exception exception) {
            throw new IllegalStateException(
                    "Unable to generate identity hash",
                    exception
            );
        }

    }

    public String toHex(byte[] bytes) {

        StringBuilder result = new StringBuilder();

        for (byte value : bytes) {
            result.append(
                    String.format(
                            "%02x",
                            value
                    )
            );
        }

        return result.toString();

    }
}
