    package com.app.neos.service.join;
    
    import com.app.neos.domain.college.CollegeDTO;
    import com.app.neos.domain.user.UserDTO;
    import com.app.neos.entity.college.College;
    import com.app.neos.entity.user.User;
    import com.app.neos.repository.college.CollegeRepository;
    import com.app.neos.repository.user.CollegesCustomRepository;
    import com.app.neos.repository.user.UserRepository;
    import com.app.neos.type.user.UserCollegeMajor;
    import lombok.RequiredArgsConstructor;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    
    import java.util.Comparator;
    import java.util.HashSet;
    import java.util.List;
    import java.util.stream.Collectors;
    
    import static com.app.neos.type.user.UserCollegeMajor.*;
    
    @Service
    @Slf4j
    @RequiredArgsConstructor
    public class JoinService {
        private final UserRepository userRepository;
        private final CollegeRepository collegeRepository;
        private final CollegesCustomRepository collegesCustomRepository;
    
        public void join(UserDTO userDTO){
            UserDTO joinUser = userDTO;
            joinUser.setUserNeosBadge("/images/fix/neosLevel1.png");
            joinUser.setUserNeosPowerLevel(1);
            joinUser.setUserNeosPowerAbility(50);
    
            joinUser.setUserNeosPoint(0);
            joinUser.setUserChattingPoint(1000);
            joinUser.setUserIntroduce("안녕하세요 네오스인입니다.");
    
    
            User user = userDTO.toEntity();
            if(joinUser.getCollegeId() != null){
               College college =  collegeRepository.findById(userDTO.getCollegeId()).get();
                user.changeCollege(college);
            }
    
            userRepository.save(user);
        }
    
        public boolean duplicateId(String userOauthId){
            return userRepository.findAllByUserOAuthId(userOauthId).size() != 0;
        }
    
        @Transactional
        public void certify(String token){
            User user = userRepository.findByUserOAuthId(token).get();
            user.certifyOk("true");
        }
    
        public List<String> getCollegeCityList(){
            List<CollegeDTO> collegeDTOList = collegesCustomRepository.findAll();
            List<String> locations = collegeDTOList.stream().map(CollegeDTO::getCollegeCity).collect(Collectors.toList());
            HashSet<String> location = new HashSet<>(locations);
            List<String> locationFinal = location.stream().collect(Collectors.toList());
            locationFinal.sort(Comparator.naturalOrder());
            return locationFinal;
        }
    
        public List<UserCollegeMajor> getMajorList(){
            List<UserCollegeMajor> collegeMajors = List.of(인문계열,사회계열,자연계열,의약계열,공학계열,예체능계열,교육계열,IT계열,기타계열);
    //        List<String> collegeMajorList = collegeMajors.stream().map(UserCollegeMajor::toString).collect(Collectors.toList());
    //        collegeMajorList.sort(Comparator.naturalOrder());
            return collegeMajors;
        }
    
        public List<CollegeDTO> showColleges(String collegeCity){
            return collegesCustomRepository.findByCollegeCity(collegeCity);
        }
    
        public CollegeDTO showUrl(String collegeName){
            return collegesCustomRepository.findByCollegeName(collegeName);
        }
    
    }
