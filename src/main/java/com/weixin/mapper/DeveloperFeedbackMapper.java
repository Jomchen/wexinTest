package com.weixin.mapper;


import com.weixin.entity.DeveloperFeedback;

public interface DeveloperFeedbackMapper {

    int deleteById(Integer wid);

    int insert(DeveloperFeedback record);

    int insertSelective(DeveloperFeedback record);

    DeveloperFeedback selectById(Integer wid);

    int updateByIdSelective(DeveloperFeedback record);

    int updateById(DeveloperFeedback record);

}