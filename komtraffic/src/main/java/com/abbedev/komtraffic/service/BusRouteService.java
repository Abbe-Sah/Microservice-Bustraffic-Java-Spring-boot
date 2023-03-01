package com.abbedev.komtraffic.service;

import com.abbedev.komtraffic.model.BusRoute;
import com.abbedev.komtraffic.model.ResultRoute;
import com.abbedev.komtraffic.repository.BusRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusRouteService {

    BusRouteRepository busRouteRepository;
    private float totalDuration;
    private float totalDistance;

    @Autowired
    public BusRouteService(BusRouteRepository theBusRouteRepository) {
        busRouteRepository = theBusRouteRepository;
    }

    public void addFakeRoutes() {
    }

    public List<BusRoute> showAll() {
        return busRouteRepository.findAll();
    }

    public Boolean findRoute(String route) {
        addFakeRoutes();
        List<BusRoute> busRoute = busRouteRepository.findAll();
        for (BusRoute name : busRoute) {
            if (name.getName().equalsIgnoreCase(route)) {
                totalDistance = 0;
                totalDuration = 0;
                totalDistance += name.getDistance();
                totalDuration += name.getDuration();
                return true;
            }
        }
        return false;
    }

    public List<ResultRoute> showResult(String start, String destination) {
        List<ResultRoute> result = new ArrayList<>();
        ResultRoute resultRoute = new ResultRoute();
        resultRoute.setStart(start);
        resultRoute.setDestination(destination);
        resultRoute.setDistance(totalDistance);
        resultRoute.setDuration(totalDuration);
        result.add(resultRoute);
        return result;

    }

    public void updateDelay(long id, String message) {
        Optional<BusRoute> currentBus = busRouteRepository.findById(id);
        currentBus.get().setDelay(message);
        busRouteRepository.save(currentBus.get());
    }

    public float getTotalDuration() {
        return totalDuration;
    }

    public float getTotalDistance() {
        return totalDistance;
    }

}
