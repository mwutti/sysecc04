package at.syssec.ss15.ss.ab4.impl.Kohlbacher_Wutti;

import at.syssec.ss15.ss.ab4.DHClientA;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by michael on 29.05.15.
 */
public class DHClientAImpl implements DHClientA {
    private int bitLength;
    private BigInteger p;
    private BigInteger g;
    private BigInteger secret;
    private BigInteger secretPart;
    private BigInteger secretPartPartner;

    @Override
    public void setBitLength(int n) {
        this.bitLength = n;
    }

    @Override
    public BigInteger sendPrime() {
        BigInteger p;
        do {
            BigInteger q = BigInteger.probablePrime(bitLength, new SecureRandom());
            p = q.multiply(BigInteger.valueOf(2)).add(BigInteger.ONE);
        } while (!p.isProbablePrime(1000));
        this.p = p;
        return p;
    }

    @Override
    public BigInteger sendGenerator() {
        g = generateGenerator(p);
        return g;
    }

    @Override
    public BigInteger sendPartOfSecret() {
        this.secret = generatePrivatePart(this.p);
        this.secretPart = g.modPow(this.secret, this.p);

        return this.secretPart;
    }

    @Override
    public void receivePartOfSecret(BigInteger b) {
        this.secretPartPartner = b;
    }

    @Override
    public BigInteger getSecret() {
        return this.secret;
    }



    private BigInteger generateGenerator(BigInteger p) {
        List<BigInteger> primTeiler = findPrimteiler(p.subtract(BigInteger.ONE));

        for (long i = 2; BigInteger.valueOf(i).compareTo(p.subtract(BigInteger.ONE)) <= 0; i++) {
            boolean isGenerator = true;
            BigInteger g = BigInteger.valueOf(i);

            for (BigInteger t : primTeiler) {
                if (g.mod(p.subtract(BigInteger.ONE).divide(t)).compareTo(BigInteger.ONE) == 0) {
                    isGenerator = false;
                }
            }

            BigInteger q = p.subtract(BigInteger.valueOf(1)).divide(BigInteger.valueOf(2));
            if (!g.modPow(q, p).equals(BigInteger.valueOf(1))) {
                isGenerator = false;
            }

            if (isGenerator) {
                return g;
            }
        }
        return null;
    }

    private List<BigInteger> findPrimteiler(BigInteger p) {
        List<BigInteger> primTeiler = new ArrayList<BigInteger>();
        BigInteger prim = BigInteger.valueOf(2);
        BigInteger pTemp = p.add(BigInteger.ZERO);
        //Suche nur notmendig bis n/2 (optimale wäre sqrt(n), ~.~)
        while (prim.compareTo(p.divide(BigInteger.valueOf(2))) <= 0) {
            if (pTemp.mod(prim).equals(BigInteger.ZERO)) {
                primTeiler.add(prim);
                pTemp = pTemp.divide(prim);
            }
            prim = prim.nextProbablePrime();
        }
        return primTeiler;
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
