package com.interview.simpledatawarehouse.model;

public enum Dimension {
    DATASOURCE("DATASOURCE"), CAMPAIGN("CAMPAIGN"), DAILY("DAILY");

    private final String databaseColumnName;

    private Dimension(String databaseColumnName) {
        this.databaseColumnName = databaseColumnName;
    }

    public String getDatabaseColumnName() {
        return databaseColumnName;
    }
}
