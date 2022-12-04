package com.app.neos.service.join;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoogleService {

    public String[] getGoogleAccessToken(String code){
        String access_Token="";
        String refresh_Token ="";
        String reqURL = "https://oauth2.googleapis.com/token";

        try{
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=514426667105-1lnfsvd1qiivml6ajencglio5lpvct8d.apps.googleusercontent.com"); // 구글 클라이언트 아이디
            sb.append("&client_secret=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"); // 구글 시크릿 키
            sb.append("&redirect_uri=http://localhost:10718/join/google"); // TODO 인가코드 받은 redirect_uri 입력
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            log.info("responseCode : " + responseCode);
            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            log.info("response body : " + result);

            //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
//            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            log.info("access_token : " + access_Token);
//            log.info("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return new String[]{access_Token,refresh_Token};
    }

    public String[] getGoogleLoginAccessToken(String code){
        String access_Token="";
        String token_ID ="";
        String reqURL = "https://oauth2.googleapis.com/token";

        try{
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=514426667105-1lnfsvd1qiivml6ajencglio5lpvct8d.apps.googleusercontent.com"); // 구글 클라이언트 아이디
            sb.append("&client_secret=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"); // 구글 시크릿 키
            sb.append("&redirect_uri=http://localhost:10718/login/google"); // TODO 인가코드 받은 redirect_uri 입력
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            log.info("responseCode : " + responseCode);
            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            log.info("response body : " + result);

            //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
//            token_ID = element.getAsJsonObject().get("id_token").getAsString();
//            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            log.info("access_token : " + access_Token);

            br.close();
            bw.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return new String[]{access_Token,token_ID};
    }

    public String[] getGoogleRefreshAccessToken(String refreshToken){
        String access_Token="";
        String token_ID ="";
        String reqURL = "https://oauth2.googleapis.com/token";

        try{
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=refresh_token");
            sb.append("&client_id=514426667105-1lnfsvd1qiivml6ajencglio5lpvct8d.apps.googleusercontent.com"); // 구글 클라이언트 아이디
            sb.append("&client_secret=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"); // 구글 시크릿 키
            sb.append("&refresh_token=" + refreshToken);
            bw.write(sb.toString());
            bw.flush();

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            log.info("responseCode : " + responseCode);
            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            log.info("response body : " + result);

            //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
//            token_ID = element.getAsJsonObject().get("id_token").getAsString();
//            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            log.info("access_token : " + access_Token);

            br.close();
            bw.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return new String[]{access_Token,token_ID};
    }


    public void googleProfile(String token) throws Exception {
//        ?access_token=
        String reqURL = "https://www.googleapis.com/oauth2/v2/userinfo";

        //access_token을 이용하여 사용자 정보 조회
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            log.info("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            log.info("response body : " + result);

            //Gson 라이브러리로 JSON파싱
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);


            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public String getGoogleIdByToken(String token) throws Exception{
        String reqURL = "https://www.googleapis.com/oauth2/v2/userinfo";
        String id= null;
        //access_token을 이용하여 사용자 정보 조회
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            log.info("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            //Gson 라이브러리로 JSON파싱
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            id = element.getAsJsonObject().get("id").getAsString();

            br.close();
            return id;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }

    public String getGoogleEmailByToken(String token) throws Exception{
        String reqURL = "https://www.googleapis.com/oauth2/v2/userinfo";
        String email= null;
        //access_token을 이용하여 사용자 정보 조회
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            log.info("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            //Gson 라이브러리로 JSON파싱
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            email = element.getAsJsonObject().get("email").getAsString();

            br.close();
            return email;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return email;
    }

    public String getGoogleNameByToken(String token) throws Exception{
        String reqURL = "https://localhost:10718.my.salesforce.com/services/oauth2/revoke?token="+token;
        String name= null;
        //access_token을 이용하여 사용자 정보 조회
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            log.info("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            //Gson 라이브러리로 JSON파싱
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            name = element.getAsJsonObject().get("name").getAsString();

            br.close();
            return name;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }

    public String getGoogleProfileImgByToken(String token) throws Exception{
        String reqURL = "https://www.googleapis.com/oauth2/v2/userinfo";
        String img= null;
        //access_token을 이용하여 사용자 정보 조회
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            log.info("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            //Gson 라이브러리로 JSON파싱
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            img = element.getAsJsonObject().get("picture").getAsString();

            br.close();
            return img;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public void logoutGoogle(String token){
        String reqURL = "https://oauth2.googleapis.com/revoke?token="+token;
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-type","application/x-www-form-urlencoded");
            int responseCode = conn.getResponseCode();
            log.info("responseCode : " + responseCode);

            if(responseCode ==400)
                throw new RuntimeException("구글 로그아웃 도중 오류 발생");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String br_line = "";
            String result = "";
            while ((br_line = br.readLine()) != null) {
                result += br_line;
            }
            log.info("결과");
            log.info(result);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

}
