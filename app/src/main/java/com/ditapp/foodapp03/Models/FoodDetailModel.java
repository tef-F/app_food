package com.ditapp.foodapp03.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FoodDetailModel {

    @SerializedName("food")
    @Expose
    private List<FoodFullModel> food = null;
    @SerializedName("step")
    @Expose
    private List<StepModel> step = null;
    @SerializedName("comment")
    @Expose
    private List<CommentModel> comment = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public FoodDetailModel() {
    }

    /**
     *
     * @param step
     * @param comment
     * @param food
     */
    public FoodDetailModel(List<FoodFullModel> food, List<StepModel> step, List<CommentModel> comment) {
        super();
        this.food = food;
        this.step = step;
        this.comment = comment;
    }

    public List<FoodFullModel> getFood() {
        return food;
    }

    public void setFood(List<FoodFullModel> food) {
        this.food = food;
    }

    public List<StepModel> getStep() {
        return step;
    }

    public void setStep(List<StepModel> step) {
        this.step = step;
    }

    public List<CommentModel> getComment() {
        return comment;
    }

    public void setComment(List<CommentModel> comment) {
        this.comment = comment;
    }


}
