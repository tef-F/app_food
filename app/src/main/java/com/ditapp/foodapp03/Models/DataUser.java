package com.ditapp.foodapp03.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataUser {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
//    @SerializedName("password")
//    @Expose
//    private String password;
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
    public DataUser() {
    }

    /**
     *
     * @param avt
     * @param name
     * @param email
//     * @param password
     * @param role
     * @param id
     * @param userName
     */
    public DataUser(Integer id, String userName,String name, String email, String avt, Integer role) {
        super();
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.email = email;
//        this.password = password;
        this.avt = avt;
        this.role = role;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

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
        StringBuilder sb = new StringBuilder();
        sb.append(DataUser.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("userName");
        sb.append('=');
        sb.append(((this.userName == null)?"<null>":this.userName));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("email");
        sb.append('=');
        sb.append(((this.email == null)?"<null>":this.email));
        sb.append(',');
        sb.append("avt");
        sb.append('=');
        sb.append(((this.avt == null)?"<null>":this.avt));
        sb.append(',');
        sb.append("role");
        sb.append('=');
        sb.append(((this.role == null)?"<null>":this.role));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}
