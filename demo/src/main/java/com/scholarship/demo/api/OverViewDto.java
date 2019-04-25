package com.scholarship.demo.api;

import lombok.Data;

import java.util.List;
@Data
public class OverViewDto {

    List<OverviewResponse> data;
    String level;
}
