package com.ymx.fbms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ymx.fbms.mapper.CourseMapper;
import com.ymx.fbms.pojo.Course;
import com.ymx.fbms.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Resource
    CourseMapper courseMapper;

    @Override
    public PageInfo<Course> getCoursePageLike(Integer pageNum, Integer pageSize, String courseName) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(courseMapper.getCourseByCourseNameLike(courseName));
    }

    @Override
    public int updateTeacherId(Course course) {
        return courseMapper.updateTeacherId(course);
    }

    @Override
    public int deleteCourseByNo(Course course) {
        return courseMapper.deleteByNo(course);
    }

    @Override
    public int addCourse(Course course) {
        return courseMapper.addCourse(course);
    }

    @Override
    public List<String> getCourseName() {
        return courseMapper.getAllCourseName();
    }

    @Override
    public Integer getNoByCourseName(String courseName) {
        return courseMapper.getNoByCourseName(courseName);
    }
}
