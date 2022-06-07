package com.stupid.demo.lambda;

import lombok.ToString;

/**
 * 函数引用
 * 在方法体中不要写复杂的逻辑，可以将复杂逻辑抽离成方法，在lambda表达式中引用即可（函数引用）
 * 函数引用只需关注引用的方法的 请求参数 和 返回值 与函数式接口中的方法一致即可
 */
public class LambdaDemo2 {

    public static void main(String[] args) {
        // 引用非静态方法
        TwoParamAndReturn twoParamAndReturn = new LambdaDemo2()::localTest;
        int r = twoParamAndReturn.test(1, 2);
        System.out.println("r = " + r);

        // 引用静态方法
        TwoParamAndReturn twoParamAndReturn2 = LambdaDemo2::localTest2;
        int r2 = twoParamAndReturn2.test(3, 4);
        System.out.println("r2 = " + r2);

        // 在静态方法中不能new非静态内部类
        // 非静态内部类，必须有一个外部类的引用才能创建
        // 外部类的非静态方法中，因为有隐含的外部类引用this，所以可以直接创建非静态内部类
        // UserFactory userFactory = new LambdaDemo2().new User()::getUser;
        // 引用构造方法
        UserFactory userFactory = () -> new User();
        System.out.println(userFactory.getUser().toString());

        UserFactory userFactory1 = new User()::getUser;
        System.out.println(userFactory1.getUser().toString());

        UserFactory userFactory11 = User::new;
        System.out.println(userFactory11.getUser().toString());

        UserFactory2 userFactory2 = User::new;
        System.out.println(userFactory2.getUser("张三").toString());

        UserFactory3 userFactory3 = User::new;
        System.out.println(userFactory3.getUser("李四",10).toString());
    }

    @FunctionalInterface
    interface TwoParamAndReturn {
        int test(int a,int b);
    }

    public Integer localTest(Integer a, Integer b){
        System.out.println("计算 a + b = "+(a+b));
        return a+b;
    }

    public static Integer localTest2(Integer a, Integer b){
        System.out.println("计算 a + b = "+(a+b));
        return a+b;
    }

    @FunctionalInterface
    interface UserFactory {
        User getUser();
    }

    @FunctionalInterface
    interface UserFactory2 {
        User getUser(String name);
    }

    @FunctionalInterface
    interface UserFactory3 {
        User getUser(String name,Integer age);
    }

    @ToString
    public static class User {
        private String name;
        private Integer age;

        public User() {
        }

        public User(String name) {
            this.name = name;
        }

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public User getUser(){
            return new User();
        }
    }

}
