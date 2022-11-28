package com.app.neos.entity.counseling;

import com.app.neos.domain.counseling.CounselingDTO;
import com.app.neos.entity.period.Period;
import com.app.neos.entity.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_COUNSELING")
@Getter @ToString(exclude = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Counseling extends Period {

    @Id @GeneratedValue @NonNull
    private Long counselingId;
    @NonNull
    private String counselingTitle;
    @NonNull
    private String counselingContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    public void changeUser(User user){
        this.user = user;
    }

   @Builder
    public Counseling(@NonNull String counselingTitle, @NonNull String counselingContent) {
        this.counselingTitle = counselingTitle;
        this.counselingContent = counselingContent;
    }


    public void update(CounselingDTO counselingDTO){
        this.counselingTitle = counselingDTO.getCounselingTitle();
        this.counselingContent = counselingDTO.getCounselingContent();
    }
}
