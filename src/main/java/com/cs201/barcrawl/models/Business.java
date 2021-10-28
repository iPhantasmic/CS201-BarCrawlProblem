package com.cs201.barcrawl.models;

import com.cs201.barcrawl.util.JsonNodeConverter;
import com.fasterxml.jackson.databind.JsonNode;

import javax.persistence.*;

@Entity
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String business_id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String postal_code;

    @Column(precision = 10, scale = 8)
    private double latitude;
    @Column(precision = 11, scale = 7)
    private double longitude;

    private double stars;
    private int review_count;
    private int is_open;
    private double price;

    @Convert(converter = JsonNodeConverter.class)
    @Column(columnDefinition = "LONGTEXT")
    private JsonNode attributes;

    @Column(columnDefinition = "LONGTEXT")
    private String categories;

    @Convert(converter = JsonNodeConverter.class)
    @Column(columnDefinition = "LONGTEXT")
    private JsonNode hours;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
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

    public double getPrice() { return  price; }

    public void setPrice(double price) { this.price = price; }

}
