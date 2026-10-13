package com.example.websocket.controller;

import com.example.websocket.model.UserHistory;
import com.example.websocket.service.UserHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/userhistory")
public class UserHistoryController {

    public final UserHistoryService userHistoryService;

    @PostMapping("/addHistory")
    public String addUserHistory(@RequestBody UserHistory userHistory){
        userHistoryService.addUserHistory(userHistory);
        return "User History Added Successfully";
    }

    @GetMapping("/getUserHistory")
    public  UserHistory getUserHistoryById(@RequestParam Long id){
        return userHistoryService.getUserHistoryById(id);
    }

    @GetMapping("/getAllUserHistory")
    public List<UserHistory> getAllUserHistory(){
        return userHistoryService.getUserHistory();
    }

    @PutMapping("/updateUserHistory")
    public UserHistory updateUserHistory(@RequestParam Long id, @RequestParam String moduleName){
        return userHistoryService.updateUserHistory(moduleName,id);
    }

    @DeleteMapping("/deteUserHistory")
    public String deleteUserHistoryById(@RequestParam Long id){
        userHistoryService.deleteUserHistoryById(id);
        return  "User History Deleted Successfully";
    }

}
