package shop.mtcoding.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class UserController {

    //  필드 선언문.
    private final UserRepository userRepository;    //  NULL
    private final HttpSession session;

    //  회원가입 mapping
    @GetMapping("/join")
    public String login(UserRequest.JoinDTO requestDTO){
        System.out.println(requestDTO);

        //  1. 유효성 검사 / 간단하게 username만 하겠습니다.
        if (requestDTO.getUsername().length() < 3){
            return "error/400";
        }

        //  2. Model에 위임하기
        userRepository.save

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
