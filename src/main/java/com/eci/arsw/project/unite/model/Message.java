package com.eci.arsw.project.unite.model;

/**
 *
 * @author SergioRt
 */
public class Message {

    private String text;
    private String author;

    public Message() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Message{" + "text=" + text + ", author=" + author + '}';
    }
    
}
