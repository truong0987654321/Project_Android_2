package com.example.Project_android_2.models;

public class chapter {
    private int id;
    private String title;
    private String index;
    private String content;
    private String id_comic;

    public chapter() {

    }

    public chapter(int id, String title, String index, String content, String id_comic) {
        this.id = id;
        this.title = title;
        this.index = index;
        this.content = content;
        this.id_comic = id_comic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId_comic() {
        return id_comic;
    }

    public void setId_comic(String id_comic) {
        this.id_comic = id_comic;
    }
}
