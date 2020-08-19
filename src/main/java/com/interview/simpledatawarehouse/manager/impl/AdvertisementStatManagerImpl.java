package com.interview.simpledatawarehouse.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.interview.simpledatawarehouse.manager.AdvertisementStatManager;
import com.interview.simpledatawarehouse.model.AdvertisementStatQueryResult;
import com.interview.simpledatawarehouse.model.Dimension;
import com.interview.simpledatawarehouse.model.Filter;
import com.interview.simpledatawarehouse.model.Metric;
import com.interview.simpledatawarehouse.store.AdvertisementStatStore;

@Component
public class AdvertisementStatManagerImpl implements AdvertisementStatManager {

    private final AdvertisementStatStore advertisementStatStore;

    @Autowired
    public AdvertisementStatManagerImpl(AdvertisementStatStore advertisementStatStore) {
        this.advertisementStatStore = advertisementStatStore;
    }

    @Override
    public List<AdvertisementStatQueryResult> calculateAdvertisementStat(List<Metric> metrics,
            List<Dimension> dimensions, Map<Filter, String> filters) {
        return advertisementStatStore.calculateAdvertisementStats(metrics, dimensions, filters);
    }
}
