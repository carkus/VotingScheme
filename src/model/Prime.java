package model;
import java.math.BigInteger;
import java.util.Random;

public class Prime {

    public static BigInteger getPrime() {        
        long n = new Random().nextLong();
        return generatePrime(1, n < 0 ? n *= -1 : n);
    }

    public static BigInteger generatePrime(long low, long high) {
        Random random = new Random();
        BigInteger p;   // prime number
        do {
            long n = ((long)(random.nextDouble() * (high - low))) + low ;
            p = new BigInteger(Long.toString(n)).nextProbablePrime();
        } while (p.compareTo(new BigInteger(Long.toString(high))) > 0);
        return p;
    }
}
