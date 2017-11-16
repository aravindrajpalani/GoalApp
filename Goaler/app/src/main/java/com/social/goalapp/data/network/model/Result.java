package com.social.goalapp.data.network.model;

/**
 * Created by Aravindraj on 11/6/2017.
 */
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("commentt")
    @Expose
    private List<Commentt> commentt = null;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("date_modified")
    @Expose
    private String dateModified;
    @SerializedName("goaldays")
    @Expose
    private Integer goaldays;
    @SerializedName("points")
    @Expose
    private Integer points;
    @SerializedName("likes")
    @Expose
    private List<Like> likes = null;
    @SerializedName("expiry_date")
    @Expose
    private String expiryDate;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("complete_allowed")
    @Expose
    private Boolean completeAllowed;
    @SerializedName("owner")
    @Expose
    private String owner;
    @SerializedName("owner_displayname")
    @Expose
    private String ownerDisplayname;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Commentt> getCommentt() {
        return commentt;
    }

    public void setCommentt(List<Commentt> commentt) {
        this.commentt = commentt;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public Integer getGoaldays() {
        return goaldays;
    }

    public void setGoaldays(Integer goaldays) {
        this.goaldays = goaldays;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getCompleteAllowed() {
        return completeAllowed;
    }

    public void setCompleteAllowed(Boolean completeAllowed) {
        this.completeAllowed = completeAllowed;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnerDisplayname() {
        return ownerDisplayname;
    }

    public void setOwnerDisplayname(String ownerDisplayname) {
        this.ownerDisplayname = ownerDisplayname;
    }
}