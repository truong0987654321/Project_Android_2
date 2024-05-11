package com.example.Project_android_2.activities.RC_recyclerView;

public class comic_chapter_model {
    private String Image_comic;
    private String Name_comic;
    private Integer Chapter_index;

    public comic_chapter_model() {
    }

    public comic_chapter_model(String name_comic, Integer chapter_index,String image) {
        Name_comic = name_comic;
        Chapter_index = chapter_index;
        Image_comic = image;
    }

    public String getName_comic() {
        return Name_comic;
    }

    public void setName_comic(String name_comic) {
        Name_comic = name_comic;
    }

    public Integer getChapter_index() {
        return Chapter_index;
    }

    public void setChapter_index(Integer chapter_index) {
        Chapter_index = chapter_index;
    }

    public String getImage_comic() {
        return Image_comic;
    }

    public void setImage_comic(String image_comic) {
        Image_comic = image_comic;
    }
}