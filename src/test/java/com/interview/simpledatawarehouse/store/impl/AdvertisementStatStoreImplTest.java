package com.interview.simpledatawarehouse.store.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import com.interview.simpledatawarehouse.model.Dimension;
import com.interview.simpledatawarehouse.model.Filter;
import com.interview.simpledatawarehouse.model.Metric;
import com.interview.simpledatawarehouse.store.impl.AdvertisementStatStoreImpl.SqlAndParams;

@RunWith(MockitoJUnitRunner.class)
public class AdvertisementStatStoreImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    private AdvertisementStatStoreImpl advertisementStatStore;

    @Before
    public void setup() {
        advertisementStatStore = new AdvertisementStatStoreImpl(jdbcTemplate);
    }

    @Test
    public void buildAdvertisementStatQuerySqlTest() {

        // GIVEN
        List<Metric> metrics = new ArrayList<>();
        metrics.add(Metric.CLICK);

        List<Dimension> dimensions = new ArrayList<>();
        dimensions.add(Dimension.DATASOURCE);

        Map<Filter, String> filters = new HashMap<>();
        filters.put(Filter.CAMPAIGN, "Google Ads");

        // WHEN
        SqlAndParams sqlAndParams = advertisementStatStore.buildAdvertisementStatQuery(metrics, dimensions, filters);

        // THEN
        String expectedSql =
                "SELECT datasource,SUM(clicks) AS TOTAL_CLICKS FROM ADVERTISEMENTSTATS  WHERE campaign in (?) GROUP BY datasource;";
        assertEquals(normalizeString(expectedSql), normalizeString(sqlAndParams.getSql()));

        assertEquals(1, sqlAndParams.getParams().size());
        assertEquals("Google Ads", sqlAndParams.getParams().get(0));
    }

    private String normalizeString(String value) {
        return value.toLowerCase().replaceAll("\\s+", "");
    }
}
