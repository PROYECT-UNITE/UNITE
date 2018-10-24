package com.eci.arsw.project.unite.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author sergio
 */
public class Gather {

    List<Item> items;

    public Gather() {
        items = new CopyOnWriteArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    
    

}
