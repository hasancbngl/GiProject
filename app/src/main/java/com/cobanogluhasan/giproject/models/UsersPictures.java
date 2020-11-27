package com.cobanogluhasan.giproject.models;

public class UsersPictures {

    private String userLocation;
    private String userEmail;
    private String imageUrl;

    public UsersPictures(String userLocation, String userEmail, String imageUrl) {
        this.userLocation = userLocation;
        this.userEmail = userEmail;
        this.imageUrl = imageUrl;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
