package com.ditapp.foodapp03.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StepModel {

    @SerializedName("id_food")
    @Expose
    private Integer idFood;
    @SerializedName("step")
    @Expose
    private Integer step;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("description")
    @Expose
    private String description;

    /**
     * No args constructor for use in serialization
     *
     */
    public StepModel() {
    }

    /**
     *
     * @param image
     * @param idFood
     * @param description
     * @param step
     */
    public StepModel(Integer idFood, Integer step, String image, String description) {
        super();
        this.idFood = idFood;
        this.step = step;
        this.image = image;
        this.description = description;
    }

    public Integer getIdFood() {
        return idFood;
    }

    public void setIdFood(Integer idFood) {
        this.idFood = idFood;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "StepModel{" +
                "idFood=" + idFood +
                ", step=" + step +
                ", image=" + image +
                ", description='" + description + '\'' +
                '}';
    }
}
