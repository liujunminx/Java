package com.nowcoder.problem.ordinary.november;

public class Test02 {
    public static void main(String[] args) {
        Integer i;
        /**
         * 不能这样定义必须要显示的new数组
         */
//        int[100] arr;
//        int arr[100];
        int[] arr1 = new int[]{
                1, 2, 3, 4, 5
        };
        int[] arr2 = new int[]{
                1, 2, 3, 4, 5
        };
        //直接比较地址
        System.out.println("arr1 == arr2: " + arr1.equals(arr2));
        System.out.println(arr1 + ", " + arr2);
    }
}
