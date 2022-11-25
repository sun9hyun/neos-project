package com.app.neosproject.repository.inquiry;

import com.app.neosproject.entity.inquiry.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
}
