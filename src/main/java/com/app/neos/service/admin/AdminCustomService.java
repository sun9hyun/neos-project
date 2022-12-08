package com.app.neos.service.admin;

import com.app.neos.domain.Admin.AdminUserDTO;
import com.app.neos.repository.admin.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminCustomService {
    private final AdminCustomRepository adminCustomRepository;
    private final AdminStoreRepository adminStoreRepository;
    private final AdminStudyRepository adminStudyRepository;
    private final AdminCommunityRepository adminCommunityRepository;
    private final AdminCounselingRepository adminCounselingRepository;
    private final AdminInquiryRepository adminInquiryRepository;
    private final AdminStudyFeedReplyRepository adminStudyFeedReplyRepository;
    private final AdminCommunityReplyRepository adminCommunityReplyRepository;
    private final AdminCommunityReReplyRepository adminCommunityReReplyRepository;
    private final AdminCounselingReplyRepository adminCounselingReplyRepository;
    private final AdminCounselingReReplyRepository adminCounselingReReplyRepository;
    private final AdminStoreReplyRepository adminStoreReplyRepository;
    private final AdminStoreReReplyRepository adminStoreReReplyRepository;

    public AdminUserDTO workCount(Long userId){
        AdminUserDTO adminUserDTO = new AdminUserDTO();
        int replyCount = 0;

        int studyFeedReply = adminStudyFeedReplyRepository.findAllByStudyFeedReplyWriter_UserId(userId).size();
        int communityReply = adminCommunityReplyRepository.findByUser_UserId(userId).size();
        int communityReReply = adminCommunityReReplyRepository.findByUser_UserId(userId).size();
        int counselingReply = adminCounselingReplyRepository.findByUser_UserId(userId).size();
        int counselingReReply = adminCounselingReReplyRepository.findByUser_UserId(userId).size();
        int storeReply = adminStoreReplyRepository.findByUser_UserId(userId).size();
        int storeReReply = adminStoreReReplyRepository.findByUser_UserId(userId).size();

        replyCount = studyFeedReply + communityReply + communityReReply + counselingReply + counselingReReply + storeReply + storeReReply;


        adminUserDTO.setStoreCount((long) adminStoreRepository.findByUser_UserId(userId).size());
        adminUserDTO.setStudyCount((long) adminStudyRepository.findByUser_UserId(userId).size());
        adminUserDTO.setCommunityCount((long) adminCommunityRepository.findByUser_UserId(userId).size());
        adminUserDTO.setCounselingCount((long) adminCounselingRepository.findByUser_UserId(userId).size());
        adminUserDTO.setReplyCount((long) replyCount);
        adminUserDTO.setInquiryCount((long) adminInquiryRepository.findByUser_UserId(userId).size());

        return adminUserDTO;
    }

}
