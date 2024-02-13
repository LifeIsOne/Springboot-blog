package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RequiredArgsConstructor // final이 붙은 애들에 대한 생성자를 만들어줌
@Controller
public class UserController {

    // 자바는 final 변수는 반드시 초기화가 되어야함.
    private final UserRepository userRepository;
    private final HttpSession session;

    @PostMapping("/user/{id}/update")
    public String update(@PathVariable int id, UserRequest.UpdateDTO requestDTO){

        System.out.println(requestDTO);
        //  인증 확인
        //  권한 확인
        //  Model위임 id로 user를 조회
        //  가방에 담기
        userRepository.update(requestDTO, id);

        return "redirect:/{id}";
    }
    @GetMapping("/user/{id}/updateForm")
    public String updateForm(@PathVariable int id, UserRequest.UpdateDTO requestDTO) {
        System.out.println("아싸");

        //  인증 확인

        //  권한 확인


        //  Model위임 id로 user를 조회

        //  가방에 담기
        return "user/updateForm/{id}";
    }


    // 왜 조회인데 post임? 민간함 정보는 body로 보낸다.
    // 로그인만 예외로 select인데 post 사용
    // select * from user_tb where username=? and password=?

//    @PostMapping("/login")
//    public String login(UserRequest.LoginDTO requestDTO){
//
//
//        System.out.println(requestDTO); // toString -> @Data
//
//        if(requestDTO.getUsername().length() < 3){
//            return "error/400"; // ViewResolver 설정이 되어 있음. (앞 경로, 뒤 경로)
//        }
//
//        User user = userRepository.findByUsernameAndPassword(requestDTO);
//
//        if(user == null){ // 조회 안됨 (401)
//            return "error/401";
//        }else{ // 조회 됐음 (인증됨)
//            session.setAttribute("sessionUser", user); // 락카에 담음 (StateFul)
//        }
//
//        return "redirect:/"; // 컨트롤러가 존재하면 무조건 redirect 외우기
//    }
//    @GetMapping("/loginForm")
//    public String loginForm() {
//    return "user/loginForm";
//}

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO requestDTO){
        System.out.println(requestDTO);

        userRepository.save(requestDTO); // 모델에 위임하기
        return "redirect:/loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }





    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}