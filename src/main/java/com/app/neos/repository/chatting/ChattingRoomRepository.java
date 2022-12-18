package com.app.neos.repository.chatting;

import com.app.neos.entity.chatting.ChattingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChattingRoomRepository extends JpaRepository<ChattingRoom,Long> {
}
