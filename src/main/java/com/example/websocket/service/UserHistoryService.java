package com.example.websocket.service;

import com.example.websocket.listeners.DatabaseChangeEvent;
import com.example.websocket.model.UserHistory;
import com.example.websocket.repo.UserHistoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserHistoryService {

    private final UserHistoryRepo userHistoryRepo;
    private final ApplicationEventPublisher publisher;

    @Transactional(readOnly = true)
    private void triggerEvent(DatabaseChangeEvent event){
        publisher.publishEvent(event);
    }

    public void addUserHistory(UserHistory userHistory) {
        userHistoryRepo.save(userHistory);
        DatabaseChangeEvent event = new DatabaseChangeEvent(this,userHistory,"UserHistory","Added UserHistory");
        triggerEvent(event);
    }

    public List<UserHistory> getUserHistory() {
        List<UserHistory> histories  = userHistoryRepo.findAll().stream().limit(10).toList();
        DatabaseChangeEvent event = new DatabaseChangeEvent(this,histories,"UserHistory","Retrieved UserHistory");
        triggerEvent(event);
        return histories;
    }

    public  UserHistory getUserHistoryById(Long id) {
        UserHistory userHistory = userHistoryRepo.findById(id).orElseThrow(() -> new RuntimeException("UserHistory Not Found"));
        DatabaseChangeEvent event = new DatabaseChangeEvent(this,userHistory,"UserHistory","Fetched userHistory");
        triggerEvent(event);
        return userHistory;
    }

    public UserHistory updateUserHistory(String moduleName,Long id) {
        UserHistory userHistory = userHistoryRepo.findById(id).orElseThrow(() -> new RuntimeException("UserHistory Not Found"));
        userHistory.setModuleName(moduleName);
        userHistoryRepo.save(userHistory);
        DatabaseChangeEvent event = new DatabaseChangeEvent(this,userHistory,"UserHistory","Updated UserHistory");
        triggerEvent(event);
        return userHistory;
    }


    public void deleteUserHistoryById(Long id) {
        UserHistory delete = userHistoryRepo.findById(id).orElseThrow(() -> new RuntimeException("UserHistory Not Found"));
        userHistoryRepo.deleteById(id);
        triggerEvent(new DatabaseChangeEvent(this,delete,UserHistory.class.getName(),"Deleted UserHistory"));
    }

}
