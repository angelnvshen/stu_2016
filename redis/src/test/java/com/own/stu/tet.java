package com.own.stu;

/**
 * Created by CHANEL on 2016/7/21.
 */
public class tet {

    private static String HElLO = "hello";
    public static void main(String[] args) {
       /* System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Math.pow(2, 31));*/

        /*String str1 = new String(HElLO);
        String str2 = new String(HElLO);
        System.out.println(str1 == str2);
        String str3 = HElLO;
        String str4 = HElLO;
        System.out.println(str3 == str4);*/

        /*switch(HElLO){

        }*/
       /* X x = new X();
        x.setName("ooooo");
        System.out.println(x);
        System.out.println(setString(x));
        System.out.println(x);*/

        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program" + "ming";
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s1 == s1.intern());
    }

    public static X setString(X source){
        source.setName("xxxxxxx");
        return source;
    }

    static class X{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "X{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
