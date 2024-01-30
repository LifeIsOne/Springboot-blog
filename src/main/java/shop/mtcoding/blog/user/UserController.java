package shop.mtcoding.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor    //  final이 초기화 할 필요가 없다.
@Controller
public class UserController {

    //  필드 선언문.
    private final UserRepository userRepository;    //  NULL
    private final HttpSession session;

    //  회원가입 Mapping !Post가 당연합니다!
    @PostMapping("/join")
    public String join(UserRequest.JoinDTO requestDTO){
        System.out.println(requestDTO);

        //  1. 유효성 검사 / 간단하게 username만
        if (requestDTO.getUsername().length() < 3){
            return "error/400";
        }

        //  2. Model에 위임하기
        userRepository.saveV2(requestDTO);

        return "redirect:/loginFrom";
    }

    // 로그인 Mapping
    @PostMapping("/login")
    public String login(UserRequest.LoginDTO requestDTO){
        System.out.println(requestDTO);

        //  1. 유효성 검사 / 간단하게 username만 하겠습니다.
        if (requestDTO.getUsername().length() < 3){
            return "error/400";
        }

        //  2. Model에 위임    / SELECT * FROM user_tb WHERE username=? AND password=?
        User user = userRepository.findByUsernameAndPassword(requestDTO);   // requestDTO안 username과 password가 있음.

        //  3. 응답
        if (user == null){
            return "error/401";
        }else{
            session.setAttribute("sessionUser", user);
            return "redirect:/";
        }
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
