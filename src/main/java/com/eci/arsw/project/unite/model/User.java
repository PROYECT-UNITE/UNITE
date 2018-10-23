package com.eci.arsw.project.unite.model;

/**
 *
 * @author sergio
 */
public class User {
    
    public static Integer ASSISTANT = 1;
    public static Integer INDETERMINATE = 2;
    public static Integer INVOLUNTARY_ABSENCE = 3;

    private String username;
    private String password;
    private String mail;
    private String name;

    public User(String username, String password, String mail, String name) {
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.name = name;
    }
    
    public User() {
        
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
