package com.bnu.jlh.application.common;

import java.util.ArrayList;

public class ExcelSheet extends ArrayList<ExcelRow> {
    private String name;

    public ExcelSheet() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
