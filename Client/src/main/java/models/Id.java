package models;

/* 
 * POJO for an Id object
 */
public class Id {


    private String userid;
    private String name;
    private String github;
    
    public Id (String name, String githubId) {
        this.userid = "-";
        this.name = name;
        this.github = githubId;
    }

    public Id() {}





    public String getName() {
        return name;
    }

    public String getGithub() {
        return github;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}