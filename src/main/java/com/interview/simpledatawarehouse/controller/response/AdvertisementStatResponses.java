package com.interview.simpledatawarehouse.controller.response;

import java.util.List;

public class AdvertisementStatResponses implements AdvertisementStatApi {

    private List<AdvertisementStatResponse> advertisementStats;

    public AdvertisementStatResponses() {

    }

    public AdvertisementStatResponses(List<AdvertisementStatResponse> advertisementStats) {
        this.advertisementStats = advertisementStats;
    }

    public List<AdvertisementStatResponse> getAdvertisementStats() {
        return advertisementStats;
    }

    public void setAdvertisementStats(List<AdvertisementStatResponse> advertisementStats) {
        this.advertisementStats = advertisementStats;
    }
}
