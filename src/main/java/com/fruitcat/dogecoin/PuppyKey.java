package com.fruitcat.dogecoin;

import com.fruitcat.bitcoin.Minikey;
import com.google.bitcoin.core.AddressFormatException;
import com.google.bitcoin.core.DumpedPrivateKey;
import com.google.bitcoin.core.ECKey;
import com.google.bitcoin.core.NetworkParameters;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

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
    public static void main(String args[]) throws Exception {
        Minikey minikey;
        if (args.length == 0) {
            minikey = new Minikey();
        } else {
            minikey = new Minikey(args[0]);
        }
        String mk = minikey.toString();
        System.out.println("Minikey: " + mk);
        String dogeKey = miniToDoge(mk);
        System.out.println("Dogecoin key: " + dogeKey);
        System.out.println("Dogecoin address: " + dogeAddress(dogeKey));

        //write qr code for key
        QRCodeWriter qr = new QRCodeWriter();
        BitMatrix m = qr.encode(mk, BarcodeFormat.QR_CODE, 200, 200);
        MatrixToImageWriter.writeToFile(m, "png", new java.io.File("minikey.png"));
        System.out.println("QR code for key was written to minikey.png");
    }
}