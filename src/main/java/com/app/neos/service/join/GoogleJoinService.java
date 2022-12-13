package com.app.neos.service.join;

import com.app.neos.oauth.GoogleOAuthRequest;
import com.app.neos.oauth.GoogleOAuthResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class GoogleJoinService {

    public ArrayList<String> getGoogleAccessTokenInfo(String authCode) throws Exception{

        try {
            //HTTP Request를 위한 RestTemplate

            RestTemplate restTemplate = new RestTemplate();


            //Google OAuth Access Token 요청을 위한 파라미터 세팅

            GoogleOAuthRequest googleOAuthRequestParam =  new GoogleOAuthRequest();

            googleOAuthRequestParam.setClientId("514426667105-1lnfsvd1qiivml6ajencglio5lpvct8d.apps.googleusercontent.com");

            googleOAuthRequestParam.setClientSecret("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");

            googleOAuthRequestParam.setCode(authCode);

            googleOAuthRequestParam.setRedirectUri("http://localhost:10718/join/google");

            googleOAuthRequestParam.setGrantType("authorization_code");


            //JSON 파싱을 위한 기본값 세팅

            //요청시 파라미터는 스네이크 케이스로 세팅되므로 Object mapper에 미리 설정해준다.

            ObjectMapper mapper = new ObjectMapper();

            mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);


            //AccessToken 발급 요청

            ResponseEntity<String> resultEntity = restTemplate.postForEntity("https://oauth2.googleapis.com/token", googleOAuthRequestParam, String.class);


            //Token Request

            GoogleOAuthResponse result = mapper.readValue(resultEntity.getBody(), new TypeReference<GoogleOAuthResponse>() {

            });


            //ID Token만 추출 (사용자의 정보는 jwt로 인코딩 되어있다)

            String jwtToken = result.getIdToken();

            String requestUrl = UriComponentsBuilder.fromHttpUrl("https://oauth2.googleapis.com/tokeninfo").queryParam("id_token", jwtToken).toUriString();


            String resultJson = restTemplate.getForObject(requestUrl, String.class);


            Map<String,String> userInfo = mapper.readValue(resultJson, new TypeReference<Map<String, String>>(){});

            log.info("userinfo:"+userInfo);

            ArrayList<String> userInfos = new ArrayList<>();

            String email=userInfo.get("email");
            String nickname=userInfo.get("name");
            String userImage=userInfo.get("picture");
            String userOauthId=userInfo.get("sub");

            userInfos.add(email);
            userInfos.add(nickname);
            userInfos.add(userImage);
            userInfos.add(userOauthId);

            return userInfos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String loginInfo(String authCode) throws Exception{

        try {
            //HTTP Request를 위한 RestTemplate

            RestTemplate restTemplate = new RestTemplate();


            //Google OAuth Access Token 요청을 위한 파라미터 세팅

            GoogleOAuthRequest googleOAuthRequestParam =  new GoogleOAuthRequest();

            googleOAuthRequestParam.setClientId("514426667105-1lnfsvd1qiivml6ajencglio5lpvct8d.apps.googleusercontent.com");

            googleOAuthRequestParam.setClientSecret("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");

            googleOAuthRequestParam.setCode(authCode);

            googleOAuthRequestParam.setRedirectUri("http://localhost:10718/login/google");

            googleOAuthRequestParam.setGrantType("authorization_code");


            //JSON 파싱을 위한 기본값 세팅

            //요청시 파라미터는 스네이크 케이스로 세팅되므로 Object mapper에 미리 설정해준다.

            ObjectMapper mapper = new ObjectMapper();

            mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);


            //AccessToken 발급 요청

            ResponseEntity<String> resultEntity = restTemplate.postForEntity("https://oauth2.googleapis.com/token", googleOAuthRequestParam, String.class);


            //Token Request

            GoogleOAuthResponse result = mapper.readValue(resultEntity.getBody(), new TypeReference<GoogleOAuthResponse>() {

            });


            //ID Token만 추출 (사용자의 정보는 jwt로 인코딩 되어있다)

            String jwtToken = result.getIdToken();

            String requestUrl = UriComponentsBuilder.fromHttpUrl("https://oauth2.googleapis.com/tokeninfo").queryParam("id_token", jwtToken).toUriString();


            String resultJson = restTemplate.getForObject(requestUrl, String.class);


            Map<String,String> userInfo = mapper.readValue(resultJson, new TypeReference<Map<String, String>>(){});

            log.info("userinfo:"+userInfo);




            String userOauthId=userInfo.get("sub");

            return userOauthId;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }




}
