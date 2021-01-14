package com.rj.modules.login.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2021-01-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("rjadmin")
@ApiModel(value="Rjadmin对象", description="")
public class Rjadmin implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer id;

    private String adminname;

    private String password;

    private String nichen;

    @TableField("phoneNumber")
    private String phoneNumber;

    @TableField("telephoneNumber")
    private String telephoneNumber;

    private String address;


}
