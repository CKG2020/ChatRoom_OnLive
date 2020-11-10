package com;

import java.util.ArrayList;
//惰性求值与及早求值



public class TestStream {
    public static void main(String[] args) {
//        List<com.Student> studentList = Stream.of(new com.Student("路非", 20, 175),
//                new com.Student("独孤", 20, 168),
//                new com.Student("东方不败", 16, 185)).collect(Collectors.toList());
//        System.out.println(studentList);
//
        ArrayList<Object> students = new ArrayList<>(3);
        students.add(new Student("xx",20,168));
           students.add( new Student("yy",43,180));
           students.add(new Student("zz",81,176));
//        List<com.Student> list= students.stream().filter(stu->stu.getHeight() < 180)
//                .collect(Collectors.toList());
//
//        System.out.println(list);

//        students.stream().map(student->studen)

//
//        Integer reduce = Stream.of(1, 2, 3, 4).reduce(0, (acc, x) -> acc + x);
//        System.out.println(reduce);
    }

}

