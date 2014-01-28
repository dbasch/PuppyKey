package com.fruitcat.bitcoin;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 *  A Bitcoin mini private key, see:
 *  https://en.bitcoin.it/wiki/Mini_private_key_format
 */
public class Minikey {

    String BASE58 = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";
    private String key;

    // generate a potential mini key
    private String candidate() {

        SecureRandom sr = new SecureRandom();
        StringBuffer buf = new StringBuffer("S");
        int l = BASE58.length();
        for (int i = 0; i < 29; i++) {
            buf.append(BASE58.charAt(sr.nextInt(l)));
        }
        return buf.toString();
    }

    /**
     * Creates a new random Minikey.
     */
    public Minikey() {

        String c = null;
        for (byte valid = -1; valid != 0; ) {
            c = candidate();
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] d = md.digest(c.concat("?").getBytes());
                valid = d[0];
            }
            catch(NoSuchAlgorithmException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        key = c;
    }

    /**
     * Creates a Minikey object from a string representation of an existing mini key.
     * @param key
     */
    public Minikey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return key;
    }

    /**
     * Returns this key as a 32-byte array.
     * @return
     */
    public byte[] keyBytes() {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(key.getBytes());
        }
        catch(NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
