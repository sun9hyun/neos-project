package com.app.neos.entity.study;

import com.app.neos.domain.study.StudyFeedDTO;
import com.app.neos.domain.study.StudyQuestionDTO;
import com.app.neos.entity.period.Period;
import com.app.neos.entity.user.User;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "TBL_STUDY_FEED")
@Getter @ToString(exclude = {"studyFeedWriter","study","replies"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyFeed extends Period {
    @Id @GeneratedValue
    private Long studyFeedId;
    @NotNull
    private String studyFeedContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User studyFeedWriter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDY_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Study study;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "studyFeed", cascade = CascadeType.REMOVE)
    private List<StudyFeedReply> replies;

    public void changeList(List<StudyFeedReply> replies){
        this.replies = replies;
    }

    public void changeStudyFeedWriter(User studyFeedWriter){
        this.studyFeedWriter=studyFeedWriter;
    }

    public void changeStudy(Study study){
        this.study=study;
    }

    @Builder
    public StudyFeed(@NotNull String studyFeedContent) {
        this.studyFeedContent = studyFeedContent;
    }

    public void update(StudyFeedDTO studyFeedDTO){
        this.studyFeedContent = studyFeedDTO.getStudyFeedContent();
    }

    public StudyFeedDTO toDTO(){
        String dateFormat = this.getUpdatedDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        StudyFeedDTO dto = new StudyFeedDTO();
        dto.setStudyFeedId(this.studyFeedId);
        dto.setStudyFeedContent(this.studyFeedContent);
        dto.setStudy(this.study.toDTO());
        dto.setCreatedDate(dateFormat);
        dto.setStudyFeedWriter(this.studyFeedWriter.toDTO());
        dto.setReplyLength(this.getReplies().size());
        return dto;
    }

}
