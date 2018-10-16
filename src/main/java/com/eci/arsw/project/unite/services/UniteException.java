package com.eci.arsw.project.unite.services;

/**
 *
 * @author sergio
 */
public class UniteException extends Exception {

    public UniteException(String message, Throwable cause) {
        super(message, cause);
    }

    public UniteException(String message) {
        super(message);
    }
}
