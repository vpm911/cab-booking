package com.cabs.app.rest;

import com.cabs.app.service.InsightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class InsightsController {

    @Autowired
    InsightsService insightsService;

    @GetMapping("/insights/idleTime/{cabId}")
    public ResponseEntity<Object> getIdleTimeByCabId(@PathVariable Long cabId, @RequestParam String from,
                                                     @RequestParam String to) {
        Double idleHours = insightsService.getIdleTimeByCabId(cabId,Instant.parse(from),Instant.parse(to));
        return ResponseEntity.ok().body(idleHours);
    }
}
