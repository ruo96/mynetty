package com.wrh.string;

/**
 * @Created by wrh
 * @Description:
 * @Date: Created in 下午 5:26 2018/9/28 0028
 * @Modified By:
 */
public class TestString {

    public static void main(String[] args) {
        /*String origin = new String("123");
        String s = "123";
        String s1 = "123".intern();
        String s2 = new String("123");
        String s3 = new String("123");
        String s4 = new String("123").intern();

        System.out.println("origin: "+ System.identityHashCode(origin));
        System.out.println("s: "+ System.identityHashCode(s));
        System.out.println("s1: "+ System.identityHashCode(s1));
        System.out.println("s2: "+ System.identityHashCode(s2));
        System.out.println("s3: "+ System.identityHashCode(s3));
        System.out.println("s4: "+ System.identityHashCode(s4));
        System.out.println("s == s1? " + (s == s1));
        System.out.println("s == s1? " + s == s1);
        System.out.println("s .equals s1? " + s .equals(s1) );

        StringBuffer sb ;*/


       /* String m = new String("1");
        m.intern();
        String m2 = "1";
        System.out.println(m == m2);

        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);*/


        Long a = new Long(3);
        Long b = new Long(3);
        System.out.println(a.longValue()==b.longValue());
        System.out.println(a==b);

    }
}
