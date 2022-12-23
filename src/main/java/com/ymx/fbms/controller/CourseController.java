package com.ymx.fbms.controller;

import com.github.pagehelper.PageInfo;
import com.ymx.fbms.pojo.Course;
import com.ymx.fbms.pojo.Result;
import com.ymx.fbms.service.CourseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Resource
    CourseService courseService;

    @GetMapping("/getCoursePage")
    public PageInfo<Course> getCoursePage(@RequestParam Integer pageNum,
                                          @RequestParam Integer pageSize,
                                          @RequestParam String courseName
    ) {
        return courseService.getCoursePageLike(pageNum, pageSize, courseName);
    }

    @PostMapping("/updateTeacher")
    public Result updateTeacher(@RequestBody Course course) {
        int result = courseService.updateTeacherId(course);
        if (result == 1) {
            return Result.success(Result.CODE_200);
        } else {
            return Result.error(Result.CODE_500, "更改失败");
        }
    }

    @PostMapping("/deleteCourseByNo")
    public Result deleteCourseByNo(@RequestBody Course course) {
        int result = courseService.deleteCourseByNo(course);
        if (result == 1) {
            return Result.success(Result.CODE_200);
        } else {
            return Result.error(Result.CODE_500, "删除失败");
        }
    }

    @PostMapping("/addCourse")
    public Result addCourse(@RequestBody Course course) {
        int result = courseService.addCourse(course);
        if (result == 1) {
            return Result.success(Result.CODE_200);
        } else {
            return Result.error(Result.CODE_500, "增加失败");
        }
    }

    @GetMapping("/getAllCourseName")
    public Result getAllCourseName() {
        return Result.success(Result.CODE_200, courseService.getCourseName());
    }

    @GetMapping("/getCourseNo")
    public Result getCourseNo(@RequestParam String courseName) {
        return Result.success(Result.CODE_200, courseService.getNoByCourseName(courseName));
    }
}
