package calendarCompanion.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DateAndTime {

    @JsonProperty("status")
    private String status;
    @JsonProperty("message")
    private String message;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("countryName")
    private String countryName;
    @JsonProperty("zoneName")
    private String zoneName;
    @JsonProperty("abbreviation")
    private String abbreviation;
    @JsonProperty("gmtOffset")
    private Integer gmtOffset;
    @JsonProperty("dst")
    private String dst;
    @JsonProperty("zoneStart")
    private Integer zoneStart;
    @JsonProperty("zoneEnd")
    private Integer zoneEnd;
    @JsonProperty("nextAbbreviation")
    private String nextAbbreviation;
    @JsonProperty("timestamp")
    private Integer timestamp;
    @JsonProperty("formatted")
    private String formatted;

    public String getFormatted() {
        return formatted;
    }

    public void setFormatted(String formatted) {
        this.formatted = formatted;
    }

}