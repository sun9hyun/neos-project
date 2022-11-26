package com.app.neos.repository.chatting;

import com.app.neos.entity.chatting.ChattingContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChattingContentRepository extends JpaRepository<ChattingContent,Long> {
}
