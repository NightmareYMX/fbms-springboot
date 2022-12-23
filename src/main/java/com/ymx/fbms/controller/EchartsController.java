package com.ymx.fbms.controller;

import com.ymx.fbms.pojo.Result;
import com.ymx.fbms.pojo.UserEcharts;
import com.ymx.fbms.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/echarts")
public class EchartsController {

    @Resource
    UserService userService;

    @GetMapping("/userDistribution")
    public Result userDistribution() {
        List<UserEcharts> userAddressCount = userService.getUserAddressCount();
        return Result.success(Result.CODE_200, userAddressCount);
    }
}
