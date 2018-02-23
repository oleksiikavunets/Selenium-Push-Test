package com.selenium.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import utils.JsonUtils;


public class Tariff {


    @Expose
    @SerializedName("id")
    private Long id;

    @Expose
    @SerializedName("n")
    private String name;

    @Expose
    @SerializedName("p")
    private Double price;

    @Expose
    @SerializedName("m")
    private Long maxFolowers;

    @Expose
    @SerializedName("ol")
    private Long overLimit;

    @Expose
    @SerializedName("o")
    private Long overPrice;

    @Expose
    @SerializedName("d")
    private String description;


    public Tariff() {

    }

    public String getName() {
        return name;
    }

    public Tariff setName(String name) {
        this.name = name;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Tariff setPrice(Double price) {
        this.price = price;
        return this;
    }


    public Long getId() {
        return id;
    }

    public Tariff setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getOverPrice() {
        return overPrice;
    }

    public Tariff setOverPrice(Long overPrice) {
        this.overPrice = overPrice;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOverLimit() {
        return overLimit;
    }

    public Tariff setOverLimit(Long overLimit) {
        this.overLimit = overLimit;
        return this;
    }

    public String toString() {
        return JsonUtils.getJson(this);
    }

    public int compareTo(Object o) {
        return this.overPrice.compareTo(((Tariff) o).getOverPrice());
    }
}
