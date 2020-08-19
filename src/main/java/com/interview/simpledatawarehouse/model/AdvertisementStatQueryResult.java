package com.interview.simpledatawarehouse.model;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;

public class AdvertisementStatQueryResult {

    private final Map<Dimension, String> dimensions = new EnumMap<>(Dimension.class);
    private final Map<Metric, BigDecimal> metrics = new EnumMap<>(Metric.class);

    public Map<Dimension, String> getDimensions() {
        return dimensions;
    }

    public void addDimension(Dimension dimension, String value) {
        dimensions.put(dimension, value);
    }

    public Map<Metric, BigDecimal> getMetrics() {
        return metrics;
    }

    public void addMetricAndResult(Metric metric, BigDecimal result) {
        metrics.put(metric, result);
    }
}
