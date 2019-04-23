package com.scholarship.demo.api;

import lombok.Data;

import java.util.List;

@Data
public class JudgeRep {

    private String title;
    private List<JudgeViewRep> data;
}
