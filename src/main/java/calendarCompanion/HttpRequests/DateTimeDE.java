package calendarCompanion.HttpRequests;

import calendarCompanion.model.DateAndTime;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DateTimeDE {

    private String day;
    private String month;
    private String year;
    private String time;

    public DateTimeDE(){}
    
    public void httpGetTimeAndDate() throws IOException{
        //Build HTTP Request
        //API Endpoint : api.timezonedb.com/v2.1/get-time-zone
        //Parameters: key = KH3NW23Z0BX (to validate registered Account), format = json(response-Body), zone = Europe/Berlin
        String url = "http://api.timezonedb.com/v2.1/get-time-zone?key=QKH3NW23Z0BX&format=json&by=zone&zone=Europe/Berlin";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");

        //read response string(JSON)
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //map to javaclass
        ObjectMapper mapper = new ObjectMapper();
        DateAndTime dnt = mapper.readValue(response.toString(), DateAndTime.class);

        System.out.println(dnt.getFormatted());

        List<String> formattedResponseTimeAndDate = Arrays.asList(dnt.getFormatted().split(" "));
        List<String> dateList = Arrays.asList(formattedResponseTimeAndDate.get(0).split("-"));

        this.time = formattedResponseTimeAndDate.get(1).substring(0,5);
        this.year = dateList.get(0);
        this.day = dateList.get(2);

        switch(dateList.get(1)){
            case("01"):
                this.month = "Januar";
                break;
            case("02"):
                this.month = "Februar";
                break;
            case("03"):
                this.month = "März";
                break;
            case("04"):
                this.month = "April";
                break;
            case("05"):
                this.month = "Mai";
                break;
            case("06"):
                this.month = "Juni";
                break;
            case("07"):
                this.month = "Juli";
                break;
            case("08"):
                this.month = "August";
                break;
            case("09"):
                this.month = "September";
                break;
            case("10"):
                this.month = "Oktober";
                break;
            case("11"):
                this.month = "November";
                break;
            case("12"):
                this.month = "Dezember";
                break;
        }
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getTime() {
        return time;
    }
}
