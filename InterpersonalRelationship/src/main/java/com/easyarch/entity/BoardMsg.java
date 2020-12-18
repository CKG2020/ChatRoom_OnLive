package com.easyarch.entity;

import lombok.Data;

@Data
public class BoardMsg {
    private String sno;
    private String from_name;
    private String board_text;
    private String date_time;
}
