package com.srgstart.zhongdada.mapper;

import com.srgstart.zhongdada.model.dto.statistic.AppAnswerCountDTO;
import com.srgstart.zhongdada.model.dto.statistic.AppAnswerResultCountDTO;
import com.srgstart.zhongdada.model.entity.UserAnswer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Administrator
* @description 针对表【user_answer(用户答题记录)】的数据库操作Mapper
* @createDate 2024-09-12 09:07:37
* @Entity com.srgstart.zhongdada.model.entity.UserAnswer
*/
public interface UserAnswerMapper extends BaseMapper<UserAnswer> {

    List<AppAnswerCountDTO> doAppAnswerCount();

    List<AppAnswerResultCountDTO> doAppAnswerResultCount(@Param("appId") Long appId);

}





