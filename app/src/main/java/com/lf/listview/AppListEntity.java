package com.lf.listview;

public class AppListEntity {

    private String title;

    private int image;

    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public static AppListEntity getEntity(String title, String content, int image) {
        AppListEntity entity = new AppListEntity();

        entity.setTitle(title);
        entity.setContent(content);
        entity.setImage(image);

        return entity;
    }
}
