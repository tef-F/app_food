package com.ditapp.foodapp03.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class CommentModel {
    @SerializedName("id_cmt")
    @Expose
    private Integer idCmt;
    @SerializedName("id_food")
    @Expose
    private Integer idFood;
    @SerializedName("time")
    @Expose
    private Timestamp time;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("avt")
    @Expose
    private String avt;
    @SerializedName("role")
    @Expose
    private Integer role;

    /**
     * No args constructor for use in serialization
     *
     */
    public CommentModel() {
    }

    /**
     *
     * @param role
     * @param idFood
     * @param name
     * @param idCmt
     * @param time
     * @param id
     * @param content
     * @param avt
     */
    public CommentModel(Integer idCmt, Integer idFood, Timestamp time, String content, Integer id, String name, String avt, Integer role) {
        super();
        this.idCmt = idCmt;
        this.idFood = idFood;
        this.time = time;
        this.content = content;
        this.id = id;
        this.name = name;
        this.avt = avt;
        this.role = role;
    }

    public Integer getIdCmt() {
        return idCmt;
    }

    public void setIdCmt(Integer idCmt) {
        this.idCmt = idCmt;
    }

    public Integer getIdFood() {
        return idFood;
    }

    public void setIdFood(Integer idFood) {
        this.idFood = idFood;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "CommentModel{" +
                "idCmt=" + idCmt +
                ", idFood=" + idFood +
                ", time='" + time + '\'' +
                ", content='" + content + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", avt='" + avt + '\'' +
                ", role=" + role +
                '}';
    }
}
