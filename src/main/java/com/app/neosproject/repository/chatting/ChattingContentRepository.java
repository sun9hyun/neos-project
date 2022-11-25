package com.app.neosproject.repository.chatting;

import com.app.neosproject.entity.chatting.ChattingContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChattingContentRepository extends JpaRepository<ChattingContent,Long> {
}
