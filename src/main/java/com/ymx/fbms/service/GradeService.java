package com.ymx.fbms.service;


import com.github.pagehelper.PageInfo;
import com.ymx.fbms.pojo.Grade;

public interface GradeService {

    /**
     * 获得成绩分页信息
     * @param pageNum
     * @param pageSize
     * @param courseNo
     * @return PageInfo<Course>
     */
    PageInfo<Grade> getGradePage(Integer pageNum, Integer pageSize, Integer courseNo);

    int addGrade(Grade grade);

    int updateGrade(Grade grade);
}
