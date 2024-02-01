package shop.mtcoding.blog.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import shop.mtcoding.blog.board.Board;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    public List<Board> findAll(){
        Query query = em.createNativeQuery("select * from board_tb order by id desc", Board.class);

        List<Board> boardList = query.getResultList();
        return boardList;
    }
}
