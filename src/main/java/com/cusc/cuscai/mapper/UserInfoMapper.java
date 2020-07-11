package com.cusc.cuscai.mapper;


import java.util.List;

import com.cusc.cuscai.entity.bo.UserInfoBO;
import com.cusc.cuscai.entity.model.UserInfo;
import com.cusc.cuscai.entity.model.UserInfoExample;
import org.apache.ibatis.annotations.Param;

public interface UserInfoMapper {
    int countByExample(UserInfoExample example);

    int deleteByExample(UserInfoExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    List<UserInfoBO> selectByExample(UserInfoExample example);

    UserInfoBO selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    int updateByExample(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}