package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


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

@RequiredArgsConstructor    //  생성자를 다시 만들 필요가 없다.
@Controller
public class UserController {

    private final UserRepository userRepository;      //  NULL
    private final HttpSession session;


    @PostMapping("/login")
    public String login(UserRequest.LoginDTO requestDTO){
        System.out.println(requestDTO);

        //  1. 유효성 검사
        if(requestDTO.getUsername().length() < 3){
            return "error/400";
        }

        //  2. model 연결 / SELECT * FROM user_tb WHERE username=? AND password=?
        User user = userRepository.findByUsernameAndPassword(requestDTO);   //  안에 username, password 들어있음

        //  3. 응답
        if (user == null){
            return "error/401";
        }else{
            session.setAttribute("sessionUser", user);
            return "redirect:/";
        }

//        System.out.println(user);
//        System.out.println("로그인");

    }

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO requestDTO){
        System.out.println(requestDTO);

        //  1. 유효성 검사
        if(requestDTO.getUsername().length() < 3){
            return "error/400";
        }

        //  2. 동일 username체크
        User user = userRepository.findByUsername(requestDTO.getUsername());
        if (user == null){  //  중복이 안됐다.
            userRepository.save(requestDTO);
        }else{
            return "error/400";
        }

        //  3. Model에게 위임하기
        userRepository.saveV2(requestDTO);

        return "redirect:/loginForm";
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
        session.invalidate();   //  Server쪽 session을 날림. Client는 key를 들고있음.
        return "redirect:/";
    }
}