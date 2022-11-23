package com.example.neosproject.controller.join;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/join/*")
public class JoinController {

    /*회원가입 모달 */
    @GetMapping("/join")
    public String join(){
        return "app/loginAndJoin/join";
    }


    /*회원 가입 페이지*/
    @GetMapping("/join-page-details")
    public String joinPage(){
        return "app/loginAndJoin/joinPage";
    }

    /*이용약관*/
    @GetMapping("/use-clause-list")
    public String edragonClause(){
        return "app/loginAndJoin/edragonClause";
    }

    /*전자 금융 약관*/
    @GetMapping("/electronic-finance-clause-list")
    public String electronicFinanceClause(){
        return "app/loginAndJoin/electronicFinanceClause";
    }

    /*개인 정보 약관 */
    @GetMapping("/individual-clause-list")
    public String individualClause(){
        return "app/loginAndJoin/individualClause";
    }
}
