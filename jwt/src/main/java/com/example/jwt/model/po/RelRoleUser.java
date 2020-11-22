package com.example.jwt.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@ApiModel(value="com-example-jwt-model-po-RelRoleUser")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelRoleUser implements Serializable {
    @ApiModelProperty(value="")
    private Integer rUId;

    @ApiModelProperty(value="")
    private Integer roleId;

    @ApiModelProperty(value="")
    private Integer userId;

    private static final long serialVersionUID = 1L;

    public static final String COL_R_U_ID = "r_u_id";

    public static final String COL_ROLE_ID = "role_id";

    public static final String COL_USER_ID = "user_id";
}