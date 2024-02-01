package shop.mtcoding.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import shop.mtcoding.blog.user.User;

import javax.servlet.http.HttpSession;

@Controller
public class BoardController {

    private final HttpSession session;

    public BoardController(HttpSession session) {
        this.session = session;
    }

    @GetMapping({ "/", "/board" })
    public String index(){

        BoardRepository br = new BoardRepository();

        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null){
            System.out.println("로그인 안된 상태입니다.");
        }else {
            System.out.println("로그인 된 상태입니다.");
        }
        return "index";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    @GetMapping("/board/1")
    public String detail() {
        return "board/detail";
    }
}
