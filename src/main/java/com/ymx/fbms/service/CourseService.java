package com.ymx.fbms.service;

import com.github.pagehelper.PageInfo;
import com.ymx.fbms.pojo.Course;

import java.util.List;

public interface CourseService {

    /**
     * 获得所有课程信息包括教授老师
     * @param pageNum
     * @param pageSize
     * @param courseName
     * @return PageInfo<Course>
     */
    PageInfo<Course> getCoursePageLike(Integer pageNum, Integer pageSize, String courseName);

    /**
     * 更改授课老师
     * @param course
     * @return int
     */
    int updateTeacherId(Course course);

    /**
     * 删除课程
     * @param course
     * @return int
     */
    int deleteCourseByNo(Course course);

    /**
     * 增加课程
     * @param course
     * @return int
     */
    int addCourse(Course course);

    /**
     * 得到课程的所有种类
     * @return
     */
    List<String> getCourseName();

    Integer getNoByCourseName(String courseName);
}
