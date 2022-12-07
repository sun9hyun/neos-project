package com.app.neos.entity.study;

import com.app.neos.domain.study.StudyQuestionDTO;
import com.app.neos.domain.study.StudyQuestionReplyDTO;
import com.app.neos.entity.period.Period;
import com.app.neos.entity.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "TBL_STUDY_QUESTION")
@Getter @ToString(exclude = {"studyQuestionWriter", "study","replies"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyQuestion extends Period {
    @Id @GeneratedValue
    private Long studyQuestionId;
    @NonNull
    private String studyQuestionContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User studyQuestionWriter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDY_ID")
    private Study study;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "studyQuestion", cascade = CascadeType.REMOVE)
    private List<StudyQuestionReply> replies;

    public void changeReplies(List<StudyQuestionReply> replies){
        this.replies=replies;
    }

    public void changeStudyQuestionWriter(User studyQuestionWriter){
        this.studyQuestionWriter = studyQuestionWriter;
    }

    public void changeStudyId(Study study){
        this.study = study;
    }

    @Builder
    public StudyQuestion(@NonNull String studyQuestionContent) {
        this.studyQuestionContent = studyQuestionContent;
    }

    public void update(String content){
        this.studyQuestionContent = content;
    }

    public StudyQuestionDTO toDTO(){
        String dateFormat = this.getUpdatedDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        StudyQuestionDTO dto = new StudyQuestionDTO();
        dto.setStudyQuestionContent(this.studyQuestionContent);
        dto.setStudyQuestionWriter(this.studyQuestionWriter.toDTO());
        dto.setStudyQuestionId(this.studyQuestionId);
        dto.setStudyDTO(this.study.toDTO());
        dto.setCreatedDate(dateFormat);
        dto.setReplyLength(this.getReplies().size());
        return dto;
    }
}
