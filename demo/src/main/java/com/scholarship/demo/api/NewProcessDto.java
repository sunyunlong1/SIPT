package com.scholarship.demo.api;

import lombok.Data;

@Data
public class NewProcessDto {

    private String processName;
    private String beginTime;
    private String endTime;
}
