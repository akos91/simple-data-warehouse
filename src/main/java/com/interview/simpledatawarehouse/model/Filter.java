package com.interview.simpledatawarehouse.model;

public enum Filter {
    DATASOURCE("DATASOURCE IN ", true),
    CAMPAIGN("CAMPAIGN IN ", true),
    FROM_DATE("DAILY > ", false),
    TO_DATE("DAILY < ", false);

    private final String sql;
    private final boolean multipleValuePossible;

    private Filter(String sql, boolean multipleValuePossible) {
        this.sql = sql;
        this.multipleValuePossible = multipleValuePossible;
    }

    public String getSql() {
        return sql;
    }

    public boolean isMultipleValuePossible() {
        return multipleValuePossible;
    }
}
