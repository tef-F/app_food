package com.ditapp.foodapp03.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class FoodFullModel {

    @SerializedName("id_food")
    @Expose
    private Integer idFood;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("ingredient")
    @Expose
    private String ingredient;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("id_category")
    @Expose
    private Integer idCategory;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("avt")
    @Expose
    private String avt;
    @SerializedName("role")
    @Expose
    private Integer role;
    @SerializedName("address")
    @Expose
    private String address;

    /**
     * No args constructor for use in serialization
     *
     */
    public FoodFullModel() {
    }

    /**
     *
     * @param image
     * @param ingredient
     * @param role
     * @param address
     * @param description
     * @param title
     * @param userName
     * @param idCategory
     * @param createdAt
     * @param idFood
     * @param name
     * @param time
     * @param id
     * @param avt
     */
    public FoodFullModel(Integer idFood, String image, String title, String description, String time, String ingredient, String createdAt, Integer idCategory, Integer id, String userName, String name, String avt, Integer role, String address) {
        super();
        this.idFood = idFood;
        this.image = image;
        this.title = title;
        this.description = description;
        this.time = time;
        this.ingredient = ingredient;
        this.createdAt = createdAt;
        this.idCategory = idCategory;
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.avt = avt;
        this.role = role;
        this.address = address;
    }

    public Integer getIdFood() {
        return idFood;
    }

    public void setIdFood(Integer idFood) {
        this.idFood = idFood;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvt() {
        return avt;
    }

    public void setAvt(String avt) {
        this.avt = avt;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "FoodFullModel{" +
                "idFood=" + idFood +
                ", image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", time='" + time + '\'' +
                ", ingredient='" + ingredient + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", idCategory=" + idCategory +
                ", id=" + id +
                ", userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", avt='" + avt + '\'' +
                ", role=" + role +
                ", address='" + address + '\'' +
                '}';
    }
}
