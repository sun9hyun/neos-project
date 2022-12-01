package com.app.neos.service.myPage;

import com.app.neos.domain.store.StoreDTO;
import com.app.neos.entity.store.Store;
import com.app.neos.entity.user.User;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.type.store.StoreStatus;
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
public class MyPageServiceTest {

}
