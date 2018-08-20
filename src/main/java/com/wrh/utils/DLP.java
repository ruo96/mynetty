package com.wrh.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.util.Random;

public class DLP {
    /**计算离散率, p:素数域p, g:生成元g*/
    public static final BigInteger P = new BigInteger("8878952384603387");
    public static final BigInteger G = new BigInteger("6553");
    private static Random random = new Random();
    private int bits;
    private DLP(int len) {
        bits = decimalBits(len);
    }

    /**
     * 创建一个DLP工具类
     * @param len 10进制位数
     * @return DLP工具类
     */
    public static DLP create(int len){return new DLP(len);}

    /**
     * 获取十进制数的bit数量
     * @param len 十进制数的位数
     * @return
     */
    public static int decimalBits(int len) {
        double bits = len/Math.log10(2);
        return (int)Math.floor(bits);
    }

    /**
     * 生成序号
     * @param r 随机数r
     * @param i 序号i
     * @return 离散序号
     */
    public synchronized BigInteger genSN(BigInteger r, long i) {
        return G.modPow(r.add(BigInteger.valueOf(i)), P);
    }

    /**
     * 产生16位随机数字, 不够前面补0
     * @param r
     * @param i
     * @return
     */
    public synchronized String gen16SN(BigInteger r, long i) {
        String item = genSN(r, i).toString();
        return StringUtils.leftPad(item, 16, "0");
    }

    /**
     * 生成大素数
     * @return 大素数P
     */
    public BigInteger genP() {
        BigInteger prime = BigInteger.probablePrime(bits, random);
        while(true) {
            BigInteger prime2 = prime.subtract(BigInteger.ONE).divide(BigInteger.valueOf(2));
            if(prime2.isProbablePrime(100)) {
                break;
            }
            prime = BigInteger.probablePrime(bits, random);
        }
        return prime;
    }

    /**
     * 获取生成元G
     * @param p 素数域的p
     * @return 生成元G
     */
    public BigInteger genG(BigInteger p) {
        BigInteger g = genPrime(p);
        BigInteger p1 = p.subtract(BigInteger.ONE);
        while(g.compareTo(BigInteger.ONE)==0 || g.gcd(p1).compareTo(BigInteger.ONE)!=0) {
            g = genR();
        }
        return g;
    }

    /**
     * 生成随机数
     * @return 随机数R
     */
    public synchronized BigInteger genR() {
        //生成随机数
        int bound = 1000;
        int r = random.nextInt(bound);
        BigInteger randomBigInteger = genPrime(P).add(BigInteger.valueOf((long)r)).mod(P);
        while(randomBigInteger.compareTo(BigInteger.valueOf(bound))<=0) {
            randomBigInteger = genPrime(P).add(BigInteger.valueOf((long)r)).mod(P);
        }
        return randomBigInteger;
    }

    /**
     * 生成随机素数
     * @param p 素数域的p
     * @return 小于p的素数
     */
    public BigInteger genPrime(BigInteger p) {
        //生成随机数
        int minBits = bits/7;
        int maxBits = bits*6/7;
        int threshold = 2;

        if(minBits < threshold) {
            minBits = threshold;
        }

        int randomBits = random.nextInt(maxBits-minBits);
        randomBits += minBits;
        return BigInteger.probablePrime(randomBits, random).mod(p);
    }
}
