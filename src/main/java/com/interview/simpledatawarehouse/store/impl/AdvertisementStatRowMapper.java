package com.interview.simpledatawarehouse.store.impl;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.interview.simpledatawarehouse.model.AdvertisementStatQueryResult;
import com.interview.simpledatawarehouse.model.Dimension;
import com.interview.simpledatawarehouse.model.Metric;
import com.interview.simpledatawarehouse.model.QueryColumn;

public class AdvertisementStatRowMapper implements RowMapper<AdvertisementStatQueryResult> {

    @Override
    public AdvertisementStatQueryResult mapRow(ResultSet rs, int rowNum) throws SQLException {

        AdvertisementStatQueryResult result = new AdvertisementStatQueryResult();

        ResultSetMetaData rsmd = rs.getMetaData();
        int columns = rsmd.getColumnCount();
        for (int i = 1; i <= columns; i++) {
            if (rsmd.getColumnName(i).equals(QueryColumn.DATASOURCE.getColumnName())) {
                result.addDimension(Dimension.DATASOURCE, rs.getString(QueryColumn.DATASOURCE.getColumnName()));
            }
            if (rsmd.getColumnName(i).equals(QueryColumn.CAMPAIGN.getColumnName())) {
                result.addDimension(Dimension.CAMPAIGN, rs.getString(QueryColumn.CAMPAIGN.getColumnName()));
            }
            if (rsmd.getColumnName(i).equals(QueryColumn.DAILY.getColumnName())) {
                result.addDimension(Dimension.DAILY, rs.getString(QueryColumn.DAILY.getColumnName()));
            }
            if (rsmd.getColumnName(i).equals(QueryColumn.TOTAL_CLICKS.getColumnName())) {
                result.addMetricAndResult(Metric.CLICK, rs.getBigDecimal(QueryColumn.TOTAL_CLICKS.getColumnName()));
            }
            if (rsmd.getColumnName(i).equals(QueryColumn.TOTAL_IMPRESSIONS.getColumnName())) {
                result.addMetricAndResult(Metric.IMPRESSION,
                        rs.getBigDecimal(QueryColumn.TOTAL_IMPRESSIONS.getColumnName()));
            }
            if (rsmd.getColumnName(i).equals(QueryColumn.CTR.getColumnName())) {
                result.addMetricAndResult(Metric.CTR, rs.getBigDecimal(QueryColumn.CTR.getColumnName()));
            }
        }
        return result;
    }
}
