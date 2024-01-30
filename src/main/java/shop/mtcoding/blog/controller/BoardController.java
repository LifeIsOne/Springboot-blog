package shop.mtcoding.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class BoardController {

    private final HttpSession session;  //  final하면 초기화 해줘야 겠죠

    public BoardController(HttpSession session) {
        this.session = session;
    }

    @GetMapping({ "/", "/board" })
    public String index() {

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
