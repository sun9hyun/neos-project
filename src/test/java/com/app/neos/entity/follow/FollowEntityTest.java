package com.app.neos.entity.follow;


import com.app.neos.domain.follow.FollowDTO;
import com.app.neos.repository.follow.FollowRepository;
import com.app.neos.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class FollowEntityTest {
        @Autowired
        UserRepository userRepository;
        @Autowired
        FollowRepository followRepository;


        @Test
        public void followSaveTest(){
        FollowDTO followDTO = new FollowDTO();

        Follow follow =  Follow.builder().myId(userRepository.findById(2l).get()).followingId(userRepository.findById(4L).get()).build();
        follow.builder();
        followRepository.save(follow);

        }

    /*    @Test
        public void deleteFollowTest(){
                followRepository.deleteById(25L);
        }*/

}
