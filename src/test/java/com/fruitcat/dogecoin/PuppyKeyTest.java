package com.fruitcat.dogecoin;

import com.fruitcat.bitcoin.Minikey;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

/**
 * Unit tests
 */
public class PuppyKeyTest {

    @Test
    public void KeyTest() throws Exception {
        Minikey k = new Minikey("SCKNuQCLTvEAcLP692vXLhTEM91b9s");
        String dk = PuppyKey.miniToDoge(k.toString());
        assertEquals(dk, "6KNpY6t9nadpdz1FcuZANCWRurk7vW26NGR4ccXExWWzLxbTEp4");
        assertEquals(PuppyKey.dogeAddress(dk), "DEQZtG5u4GmNaoUFsoqx3j8azFYZYktnRU");
    }
}
