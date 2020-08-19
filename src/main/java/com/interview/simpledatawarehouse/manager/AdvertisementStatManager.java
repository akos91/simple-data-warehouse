package com.interview.simpledatawarehouse.manager;

import java.util.List;
import java.util.Map;

import com.interview.simpledatawarehouse.model.AdvertisementStatQueryResult;
import com.interview.simpledatawarehouse.model.Dimension;
import com.interview.simpledatawarehouse.model.Filter;
import com.interview.simpledatawarehouse.model.Metric;

public interface AdvertisementStatManager {
    List<AdvertisementStatQueryResult> calculateAdvertisementStat(List<Metric> metrics, List<Dimension> dimensions,
            Map<Filter, String> filters);
}
