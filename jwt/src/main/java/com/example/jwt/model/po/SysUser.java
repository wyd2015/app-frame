package com.example.jwt.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value="com-example-jwt-model-po-SysUser")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser implements Serializable {
    @ApiModelProperty(value="")
    private Integer userId;

    @ApiModelProperty(value="")
    private String loginAccount;

    @ApiModelProperty(value="")
    private String password;

    @ApiModelProperty(value="")
    private Date createTime;

    @ApiModelProperty(value="")
    private Date updateTime;

    @ApiModelProperty(value="")
    private Boolean actStatus;

    private static final long serialVersionUID = 1L;

    public static final String COL_USER_ID = "user_id";

    public static final String COL_LOGIN_ACCOUNT = "login_account";

    public static final String COL_PASSWORD = "password";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_ACT_STATUS = "act_status";
}