package com.holiday.calendar.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Holiday {

    @JsonProperty("date")
    private String date;

    @JsonProperty("localName")
    private String localName;

    @JsonProperty("name")
    private String name;

    @JsonProperty("countryCode")
    private String countryCode;

    private boolean weekday;

    public String getDate() {
        return date;
    }
    public void setWeekday(boolean weekday) {
        this.weekday = weekday;
    }
}
