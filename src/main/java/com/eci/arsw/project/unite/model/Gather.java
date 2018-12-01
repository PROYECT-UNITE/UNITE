package com.eci.arsw.project.unite.model;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author sergio
 */
public class Gather {

    Set<Item> items;

    public Gather() {
        items = ConcurrentHashMap.newKeySet();
    }

    public void addItem(Item item){
        this.items.add(item);
    }

    public void removeItem(Item item){
        this.items.remove(item);
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

}
