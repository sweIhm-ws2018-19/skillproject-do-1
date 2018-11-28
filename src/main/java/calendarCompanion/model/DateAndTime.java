package calendarCompanion.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DateAndTime {

    //this Class is the model for the JSON Response from timezonedb API

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("countryName")
    @Expose
    private String countryName;
    @SerializedName("zoneName")
    @Expose
    private String zoneName;
    @SerializedName("abbreviation")
    @Expose
    private String abbreviation;
    @SerializedName("gmtOffset")
    @Expose
    private Integer gmtOffset;
    @SerializedName("dst")
    @Expose
    private String dst;
    @SerializedName("zoneStart")
    @Expose
    private Integer zoneStart;
    @SerializedName("zoneEnd")
    @Expose
    private Integer zoneEnd;
    @SerializedName("nextAbbreviation")
    @Expose
    private String nextAbbreviation;
    @SerializedName("timestamp")
    @Expose
    private Integer timestamp;
    @SerializedName("formatted")
    @Expose
    private String formatted;

    public String getFormatted() {
        return formatted;
    }

    public void setFormatted(String formatted) {
        this.formatted = formatted;
    }

}