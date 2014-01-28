package com.fruitcat.dogecoin;

import com.google.bitcoin.core.NetworkParameters;

/**
 * Private key and address headers for the Dogecoin network.
 */
public class DogeParams extends NetworkParameters {
    protected int dumpedPrivateKeyHeader = 158; //This is always addressHeader + 128
    protected int addressHeader = 30;

    public int getDumpedPrivateKeyHeader() {
        return dumpedPrivateKeyHeader;
    }
    public int getAddressHeader() {
        return addressHeader;
    }

}
