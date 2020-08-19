package com.interview.simpledatawarehouse.store.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.interview.simpledatawarehouse.model.AdvertisementStatQueryResult;
import com.interview.simpledatawarehouse.model.Dimension;
import com.interview.simpledatawarehouse.model.Filter;
import com.interview.simpledatawarehouse.model.Metric;
import com.interview.simpledatawarehouse.store.AdvertisementStatStore;

@Component
public class AdvertisementStatStoreImpl implements AdvertisementStatStore {

    private static final Logger LOG = LoggerFactory.getLogger(AdvertisementStatStoreImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AdvertisementStatStoreImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<AdvertisementStatQueryResult> calculateAdvertisementStats(List<Metric> metrics,
            List<Dimension> dimensions, Map<Filter, String> filters) {
        SqlAndParams sqlAndParams = buildAdvertisementStatQuery(metrics, dimensions, filters);
        return jdbcTemplate.query(sqlAndParams.sql, sqlAndParams.params.toArray(), new AdvertisementStatRowMapper());
    }

    SqlAndParams buildAdvertisementStatQuery(List<Metric> metrics, List<Dimension> dimensions,
            Map<Filter, String> filters) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");

        StringJoiner aggregateJoiner = new StringJoiner(",");

        StringJoiner groupByColumnJoiner = new StringJoiner(",");
        if (dimensions != null && !dimensions.isEmpty()) {
            for (Dimension groupBy : dimensions) {
                groupByColumnJoiner.add(groupBy.getDatabaseColumnName());
            }
            aggregateJoiner.add(groupByColumnJoiner.toString());
        }

        for (Metric aggregateOn : metrics) {
            aggregateJoiner.add(aggregateOn.getSql());
        }
        sql.append(aggregateJoiner.toString());

        sql.append(" FROM ADVERTISEMENTSTATS ");

        SqlAndParams filterAndParams = buildSqlFilter(filters);
        sql.append(filterAndParams.sql);

        if (dimensions != null && !dimensions.isEmpty()) {
            sql.append(" GROUP BY ");
            sql.append(groupByColumnJoiner.toString());
        }
        sql.append(";");

        LOG.debug("Generated SQL: {}", sql);
        return new SqlAndParams(sql.toString(), filterAndParams.params);
    }

    private SqlAndParams buildSqlFilter(Map<Filter, String> filters) {
        if (filters == null || filters.isEmpty()) {
            return new SqlAndParams();
        }
        List<Object> filterParams = new LinkedList<>();
        StringBuilder filterSql = new StringBuilder();
        filterSql.append(" WHERE ");
        StringJoiner filterJoiner = new StringJoiner(" AND ");
        for (Map.Entry<Filter, String> filter : filters.entrySet()) {
            if (filter.getKey().isMultipleValuePossible()) {
                StringJoiner filterParamJoiner = new StringJoiner(",");
                String[] splittedFilter = filter.getValue().split(",");
                for (int i = 0; i < splittedFilter.length; i++) {
                    filterParamJoiner.add("?");
                    filterParams.add(splittedFilter[i].trim());
                }
                filterJoiner.add(filter.getKey().getSql() + "(" + filterParamJoiner.toString() + ")");
            } else {
                filterJoiner.add(filter.getKey().getSql() + "?");
                filterParams.add(filter.getValue());
            }
        }
        filterSql.append(filterJoiner.toString());
        return new SqlAndParams(filterSql.toString(), filterParams);
    }

    static class SqlAndParams {
        private final String sql;
        private final List<Object> params;

        public SqlAndParams() {
            this.sql = "";
            this.params = new LinkedList<>();
        }

        public SqlAndParams(String sql, List<Object> params) {
            this.sql = sql;
            this.params = params;
        }

        public String getSql() {
            return sql;
        }

        public List<Object> getParams() {
            return params;
        }
    }
}
