package com.ymx.fbms.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Files {
    private Integer fileId;
    private String fileName;
    private String fileType;
    private Long fileSize;
    private String fileUrl;
    private Boolean fileEnable;
}
