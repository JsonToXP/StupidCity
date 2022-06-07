package com.stupid.demo.lambda;

/**
 * LambdaDemo表达式 语法
 */
public class LambdaDemo {

    public static void main(String[] args) {
        // 没有参数，没有返回值
        NoParamAndNoReturn noParamAndNoReturn = () -> {
            System.out.println("没有参数，没有返回值");
        };
        noParamAndNoReturn.test();
        // 一个参数，没有返回值
        OneParamAndNoReturn oneParamAndNoReturn = a -> {
            System.out.println("一个参数，没有返回值 a = " + a);
        };
        oneParamAndNoReturn.test(1);
        // 多个参数，没哟返回值
        TwoParamAndNoReturn twoParamAndNoReturn = (a,b) -> {
            System.out.println("多个参数，没有返回值 a，b = " + a + "," + b);
        };
        twoParamAndNoReturn.test(3,5);
        // 没有参数，有返回值
        NoParamAndReturn noParamAndReturn = () -> {
            System.out.println("没有参数，有返回值");
            return 1;
        };
        int r = noParamAndReturn.test();
        // 一个参数，有返回值
        OneParamAndReturn oneParamAndReturn = a -> {
            System.out.println("一个参数，有返回值 a = " + a);
            return a;
        };
        int r1 = oneParamAndReturn.test(3);
        // 多个参数，有返回值
        TwoParamAndReturn twoParamAndReturn = (a,b) -> {
            System.out.println("多个参数，有返回值 a + b = " + (a+b));
            return a + b;
        };
        int r2 = twoParamAndReturn.test(3,5);
        System.out.println("r = "+r);
        System.out.println("r1 = "+r1);
        System.out.println("r2 = "+r2);
    }

    /**
     * 没有参数，没有返回值
     */
    @FunctionalInterface
    interface NoParamAndNoReturn{
        void test();
    }

    /**
     * 一个参数，没有返回值
     */
    @FunctionalInterface
    interface OneParamAndNoReturn{
        void test(int a);
    }

    /**
     * 多个参数，没有返回值
     */
    @FunctionalInterface
    interface TwoParamAndNoReturn{
        void test(int a,int b);
    }

    /**
     * 没有参数，有返回值
     */
    interface NoParamAndReturn{
        int test();
    }

    /**
     * 一个参数，有返回值
     */
    interface OneParamAndReturn{
        int test(int a);
    }

    /**
     * 多个参数，有返回值
     */
    interface TwoParamAndReturn{
        int test(int a,int b);
    }
}
