package com.ymx.fbms.mapper;

import com.ymx.fbms.pojo.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper {

    /**
     * 查询课程详细信息(左外连接用户表)
     * @param courseName
     * @return List<Course>
     */
    List<Course> getCourseByCourseNameLike(@Param("courseName") String courseName);

    /**
     * 更新老师
     * @param course
     * @return int
     */
    int updateTeacherId(@Param("course") Course course);

    /**
     * 通过no删除课程
     * @param course
     * @return
     */
    int deleteByNo(@Param("course") Course course);

    /**
     * 增加course
     * @param course
     * @return
     */
    int addCourse(@Param("course") Course course);

    /**
     * 得到所有的课程种类
     * @return
     */
    List<String> getAllCourseName();

    /**
     * 通过课程名获得课程号
     * @param courseName
     * @return
     */
    Integer getNoByCourseName(@Param("courseName") String courseName);
}
