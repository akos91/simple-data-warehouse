package com.interview.simpledatawarehouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.interview.simpledatawarehouse.controller.mapper.AdvertisementStatResponseMapper;
import com.interview.simpledatawarehouse.controller.request.AdvertisementStatRequest;
import com.interview.simpledatawarehouse.controller.response.AdvertisementStatApi;
import com.interview.simpledatawarehouse.controller.response.AdvertisementStatErrorResponse;
import com.interview.simpledatawarehouse.manager.AdvertisementStatManager;
import com.interview.simpledatawarehouse.model.AdvertisementStatQueryResult;

@RestController
public class AdvertisementStatsController {

    private static final Logger LOG = LoggerFactory.getLogger(AdvertisementStatsController.class);

    private final AdvertisementStatManager advertisementStatManager;
    private final AdvertisementStatResponseMapper advertisementStatResponseMapper;

    @Autowired
    public AdvertisementStatsController(AdvertisementStatManager advertisementStatManager,
            AdvertisementStatResponseMapper advertisementStatResponseMapper) {
        this.advertisementStatManager = advertisementStatManager;
        this.advertisementStatResponseMapper = advertisementStatResponseMapper;
    }

    @PostMapping("/advertisement/stats")
    public ResponseEntity<AdvertisementStatApi> advertisementStats(@RequestBody AdvertisementStatRequest request) {
        if (request.getMetrics() == null || request.getMetrics().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new AdvertisementStatErrorResponse("Metrics must be provided!"));
        }
        try {
            List<AdvertisementStatQueryResult> result =
                    advertisementStatManager.calculateAdvertisementStat(request.getMetrics(), request.getDimensions(),
                            request.getFilters());
            return ResponseEntity.ok(advertisementStatResponseMapper.mapToResponseList(result));
        } catch (Exception e) {
            LOG.error("Server error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AdvertisementStatErrorResponse("Internal Server Error"));
        }
    }
}
