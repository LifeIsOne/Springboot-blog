package shop.mtcoding.blog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final HttpSession session;
    private final BoardRepository boardRepository;  //  의존성 주입

    //  http://localhost:8080?page=0 (int page 가 파싱)
    @GetMapping({ "/", "/board" })  //  @Data 반드시 필요
    public String index(HttpServletRequest request, @RequestParam(defaultValue = "0") int page)  { // ※모르겠음

        //  위임
        List<Board> boardList = boardRepository.findAll(page);
        request.setAttribute("boardList", boardList);

        int currentPage = page;
        int nextPage = currentPage+1;
        int PrevPage = currentPage-1;
        request.setAttribute("nextPage", nextPage);
        request.setAttribute("prevPage", PrevPage);

        boolean first = currentPage == 0 ? true : false;
        boolean last = true;

        int totalCount = 7;
//        int paging = 3;
//        int totalPage = 2;


        request.setAttribute("first", first);
        request.setAttribute("last", last);

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
