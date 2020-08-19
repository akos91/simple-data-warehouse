package com.interview.simpledatawarehouse.controller.request;

import java.util.List;
import java.util.Map;

import com.interview.simpledatawarehouse.model.Dimension;
import com.interview.simpledatawarehouse.model.Filter;
import com.interview.simpledatawarehouse.model.Metric;

public class AdvertisementStatRequest {

    private List<Metric> metrics;
    private List<Dimension> dimensions;
    private Map<Filter, String> filters;

    public AdvertisementStatRequest(List<Metric> metrics, List<Dimension> dimensions, Map<Filter, String> filters) {
        this.metrics = metrics;
        this.dimensions = dimensions;
        this.filters = filters;
    }

    public List<Metric> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<Metric> metrics) {
        this.metrics = metrics;
    }

    public List<Dimension> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<Dimension> dimensions) {
        this.dimensions = dimensions;
    }

    public Map<Filter, String> getFilters() {
        return filters;
    }

    public void setFilters(Map<Filter, String> filters) {
        this.filters = filters;
    }
}
