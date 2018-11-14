package com.eci.arsw.project.unite.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author sergio
 */
public class Chat {

    List<Message> record;

    public Chat() {
        record = new CopyOnWriteArrayList<>();
    }
    
    public void saveMessage(Message message){
        record.add(message);
    }

    public List<Message> getRecord() {
        return record;
    }

    public void setRecord(List<Message> record) {
        this.record = record;
    }

}
