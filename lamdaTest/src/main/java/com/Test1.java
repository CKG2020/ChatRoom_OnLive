package com;

import java.math.BigDecimal;
import java.util.function.*;
//函数式接口
public class Test1 {
    public static void main(String[] args) {
        Predicate<Integer> predicate= x -> x >185;
        Student student = new Student("xx", 20, 160);
//        System.out.println("xx的身高高于１８５吗："+predicate.test( student.getHeight()));

        Consumer<String> consumer = System.out::println;
        consumer.accept("我命由我不由天");


                                            //方法引用
                                            // ClassName::methodName
          Function<Student,String>  function=Student::getName;
        String apply = function.apply(student);
        System.out.println(apply);


        Supplier<Integer>  supplier=()->Integer.valueOf(BigDecimal.TEN.toString());
        System.out.println(supplier.get());

  //转换为相反的值
        UnaryOperator<Boolean> unaryOperator=xxx->!xxx;
        Boolean apply1 = unaryOperator.apply(false);
        System.out.println(apply1);
//自定义表达式　　也可以叫自定义运算符
        BinaryOperator<Integer> operator=(x,y)->x*y;
        Integer apply2 = operator.apply(2, 3);
        System.out.println(apply2);
        test(()->"我是一个演示的函数式接口");
    }
    public  static  void test(Worker worker){
        String work = worker.work();
        System.out.println(work);

    }
    public  interface Worker{
        String work();
    }


}
