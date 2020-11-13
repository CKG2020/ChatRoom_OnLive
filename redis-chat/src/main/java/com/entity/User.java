package com.entity;

import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Getter
@Setter
public class User implements Serializable {
    private  Long id;
    private String name;
    private String avatar;

}
