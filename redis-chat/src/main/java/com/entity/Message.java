package com.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Data
@Getter
@Setter
public class Message implements Serializable {
    private User from;
    private String message;
    private  User to;
    private String time;
}
