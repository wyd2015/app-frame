package com.example.jwt.dao;

import com.example.jwt.model.po.RelRoleUser;
import com.example.jwt.model.po.SysRole;
import com.example.jwt.model.qo.RelRoleUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RelRoleUserMapper {
    long countByExample(RelRoleUserExample example);

    int deleteByExample(RelRoleUserExample example);

    List<RelRoleUser> selectByExample(RelRoleUserExample example);

    int updateByExampleSelective(@Param("record") RelRoleUser record, @Param("example") RelRoleUserExample example);

    int updateByExample(@Param("record") RelRoleUser record, @Param("example") RelRoleUserExample example);
    
    List<SysRole> listRoleByUserId(int userId);
}