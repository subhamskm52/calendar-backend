package com.holiday.calendar.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AvailableCountry {

    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("name")
    private String name;
}
