package com.selenium.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Rayant on 07.04.2017.
 */
public class Message {

    @Expose
    private Long id;


    @Expose
    @SerializedName("tl")
    private String title;


    @Expose
    @SerializedName("tx")
    private String text;


    @Expose
    @SerializedName("cd")
    private int code = 1;

    @Expose
    @SerializedName("s")
    private int send;


    @Expose
    @SerializedName("r")
    private int received;


    @Expose
    @SerializedName("c")
    private int clicked;


    @Expose
    @SerializedName("icon")
    private String icon;

    //in seconds
    @Expose
    @SerializedName("ttl")
    private int ttl = 24 * 60 * 60;

    @Expose
    @SerializedName("redirect")
    private String redirect;

    @Expose
    @SerializedName("action")
    private String action = "open";

    @Expose
    @SerializedName("duration")
    private int duration;

    @Expose
    @SerializedName("summertime")
    private Boolean summertime;

    @Expose
    @SerializedName("vibrate")
    private ArrayList<Integer> vibrate;


    @Expose
    @SerializedName("filter")
    private String filter;

    @Expose
    @SerializedName("date")
    private Date date = new Date();

    @Expose
    @SerializedName("delayed")
    private boolean delayed = false;


    @Expose
    @SerializedName("zone")
    private String zone;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getSend() {
        return send;
    }

    public void setSend(int send) {
        this.send = send;
    }

    public int getReceived() {
        return received;
    }

    public void setReceived(int received) {
        this.received = received;
    }

    public int getClicked() {
        return clicked;
    }

    public void setClicked(int clicked) {
        this.clicked = clicked;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Boolean getSummertime() {
        return summertime;
    }

    public void setSummertime(Boolean summertime) {
        this.summertime = summertime;
    }

    public ArrayList<Integer> getVibrate() {
        return vibrate;
    }

    public void setVibrate(ArrayList<Integer> vibrate) {
        this.vibrate = vibrate;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isDelayed() {
        return delayed;
    }

    public void setDelayed(boolean delayed) {
        this.delayed = delayed;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public static final String MESSAGE_EXAMPLE = "{\"tl\":\"Hello\",\"tx\":\"Hello,Selenium\",\"redirect\":\"http://7600.at.ua\",\"zone\":\"Etc/GMT+4\",\"ttl\":14400,\"duration\":10,\"filter\":{},\"delayed\":false,\"summertime\":true}";


    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", code=" + code +
                ", send=" + send +
                ", received=" + received +
                ", clicked=" + clicked +
                ", icon='" + icon + '\'' +
                ", ttl=" + ttl +
                ", redirect='" + redirect + '\'' +
                ", action='" + action + '\'' +
                ", duration=" + duration +
                ", summertime=" + summertime +
                ", vibrate=" + vibrate +
                ", filter='" + filter + '\'' +
                ", date=" + date +
                ", delayed=" + delayed +
                ", zone='" + zone + '\'' +
                '}';
    }
}
