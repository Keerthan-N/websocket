package com.example.websocket.repo;

import com.example.websocket.model.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHistoryRepo extends JpaRepository<UserHistory, Long> {
}
