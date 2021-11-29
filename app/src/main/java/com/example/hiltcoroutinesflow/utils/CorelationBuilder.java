package com.example.hiltcoroutinesflow.utils;

import java.security.SecureRandom;
import java.util.UUID;

public abstract class CorelationBuilder {

    private static final int DEFAULT_ID_SIZE = 15;
    private static final char[] DEFAULT_CHARACTERS;
    private static final int DEFAULT_CHARACTERS_MASK;
    private static final SecureRandom RAND;
    static {
        DEFAULT_CHARACTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_-".toCharArray();
        DEFAULT_CHARACTERS_MASK = (2 << (31 - Integer.numberOfLeadingZeros(DEFAULT_CHARACTERS.length - 1))) - 1;

        RAND = new SecureRandom(UUID.randomUUID().toString().getBytes());
    }


    public static String newId() {
        return newId(DEFAULT_CHARACTERS, DEFAULT_ID_SIZE);
    }
    public static String newId(final int size) {
        return newId(DEFAULT_CHARACTERS, size);
    }
    public static String newId(final char[] chars, final int size) {
        if (chars == null) {
            throw new IllegalArgumentException("characters cannot be null.");
        }
        if (chars.length == 0 || chars.length >= 256) {
            throw new IllegalArgumentException("characters must contain between 1 and 255 symbols.");
        }
        if (size <= 0) {
            throw new IllegalArgumentException("size must be greater than zero.");
        }
        final SecureRandom rand = RAND;
        final int mask = chars == DEFAULT_CHARACTERS ? DEFAULT_CHARACTERS_MASK :
                (2 << (31 - Integer.numberOfLeadingZeros(DEFAULT_CHARACTERS.length - 1)));
        final int step = (int) Math.ceil(1.6 * mask * size / chars.length);
        final char[] out = new char[size];
        int iout = 0;
        final byte[] bytes = new byte[step];
        int idx;
        for (;;) {
            rand.nextBytes(bytes);
            for (int i = 0; i < step; i++) {
                idx = bytes[i] & mask;
                if (idx < chars.length) {
                    out[iout++] = chars[idx];
                    if (iout == size) {
                        return new String(out);
                    }
                }
            }
        }
    }
}
