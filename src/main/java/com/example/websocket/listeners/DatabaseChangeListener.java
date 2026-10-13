package com.example.websocket.listeners;

import com.example.websocket.dtos.ProductChangeEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.ObjectMapper;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabaseChangeListener {

    private final SimpMessagingTemplate template;

    @EventListener
    public void handleDatabaseChangeEvent(DatabaseChangeEvent event) throws ClassNotFoundException {
        String destination = "/topic/updates";
        Class<?> className = Class.forName("com.example.websocket.model." + event.getEntityName());

        ObjectMapper  mapper = new ObjectMapper();
        Object source = event.getData();
        Object product;

        if (source instanceof Collection<?>) {
            // Source is a list -> convert to List<className>
            JavaType listType = mapper.getTypeFactory()
                    .constructCollectionType(List.class, className);
            product = mapper.convertValue(source, listType);
        } else {
            // Source is a single object
            product = mapper.convertValue(source, className);
        }

        ProductChangeEvent payload = new ProductChangeEvent(
                product,
                event.getEntityName(),
                event.getChangeType());

        template.convertAndSend(destination, payload);
    }

}
