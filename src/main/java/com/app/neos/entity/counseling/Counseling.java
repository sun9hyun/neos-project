package com.app.neos.entity.counseling;

import com.app.neos.domain.counseling.CounselingDTO;
import com.app.neos.entity.period.Period;
import com.app.neos.entity.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_COUNSELING")
@Getter @ToString(exclude = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Counseling extends Period {

    @Id @GeneratedValue 
    private Long counselingId;
    @NotNull
    private String counselingTitle;
    @NotNull
    private String counselingContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @JsonIgnore
    private User user;

    public void changeUser(User user){
        this.user = user;
    }

   @Builder
    public Counseling(@NotNull String counselingTitle, @NotNull String counselingContent) {
        this.counselingTitle = counselingTitle;
        this.counselingContent = counselingContent;
    }


    public void update(CounselingDTO counselingDTO){
        this.counselingTitle = counselingDTO.getCounselingTitle();
        this.counselingContent = counselingDTO.getCounselingContent();
    }
}
