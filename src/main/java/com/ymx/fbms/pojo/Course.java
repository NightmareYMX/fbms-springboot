package com.ymx.fbms.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private Integer courseNo;
    private String courseName;
    private Double credits;
    private User user;
}
