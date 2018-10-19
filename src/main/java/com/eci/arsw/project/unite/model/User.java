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

    public String getUsername() {
        return username;
    }

    public String getMail() {
        return mail;
    }
    
}
