package com.eci.arsw.project.unite.model;

import java.util.Objects;

/**
 * @author sergio
 */
public class Item {

    private String name;
    private String description;
    private String onCharge;

    public Item() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOnCharge() {
        return onCharge;
    }

    public void setOnCharge(String onCharge) {
        this.onCharge = onCharge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name) &&
                Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}
