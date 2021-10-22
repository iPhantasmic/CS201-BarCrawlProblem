package com.cs201.barcrawl.models;

import com.fasterxml.jackson.databind.JsonNode;

public class Business {

    private String business_id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String postal_code;
    private float latitude;
    private float longitude;
    private float star_rating;
    private int review_count;
    private int is_open;
    private JsonNode attributes;
    private String categories;
    private JsonNode hours;

    public String getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(String business_id) {
        this.business_id = business_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getStar_rating() {
        return star_rating;
    }

    public void setStar_rating(float star_rating) {
        this.star_rating = star_rating;
    }

    public int getReview_count() {
        return review_count;
    }

    public void setReview_count(int review_count) {
        this.review_count = review_count;
    }

    public int getIs_open() {
        return is_open;
    }

    public void setIs_open(int is_open) {
        this.is_open = is_open;
    }

    public JsonNode getAttributes() {
        return attributes;
    }

    public void setAttributes(JsonNode attributes) {
        this.attributes = attributes;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public JsonNode getHours() {
        return hours;
    }

    public void setHours(JsonNode hours) {
        this.hours = hours;
    }
}
