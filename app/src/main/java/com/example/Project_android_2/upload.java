package com.example.Project_android_2;

public class upload {
    private String id;
    private String emali;
    private String password;
    private String user_name;
    private String uid;
    private String avatar;

    public upload() {

    }

    public upload(String id, String avatar) {
        if (avatar.trim().equals("")) {
            avatar = "no img";
        }
        this.id = id;
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setImgURL(String avatar) {
        this.avatar = avatar;
    }

    public String getEmali() {
        return emali;
    }

    public void setEmali(String emali) {
        this.emali = emali;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
