package com.example.miniprojetand;

public class Hotel {
    private String id;
    private String name;
    private String location;
    private String description;
    private String price;
    private String mainImageUrl;

    public Hotel() {
        // Default constructor for Firebase
    }

    public Hotel(String id, String name, String location, String description, String price, String mainImageUrl) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.price = price;
        this.mainImageUrl = mainImageUrl;
    }

    // Getters and setters for all fields

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMainImageUrl() {
        return mainImageUrl;
    }

    public void setMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }
}
