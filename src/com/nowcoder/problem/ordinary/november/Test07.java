package com.nowcoder.problem.ordinary.november;

public class Test07 {
    public static void main(String[] args) {
        split(12);
    }

    public static int split(int number){
        if (number > 1){
            if (number % 2 != 0){
                System.out.println(split((number + 1) / 2));
            }
            System.out.println(split(number / 2));
        }
        return number;
    }
}
