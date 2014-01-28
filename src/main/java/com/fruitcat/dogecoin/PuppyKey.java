package com.fruitcat.dogecoin;

import com.fruitcat.bitcoin.Minikey;
import com.google.bitcoin.core.AddressFormatException;
import com.google.bitcoin.core.DumpedPrivateKey;
import com.google.bitcoin.core.ECKey;
import com.google.bitcoin.core.NetworkParameters;

/**
 * Mini keys for Dogecoin.
 */
public class PuppyKey {

    /**
     * Converts a mini key to a private Dogecoin key.
     * @param key
     * @return
     */
    public static String miniToDoge(String key) {
        Minikey mk = new Minikey(key);
        ECKey k = new ECKey(mk.keyBytes(), null);
        return k.getPrivateKeyEncoded(new DogeParams()).toString();
    }

    /**
     * Derives the Dogecoin address from a private key.
     * @param key
     * @return
     * @throws AddressFormatException
     */
    public static String dogeAddress(String key) throws AddressFormatException {
        NetworkParameters np = new DogeParams();
        DumpedPrivateKey dk = new DumpedPrivateKey(np, key);
        return dk.getKey().toAddress(np).toString();
    }

    /**
     * Generates a random mini key, derives a dogecoin key/address pair from it.
     * @param args
     * @throws AddressFormatException
     */
    public static void main(String args[]) throws AddressFormatException {
        String mk = new Minikey().toString();
        System.out.println("Minikey: " + mk);
        String dogeKey = miniToDoge(mk);
        System.out.println("Dogecoin key: " + dogeKey);
        System.out.println("Dogecoin address: " + dogeAddress(dogeKey));
    }
}