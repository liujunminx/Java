package com.nowcoder.problem.ordinary.november;

public class Test05 {
    private static int i = 1;

    public int getNext(){
        return i++;
    }

    public static void main(String[] args) {
        Test05 test05 = new Test05();
        Test05 testObject = new Test05();
        test05.getNext();
        testObject.getNext();
        System.out.println(testObject.getNext());
    }
}
