package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog._core.config.security.MyLoginUser;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final HttpSession session;
    private final BoardRepository boardRepository;

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable int id, BoardRequest.UpdateDTO requestDTO, @AuthenticationPrincipal MyLoginUser myLoginUser){

        //  2. 권환 확인
        Board board = boardRepository.findById(id);
        if (board.getUserId() != myLoginUser.getUser().getId()){
            return "error/403";
        }
        //  3. 핵심 로직
        //  update board_tb set title = ?, content = ? where id = ?;
        boardRepository.update(requestDTO, id);

        return "redirect:/board/"+id;
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, HttpServletRequest request, @AuthenticationPrincipal MyLoginUser myLoginUser){

        //  권한 확인


        //  Model위임 id로 board를 조회
        Board board = boardRepository.findById(id);
        if(board.getUserId() != myLoginUser.getUser().getId()){
            return "error/403";
        }

        //  가방에 담기
        request.setAttribute("board",board);

        return "board/updateForm";
    }

    @GetMapping({ "/", "/board" })
    public String index(HttpServletRequest request) {

        List<Board> boardList = boardRepository.findAll();
        request.setAttribute("boardList", boardList);

        return "index";
    }

    @PostMapping("/board/{id}/delete")
    public String delete (@PathVariable int id, HttpServletRequest request, @AuthenticationPrincipal MyLoginUser myLoginUser){

        //  2. 권한 X
        Board board = boardRepository.findById(id);
        if (board.getUserId() != myLoginUser.getUser().getId()){
            request.setAttribute("status", 403);
            request.setAttribute("msg", "권한이 없습니다.");
            return "error/40x";
        }

        boardRepository.deleteById(id);

        return "redirect:/";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO requestDTO, HttpServletRequest request, @AuthenticationPrincipal MyLoginUser myLoginUser){


        //  1. body data 받기
        System.out.println(requestDTO);

        if (requestDTO.getTitle().length() > 30){
            request.setAttribute("status", 400);
            request.setAttribute("msg", "글 제목의 길이가 30자를 초과해서는 안돼");
            return "error/40x";     //  BadRequest : 잘못된 요청
        }

        //  3. Model 위임
        //  INSERT INTO board_tb(title, content, user_idm created_at) VALUES(?,?,?,now());
        boardRepository.save(requestDTO, myLoginUser.getUser().getId());   //  title과 content뿐, 나머지는 session에서 가져오기

        return "redirect:/";
    }

    //  게시글 작성
    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable int id, HttpServletRequest request, @AuthenticationPrincipal MyLoginUser myLoginUser) {
        //  1. Model 진입 - 상세보기 데이터 가져오기
        BoardResponse.DetailDTO responseDTO = boardRepository.findByIdWithUser(id);


        boolean pageOwner;
        if(myLoginUser == null){
            pageOwner = false;
        }else{
            int boardUserId = responseDTO.getUserId();
            int sessionUserId = myLoginUser.getUser().getId();
            pageOwner = boardUserId == sessionUserId;
        }

        request.setAttribute("board", responseDTO);
        request.setAttribute("pageOwner", pageOwner);
        return "board/detail";
    }
}