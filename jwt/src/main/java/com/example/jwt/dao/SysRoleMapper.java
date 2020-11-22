package com.example.jwt.dao;

import com.example.jwt.model.po.SysRole;
import com.example.jwt.model.qo.SysRoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper {
    long countByExample(SysRoleExample example);

    int deleteByExample(SysRoleExample example);

    List<SysRole> selectByExample(SysRoleExample example);

    int updateByExampleSelective(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    int updateByExample(@Param("record") SysRole record, @Param("example") SysRoleExample example);
}