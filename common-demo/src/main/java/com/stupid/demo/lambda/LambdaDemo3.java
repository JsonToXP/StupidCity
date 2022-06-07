package com.stupid.demo.lambda;

/**
 * 对象方法引用
 */
public class LambdaDemo3 {

    public static void main(String[] args) {
        GetPerson getPerson = person -> person.getName();
        System.out.println(getPerson.getPersonName(new Person("张三")));

        /**
         * 函数式接口中需要被实现的方法a 的参数 是一个引用类型
         * 并且该应用类型中的某个方法b的 返回值及其他参数 和方法a一致，则可以简化为下面的格式
         */
        GetPerson getPerson1 = Person::getName;
        System.out.println(getPerson1.getPersonName(new Person("张三")));

        SetPerson setPerson = (person, name) -> person.setName(name);
        Person p = new Person();
        setPerson.setPersonName(p,"李四");
        System.out.println(p.getName());

        SetPerson setPerson1 = Person::setName;
        Person p2 = new Person();
        setPerson1.setPersonName(p2,"王五");
        System.out.println(p2.getName());

        SetPerson setPerson2 = Person::setName2;
        Person p3 = new Person();
        setPerson2.setPersonName(p3,"王五");
        System.out.println(p3.getName());

        ShowPerson showPerson = (person,name,age) -> person.show(name,age);
        showPerson.show(p,"娃哈哈",16);

        ShowPerson showPerson1 = Person::show;
        showPerson1.show(p,"娃哈哈7",17);
    }

    @FunctionalInterface
    interface GetPerson {
        String getPersonName(Person person);
    }

    @FunctionalInterface
    interface SetPerson {
        void setPersonName(Person person,String name);
    }

    @FunctionalInterface
    interface ShowPerson {
        void show(Person person,String name,Integer age);
    }

    public static class Person {

        private String name;

        private Integer age;

        public void show(String name , Integer age){
            System.out.println("name = "+name+"， age = "+age);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setName2(String a){
            this.name = "name";
        }

        public Person() {
        }

        public Person(String name) {
            this.name = name;
        }
    }

}

