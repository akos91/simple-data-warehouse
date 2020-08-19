package com.interview.simpledatawarehouse.model;

public enum Metric {
    CLICK("SUM(clicks) AS TOTAL_CLICKS"),
    IMPRESSION("SUM(impressions) AS TOTAL_IMPRESSIONS"),
    CTR("(SUM(clicks) / SUM(impressions))*100 AS CTR");

    private final String sql;

    private Metric(String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }
}
