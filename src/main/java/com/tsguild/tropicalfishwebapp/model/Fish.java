/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.tropicalfishwebapp.model;

import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Jimmy Cook
 */
public class Fish {
    
    private int id;
    @NotEmpty(message="Fish name can't be empty.")
    @Length(max=50, message="Fish name must be no more than 50 characters in length.")
    private String name;
    @NotEmpty(message="Scientific name can't be empty.")
    @Length(max=100, message="Fish scientific name must be no more than 100 characters in length.")
    private String scientificName;
    @NotEmpty(message="Water type can't be empty.")
    @Length(max=50, message="Water type must be no more than 50 characters in length.")
    private String waterType;
    @NotEmpty(message="URL for fish picture can't be empty.")
    @Length(max=150, message="URL for fish picturee must be no more than 150 characters in length.")
    private String pictureURL;

    public Fish(int id, String name, String scientificName, String waterType, String pictureURL) {
        this.id = id;
        this.name = name;
        this.scientificName = scientificName;
        this.waterType = waterType;
        this.pictureURL = pictureURL;
    }

    public Fish(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getWaterType() {
        return waterType;
    }

    public void setWaterType(String waterType) {
        this.waterType = waterType;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.id;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.scientificName);
        hash = 79 * hash + Objects.hashCode(this.waterType);
        hash = 79 * hash + Objects.hashCode(this.pictureURL);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Fish other = (Fish) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.scientificName, other.scientificName)) {
            return false;
        }
        if (!Objects.equals(this.waterType, other.waterType)) {
            return false;
        }
        if (!Objects.equals(this.pictureURL, other.pictureURL)) {
            return false;
        }
        return true;
    }
    
    
    
}
