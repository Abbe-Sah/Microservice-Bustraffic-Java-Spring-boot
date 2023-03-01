package com.abbedev.komtraffic.service;

import com.abbedev.komtraffic.model.Favourite;
import com.abbedev.komtraffic.repository.FavouriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouriteService {

    FavouriteRepository favouriteRepository;

    @Autowired
    public FavouriteService(FavouriteRepository theFavouriteRepository) {
        favouriteRepository = theFavouriteRepository;
    }

    public void save(String start, String destination, float duration, float distance) {
        Favourite favourite = new Favourite();
        favourite.setStart(start);
        favourite.setDestination(destination);
        favourite.setDistance(distance);
        favourite.setDuration(duration);
        favouriteRepository.save(favourite);
    }

    public void delete(long id) {
        favouriteRepository.delete(favouriteRepository.getReferenceById(id));
    }

    public List<Favourite> showAll() {
        return favouriteRepository.findAll();
    }


}
