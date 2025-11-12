package vn.truonggiang.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String username;
    private String email;
    private String password;
    private int roleid; 

    public User() {}

    public User(int id, String username, String email, String password, int roleid) {
        super();
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roleid = roleid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleid() { 
        return roleid;
    }

    public void setRoleid(int roleid) { 
        this.roleid = roleid;
    }

    @Override
    public String toString() {
        return "User [id=" + id 
                + ", username=" + username 
                + ", email=" + email 
                + ", password=" + password 
                + ", roleid=" + roleid + "]";
    }
}
