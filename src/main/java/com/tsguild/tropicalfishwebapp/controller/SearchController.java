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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Jimmy Cook
 */
@Controller
public class SearchController {

    private FishDao dao;

    @Inject
    public SearchController(FishDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String displaySearchPage() {
        return "search";
    }

    @ResponseBody
    @RequestMapping(value = "search/{searchTerm}", method = RequestMethod.GET)
    public List<Fish> searchFish(@PathVariable String searchTerm) {
            return dao.searchFishByName(searchTerm);
    }
}
