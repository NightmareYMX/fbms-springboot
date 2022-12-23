package com.ymx.fbms.mapper;

import com.ymx.fbms.pojo.Grade;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GradeMapper {

    List<Grade> selectBySIdAndCNo(@Param("courseNo") Integer courseNo);

    int addGrade(@Param("grade") Grade grade);

    int updateGrade(@Param("grade") Grade grade);
}
