package com.app.neos.repository.chatting;

import com.app.neos.entity.chatting.Chatting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChattingRepository extends JpaRepository<Chatting,Long> {
}
