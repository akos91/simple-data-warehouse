package com.interview.simpledatawarehouse.model;

public enum QueryColumn {

    DATASOURCE("DATASOURCE"),
    CAMPAIGN("CAMPAIGN"),
    DAILY("DAILY"),

    TOTAL_CLICKS("TOTAL_CLICKS"),
    TOTAL_IMPRESSIONS("TOTAL_IMPRESSIONS"),
    CTR("CTR");

    private final String columnName;

    private QueryColumn(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
