package com.abbedev.komtraffic.controller;


import com.abbedev.komtraffic.model.BusRoute;
import com.abbedev.komtraffic.model.Favourite;
import com.abbedev.komtraffic.service.BusRouteService;
import com.abbedev.komtraffic.service.FavouriteService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping("/search")
public class BusRouteController {

    HttpHeaders header;
    @Autowired
    BusRouteService busRouteService;

    @Autowired
    FavouriteService favouriteService;

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/all")
    public ResponseEntity<List<BusRoute>> showAllFavourite(@RequestHeader("x-api-key") String apikey) {
        if (checkApi(apikey)) {
            return ResponseEntity.status(201).body(busRouteService.showAll());
        } else return ResponseEntity.status(201).body(null);

    }

    @GetMapping("/{start}/{destination}")
    public ResponseEntity<String> showRoute(@PathVariable("start") String start, @PathVariable("destination") String destination, @RequestHeader("x-api-key") String apikey) {
        busRouteService.addFakeRoutes();
        if (busRouteService.findRoute(start) && busRouteService.findRoute(destination) && checkApi(apikey)) {
            String json = new Gson().toJson(busRouteService.showResult(start, destination));
            return ResponseEntity.status(201).body(json);
        } else if (checkApi(apikey)) {
            HttpEntity request = new HttpEntity(header);
            return restTemplate.exchange("https://tomtom-mapcrud.azuremicroservices.io/search/abbe/" + start + "/" + destination, HttpMethod.GET, request, String.class);
        } else return null;

    }

    public boolean checkApi(String apikey) {
        header = new HttpHeaders();
        header.set("x-api-key", apikey);
        if (apikey.equals("2033732")) {
            return true;
        } else {
            return false;
        }
    }

    @GetMapping("/{start}/{destination}/{fav}")
    public List<Favourite> favouritesSet(@PathVariable("start") String start, @PathVariable("destination") String destination, @PathVariable("fav") int fav) {
        busRouteService.addFakeRoutes();
        if (busRouteService.findRoute(start) && busRouteService.findRoute(destination) && fav == 1) {
            favouriteService.save(start, destination, busRouteService.getTotalDuration(), busRouteService.getTotalDistance());
            return favouriteService.showAll();
        }
        return null;
    }

    @ResponseBody
    @PutMapping("/reportHandler")
    public ResponseEntity<List<BusRoute>> addDelayToBus(@RequestBody BusRoute busRoute) {
        busRouteService.updateDelay(busRoute.getId(), busRoute.getDelay());
        return ResponseEntity.status(201).body(busRouteService.showAll());
    }


}
