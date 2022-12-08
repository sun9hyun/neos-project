package com.app.neos.repository.admin;

import com.app.neos.entity.inquiry.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminInquiryRepository extends JpaRepository<Inquiry, Long> {
    public List<Inquiry> findByUser_UserId(Long userId);
}
