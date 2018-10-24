package com.eci.arsw.project.unite.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author sergio
 */
public class Chat {

    List<String> record;

    public Chat() {
        record = new CopyOnWriteArrayList<>();
    }

    public List<String> getRecord() {
        return record;
    }

    public void setRecord(List<String> record) {
        this.record = record;
    }

}
