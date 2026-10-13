package com.example.websocket.dtos;

public record ProductChangeEvent(Object product, String entityName ,String type) {
}
