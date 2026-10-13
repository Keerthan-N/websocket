package com.example.websocket.listeners;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class DatabaseChangeEvent extends ApplicationEvent {

    private final String entityName;
    private final String changeType;
    private final Object data;

    public DatabaseChangeEvent(Object source,Object data,String entityName,String   changeType) {
        super(source);
        this.data = data;
        this.entityName = entityName;
        this.changeType = changeType;
    }

}
