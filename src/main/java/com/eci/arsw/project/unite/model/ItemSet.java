package com.eci.arsw.project.unite.model;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author sergio
 */
public class ItemSet {

    private Set<Item> items;

    public ItemSet() {
        items = ConcurrentHashMap.newKeySet();
    }

    public void addItem(Item item){
        this.items.add(item);
    }

    public void removeItem(Item item){
        this.items.remove(item);
    }

    public  void changeState(Item item){
        items.remove(item);
        items.add(item);
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

}
