package at.syssec.ss15.ss.ab4.impl.Kohlbacher_Wutti;

import at.syssec.ss15.ss.ab4.DHClientA;

import java.math.BigInteger;

/**
 * Created by michael on 29.05.15.
 */
public class DHClientAImpl implements DHClientA {
    @Override
    public void setBitLength(int n) {

    }

    @Override
    public BigInteger sendPrime() {
        return null;
    }

    @Override
    public BigInteger sendGenerator() {
        return null;
    }

    @Override
    public BigInteger sendPartOfSecret() {
        return null;
    }

    @Override
    public void receivePartOfSecret(BigInteger b) {

    }

    @Override
    public BigInteger getSecret() {
        return null;
    }
}
