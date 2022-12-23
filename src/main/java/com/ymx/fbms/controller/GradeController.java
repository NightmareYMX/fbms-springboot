package com.ymx.fbms.controller;

import com.github.pagehelper.PageInfo;
import com.ymx.fbms.pojo.Grade;
import com.ymx.fbms.pojo.Result;
import com.ymx.fbms.service.GradeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/grade")
public class GradeController {

    @Resource
    GradeService gradeService;

    @GetMapping("/getGradePage")
    public PageInfo<Grade> getCoursePage(@RequestParam Integer pageNum,
                                         @RequestParam Integer pageSize,
                                         @RequestParam(required = false, name = "courseNo") Integer courseNo
    ) {
        return gradeService.getGradePage(pageNum, pageSize, courseNo);
    }

    @PostMapping("/addGrade")
    public Result addGrade(@RequestBody Grade grade) {
        int result = gradeService.addGrade(grade);
        if (result == 1) {
            return Result.success(Result.CODE_200);
        } else {
            return Result.error(Result.CODE_500, "增加失败");
        }
    }

    @PostMapping("/updateGrade")
    public Result updateGrade(@RequestBody Grade grade) {
        int result = gradeService.updateGrade(grade);
        if (result == 1) {
            return Result.success(Result.CODE_200);
        } else {
            return Result.error(Result.CODE_500, "更改失败");
        }
    }
}
