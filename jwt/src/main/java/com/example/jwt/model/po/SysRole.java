package com.example.jwt.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@ApiModel(value="com-example-jwt-model-po-SysRole")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRole implements Serializable {
    @ApiModelProperty(value="")
    private Integer roleId;

    @ApiModelProperty(value="")
    private String roleName;

    private static final long serialVersionUID = 1L;

    public static final String COL_ROLE_ID = "role_id";

    public static final String COL_ROLE_NAME = "role_name";
}