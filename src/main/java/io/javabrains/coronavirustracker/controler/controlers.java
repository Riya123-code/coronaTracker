package io.javabrains.coronavirustracker.controler;
//returns template html page to which name point to

import io.javabrains.coronavirustracker.models.Locationstate;
import io.javabrains.coronavirustracker.services.coronavirusesdataservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class controlers {

    @Autowired
    coronavirusesdataservice coronavirusesdataservice;

    @GetMapping("/")
    public String home(Model model)
    {
        List<Locationstate> allstats =coronavirusesdataservice.getAllstats();
       int totalcases= allstats.stream().mapToInt(stat->stat.getLatestTotalCases()).sum();
       int totalnewcases= allstats.stream().mapToInt(stat->stat.getDifffromprevday()).sum();

        model.addAttribute( "locationstates" , allstats);
model.addAttribute( "totalreportedcases" , totalcases);
        model.addAttribute( "totalnewcases" , totalnewcases);


        return"index";
    }


}
