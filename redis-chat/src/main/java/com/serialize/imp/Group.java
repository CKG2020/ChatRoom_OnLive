package com.serialize.imp;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Data
@Setter
@Getter
public class Group {

    private int  id;
    private String name;
    private List<Student> list = new ArrayList<Student>();

    // 省略 setter、getter

    public void addStudent(Student student) {
        list.add(student);
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + list +
                '}';
    }
}