/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.tropicalfishwebapp.dao;

import com.tsguild.tropicalfishwebapp.model.Fish;
import com.tsguild.tropicalfishwebapp.model.WaterTypeCount;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 *
 * @author Jimmy Cook
 */
public interface FishDao {
    
    public Fish addFish(Fish fish);
    
    public Fish getFishByID(int id);
    public List<Fish> getAllFish();
    
    public void updateFish(Fish fish);
    
    public void removeFish(int id);
    
    public List<Fish> searchFishByName(String name);
    
    public List<WaterTypeCount> getWaterTypeCounts();
    
}
