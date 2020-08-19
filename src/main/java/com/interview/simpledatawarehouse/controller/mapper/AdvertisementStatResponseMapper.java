package com.interview.simpledatawarehouse.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.interview.simpledatawarehouse.controller.response.AdvertisementStatResponse;
import com.interview.simpledatawarehouse.controller.response.AdvertisementStatResponses;
import com.interview.simpledatawarehouse.model.AdvertisementStatQueryResult;

@Component
public class AdvertisementStatResponseMapper {

    public AdvertisementStatResponse mapToResponse(AdvertisementStatQueryResult advertisementStatQueryResult) {
        return new AdvertisementStatResponse(advertisementStatQueryResult.getDimensions(),
                advertisementStatQueryResult.getMetrics());
    }

    public AdvertisementStatResponses mapToResponseList(
            List<AdvertisementStatQueryResult> advertisementStatQueryResultList) {
        List<AdvertisementStatResponse> responseList =
                advertisementStatQueryResultList.stream().map(this::mapToResponse).collect(Collectors.toList());
        return new AdvertisementStatResponses(responseList);
    }
}
