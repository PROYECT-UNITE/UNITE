package com.eci.arsw.project.unite.model;

/**
 *
 * @author sergio
 */
public class Location {
    
    private String latitude;
    private String longitude;

    public Location(String _longitude, String _latitude){
        latitude = _latitude;
        longitude = _longitude;
    }

    public void setLatitude(String _latitude){
        latitude = _latitude;
    }

    public void setLongitude(String _longitude){
        longitude = _longitude;
    }

    public String getLatitude(){
        return latitude;
    }

    public String getLongitude(){
        return longitude;
    }

}
