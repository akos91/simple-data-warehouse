package com.interview.simpledatawarehouse.controller.response;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;

import com.interview.simpledatawarehouse.model.Dimension;
import com.interview.simpledatawarehouse.model.Metric;

public class AdvertisementStatResponse {

    private Map<Dimension, String> dimensions = new EnumMap<>(Dimension.class);
    private Map<Metric, BigDecimal> metrics = new EnumMap<>(Metric.class);

    public AdvertisementStatResponse() {

    }

    public AdvertisementStatResponse(Map<Dimension, String> dimensions, Map<Metric, BigDecimal> metrics) {
        this.dimensions = dimensions;
        this.metrics = metrics;
    }

    public Map<Dimension, String> getDimensions() {
        return dimensions;
    }

    public void setDimensions(Map<Dimension, String> dimensions) {
        this.dimensions = dimensions;
    }

    public void addDimension(Dimension dimension, String value) {
        dimensions.put(dimension, value);
    }

    public Map<Metric, BigDecimal> getMetrics() {
        return metrics;
    }

    public void setMetrics(Map<Metric, BigDecimal> metrics) {
        this.metrics = metrics;
    }

    public void addMetricAndResult(Metric metric, BigDecimal result) {
        metrics.put(metric, result);
    }
}
