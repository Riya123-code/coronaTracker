package io.javabrains.coronavirustracker.services;

import io.javabrains.coronavirustracker.models.Locationstate;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.json.JSONException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;



@Service
public class coronavirusesdataservice {

    private static String virus_data_url="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    private List<Locationstate> allstats = new ArrayList<>();

    public List<Locationstate> getAllstats() {
        return allstats;
    }

    @PostConstruct
@Scheduled(cron = "* * 1 * * *")
    public void fetchvirusdata() throws IOException, InterruptedException, JSONException {
     List<Locationstate> newstats = new ArrayList<>();


    HttpClient client = HttpClient.newHttpClient();
       HttpRequest request = HttpRequest.newBuilder()
       .uri(URI.create(virus_data_url))
               .build();
      HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
    StringReader csvBodyReader = new StringReader(httpResponse.body());  //reader
    Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);

        for (CSVRecord record : records) {
        Locationstate locationstates =new Locationstate();

        locationstates.setState(record.get("Province/State"));
        locationstates.setCountry(record.get("Country/Region"));

int latestcases=Integer.parseInt(record.get(record.size() - 1));
        int prevdaycases=Integer.parseInt(record.get(record.size() - 2));
int newcases=latestcases-prevdaycases;

        locationstates.setLatestTotalCases(latestcases);
        locationstates.setDifffromprevday(latestcases-prevdaycases);

        System.out.println(locationstates);
var countries=record.get("Country/Region");

            newstats.add(locationstates);

            }



        this.allstats=newstats;


        }


    }





