package com.selenium.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by Rayant on 12.04.2017.
 */
public class RandomGenerator {
    private static SecureRandom random = new SecureRandom();

    public static String nextString() {
        return new BigInteger(50, random).toString(32);
    }

}
