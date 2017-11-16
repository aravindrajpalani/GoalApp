package com.social.goalapp.data.network.model;

/**
 * Created by Aravindraj on 11/6/2017.
 */
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Commentt {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("blogid")
    @Expose
    private Integer blogid;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("commentlikes")
    @Expose
    private List<Commentlike> commentlikes = null;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("date_modified")
    @Expose
    private String dateModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBlogid() {
        return blogid;
    }

    public void setBlogid(Integer blogid) {
        this.blogid = blogid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Commentlike> getCommentlikes() {
        return commentlikes;
    }

    public void setCommentlikes(List<Commentlike> commentlikes) {
        this.commentlikes = commentlikes;
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

}