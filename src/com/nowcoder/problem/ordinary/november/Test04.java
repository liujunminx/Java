package com.nowcoder.problem.ordinary.november;

public class Test04 {
    public static void main(String[] args) {
        Integer a1 = 59;
        int a2 = 59;
        Integer a3 = Integer.valueOf(59);
        Integer a4 = new Integer(59);

        // int和Integer（无论是否new）比，都为true，因为会把Integer自动拆箱为int再去比较
        System.out.println(a1 == a2);
        // 两个都是非new出来的Integer，如果数在-128到127之间。则是true，否则为false
        System.out.println(a1 == a3);
        // 无论如何, Integer和new Integer不会相等，不会经历拆箱过程
        System.out.println(a3 == a4);
        System.out.println(a2 == a4);
    }
}
