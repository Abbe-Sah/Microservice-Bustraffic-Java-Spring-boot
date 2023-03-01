package com.abbedev.komtraffic.controller;

import com.abbedev.komtraffic.model.Favourite;
import com.abbedev.komtraffic.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favourite")
public class FavouriteController {

    @Autowired
    FavouriteService favouriteService;


    @GetMapping("/all")
    public ResponseEntity<List<Favourite>> showAllFavourite() {
        return ResponseEntity.ok(favouriteService.showAll());
    }


    @DeleteMapping({"/delete/{id}"})
    public ResponseEntity<List<Favourite>> deleteFavourite(@PathVariable Long id) {
        favouriteService.delete(id);
        return ResponseEntity.ok(favouriteService.showAll());
    }

}
