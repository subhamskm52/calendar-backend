package com.holiday.calendar.service;

import com.holiday.calendar.exception.HolidayServiceException;
import com.holiday.calendar.model.AvailableCountry;
import com.holiday.calendar.model.Holiday;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class HolidayService {

    private static final Logger logger = LoggerFactory.getLogger(HolidayService.class);
    private static final String AVAILABLE_COUNTRIES_API = "https://date.nager.at/api/v3/AvailableCountries";

    @Autowired
    private RestTemplate restTemplate;


    public List<Holiday> getHolidays(String country, int year) {
        String url = "https://date.nager.at/api/v3/PublicHolidays/" + year + "/" + country;
        logger.info("Fetching holidays for country={} and year={}", country, year);

        try {
            ResponseEntity<Holiday[]> response = restTemplate.getForEntity(url, Holiday[].class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                logger.debug("Received {} holidays", response.getBody().length);
                Holiday[] holidays = response.getBody();

                // Set weekday flag for each holiday
                for (Holiday holiday : holidays) {
                    holiday.setWeekday(isWeekday(LocalDate.parse(holiday.getDate())));
                }
                return Arrays.stream(holidays).toList();
            } else {
                String error = "Empty or failed response: " + response.getStatusCode();
                logger.warn(error);
                throw new HolidayServiceException(error);
            }
        } catch (Exception e) {
            logger.error("Error while calling holiday API", e);
            throw new HolidayServiceException("Failed to fetch holidays", e);
        }
    }
    public List<AvailableCountry> getAvailableCountries() {
        try {
            AvailableCountry[] countries = restTemplate.getForObject(AVAILABLE_COUNTRIES_API, AvailableCountry[].class);
            return List.of(countries);
        } catch (Exception e) {
            logger.error("Error while calling available countries API", e);
            throw new HolidayServiceException("Failed to fetch available countries", e);
        }
    }
    private boolean isWeekday(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return !(day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY);
    }

}
