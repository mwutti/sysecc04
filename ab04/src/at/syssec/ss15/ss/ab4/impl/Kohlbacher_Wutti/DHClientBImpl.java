package at.syssec.ss15.ss.ab4.impl.Kohlbacher_Wutti;

import at.syssec.ss15.ss.ab4.DHClientB;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by michael on 29.05.15.
 */
public class DHClientBImpl implements DHClientB {

    private BigInteger p;
    private BigInteger g;
    private BigInteger secret;
    private BigInteger secretPart;
    private BigInteger secretPartPartner;

    @Override
    public void receivePrime(BigInteger p) {
        this.p = p;
    }

    @Override
    public void receiveGenerator(BigInteger q) {
        this.g = q;
    }

    @Override
    public void receivePartOfSecret(BigInteger a) {
        this.secretPartPartner = a;
    }

    @Override
    public BigInteger sendPartOfSecret() {
        this.secret = generatePrivatePart(this.p);
        this.secretPart = g.modPow(this.secret, this.p);

        return this.secretPart;
    }

    @Override
    public BigInteger getSecret() {
        return this.secret;
    }

    private BigInteger generatePrivatePart(BigInteger p) {
        BigInteger max = p.subtract(BigInteger.valueOf(2));
        BigInteger d;
        do {
            d = new BigInteger(max.bitLength(), new SecureRandom());
        } while (d.compareTo(BigInteger.valueOf(2)) < 0);
        return d;
    }

}
