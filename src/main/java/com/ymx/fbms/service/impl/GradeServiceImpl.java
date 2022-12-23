package com.ymx.fbms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ymx.fbms.mapper.GradeMapper;
import com.ymx.fbms.pojo.Grade;
import com.ymx.fbms.service.GradeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GradeServiceImpl implements GradeService {

    @Resource
    GradeMapper gradeMapper;

    @Override
    public PageInfo<Grade> getGradePage(Integer pageNum, Integer pageSize, Integer courseNo) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(gradeMapper.selectBySIdAndCNo(courseNo));
    }

    @Override
    public int addGrade(Grade grade) {
        return gradeMapper.addGrade(grade);
    }

    @Override
    public int updateGrade(Grade grade) {
        return gradeMapper.updateGrade(grade);
    }
}
