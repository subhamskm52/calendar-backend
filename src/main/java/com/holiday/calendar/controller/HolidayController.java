package com.holiday.calendar.controller;

import com.holiday.calendar.model.Holiday;
import com.holiday.calendar.service.HolidayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/holidays")
public class HolidayController {

    private static final Logger logger = LoggerFactory.getLogger(HolidayController.class);

    @Autowired
    private HolidayService holidayService;

    @GetMapping
    public ResponseEntity<List<Holiday>> getHolidays(
            @RequestParam String country,
            @RequestParam int year) {

        logger.info("Received request to get holidays for country={} and year={}", country, year);
        List<Holiday> holidays = holidayService.getHolidays(country, year);
        logger.info("Returning {} holidays for country={} and year={}", holidays.size(), country, year);

        return ResponseEntity.ok(holidays);
    }
}
