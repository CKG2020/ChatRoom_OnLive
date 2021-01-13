package com.rj.modules.login.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2021-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("rjuser")
@ApiModel(value="Rjuser对象", description="")
public class Rjuser implements Serializable {

    private static final long serialVersionUID=1L;

    private String rjusername;

    private String password;

    private String gender;

    private Date birthday;

    private String phone;

    private String email;

    private String address;

    private String major;

    private String school;


}
