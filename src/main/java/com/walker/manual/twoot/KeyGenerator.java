package com.walker.manual.twoot;

import org.bouncycastle.crypto.generators.SCrypt;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

/**
 * <p>
 *
 * </p>
 *
 * @author mu qin
 * @date 2021/2/5
 */
public class KeyGenerator {

    private static final int SCRYPT_COST = 16384;
    private static final int SCRYPT_BLOCK_SIZE = 8;
    private static final int SCRYPT_PARALLELISM = 1;
    private static final int KEY_LENGTH = 20;

    private static final int SALT_LENGTH = 16;

    private static final SecureRandom secureRandom = new SecureRandom();

    public static byte[] hash(final String password, final byte[] salt) {
        final byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_16);
        return SCrypt.generate(passwordBytes, salt, SCRYPT_COST, SCRYPT_BLOCK_SIZE, SCRYPT_PARALLELISM, KEY_LENGTH);
    }

    public static byte[] newSalt() {
        final byte[] salt = new byte[SALT_LENGTH];
        secureRandom.nextBytes(salt);
        return salt;
    }
}
