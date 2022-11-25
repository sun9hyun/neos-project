package com.app.neosproject.repository.chatting;

import com.app.neosproject.entity.chatting.Chatting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChattingRepository extends JpaRepository<Chatting,Long> {
}
