package com.example.wxj.my_firstapp.bean;


public class Video {
    private int id;
    private String name;
    private String videoUrl;
    private String videoImageUrl;

    public Video(int id, String name, String videoUrl, String videoImageUrl) {
        this.id = id;
        this.name = name;
        this.videoUrl = videoUrl;
        this.videoImageUrl = videoImageUrl;
    }

    public String getVideoImageUrl() {
        return videoImageUrl;
    }

    public void setVideoImageUrl(String videoImageUrl) {
        this.videoImageUrl = videoImageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", videoImageUrl='" + videoImageUrl + '\'' +
                '}';
    }
}
