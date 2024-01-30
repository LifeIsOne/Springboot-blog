package shop.mtcoding.blog.controller;

import org.apache.catalina.realm.UserDatabaseRealm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *  CONTROLLER
 *  1. 요청받기 -> URL (URI가 포함돼 있다.)
 *  2. 데이터(http body)는 어떻게? -> DTO
 *  3. 기본 MIME 전략 : x-www-form-urlencoded (username=ssar&password=1234)
 *  4. 유효성 검사하기 (body 데이터가 있으면)
 *  5. Client가 View만 원하는지? 혹은 DB처리 후 View도 원하는지
 *  6. View만 원한다 -> View응답
 *  7. DB처리를 원한다 -> Moder(DAO)에게 위임 후 View를 응답
 */
@Controller
public class UserController {

    @GetMapping("/join")
    public String join(UserRequest.JoinDTO requestDTO){
        System.out.println(requestDTO);

        //  1. 유효성 검사
        if(requestDTO.getUsername().length() < 3){
            return "error/400";
        }

        //  2. Moder에게 위임
        
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}
