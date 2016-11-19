/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.tropicalfishwebapp.controller;

import com.tsguild.tropicalfishwebapp.dao.FishDao;
import com.tsguild.tropicalfishwebapp.model.Fish;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Jimmy Cook
 */

@Controller
public class HomeController {

    private FishDao dao;

    // annotation-driven constructor injection
    @Inject
    public HomeController(FishDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String displayHomePage() {
        return "home";
    }

    // Retrieve fish
    @ResponseBody
    @RequestMapping(value = "/fish/{id}", method = RequestMethod.GET)
    public Fish getFish(@PathVariable int id) {
        return dao.getFishByID(id);
    }

    // Create a new fish
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/fish", method = RequestMethod.POST)
    public Fish createFish(@Valid @RequestBody Fish fish) {
        dao.addFish(fish);
        return fish;
    }

    // Delete a fish
    @ResponseStatus(HttpStatus.NO_CONTENT) // NO_CONTENT status because method is void
    @RequestMapping(value = "/fish/{id}", method = RequestMethod.DELETE)
    public void deleteFish(@PathVariable int id) {
        dao.removeFish(id);
    }

    // Update a fish
    @ResponseStatus(HttpStatus.NO_CONTENT) // NO_CONTENT status because method is void
    @RequestMapping(value = "/fish/{id}", method = RequestMethod.PUT)
    public void putFish(@PathVariable int id, @RequestBody Fish fish) {
        fish.setId(id);
        dao.updateFish(fish);
    }

    // Get all fish
    @ResponseBody
    @RequestMapping(value = "/allfish", method = RequestMethod.GET)
    public List<Fish> getAllFish() {
        return dao.getAllFish();
    }

}
