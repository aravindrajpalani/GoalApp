package com.social.goalapp.data.network.model;

/**
 * Created by Aravindraj on 11/9/2017.
 */


        import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class Profile {

    @SerializedName("displayname")
    @Expose
    private String displayname;
    @SerializedName("fbid")
    @Expose
    private String fbid;
    @SerializedName("bio")
    @Expose
    private String bio;
    @SerializedName("goalpoints")
    @Expose
    private Integer goalpoints;
    @SerializedName("followinglist")
    @Expose
    private List<User> followinglist = null;
    @SerializedName("followerslist")
    @Expose
    private List<User> followerslist = null;

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getFbid() {
        return fbid;
    }

    public void setFbid(String fbid) {
        this.fbid = fbid;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Integer getGoalpoints() {
        return goalpoints;
    }

    public void setGoalpoints(Integer goalpoints) {
        this.goalpoints = goalpoints;
    }

    public List<User> getFollowinglist() {
        return followinglist;
    }

    public void setFollowinglist(List<User> followinglist) {
        this.followinglist = followinglist;
    }

    public List<User> getFollowerslist() {
        return followerslist;
    }

    public void setFollowerslist(List<User> followerslist) {
        this.followerslist = followerslist;
    }





}