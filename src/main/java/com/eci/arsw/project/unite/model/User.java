package com.eci.arsw.project.unite.model;

import com.eci.arsw.project.unite.services.UniteException;
import java.util.regex.Pattern;
import org.springframework.data.annotation.Id;

/**
 *
 * @author sergio
 */
public class User {

    @Id
    private String username;
    
    private String password;
    private String mail;
    private String name;

    public User(String username, String password, String mail, String name) throws UniteException {
        this.username = username;
        passwordValid(password);
        this.password = password;
        this.mail = mail;
        this.name = name;
    }
    
    public User() {
        
    }

    private void passwordValid(String password) throws UniteException {
        if(password.length()<6)throw new UniteException("Password is too short minimum 6 characters and get "+password.length());
        if(!Pattern.compile("[^a-zA-Z]").matcher(password).find())throw new UniteException("The password must have special characters or numbers.");
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
