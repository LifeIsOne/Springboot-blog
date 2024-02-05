package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    public List<Board> findAll(String title){
        Query query = em.createNativeQuery("select * from board_tb order by id desc", Board.class );
        query.setParameter(1,"%"+title+"%");
        return query.getResultList();


    public BoardResponse.DetailDTO findById(int idx){
        Query query = em.createNativeQuery("select b.id, b.title, b.content,");
        query.setParameter(1, idx);

        Object[] row = (Object[]) query.getSingleResult();

        Integer id = (Integer) row[0];
        String title = (String) row[1];
        String content = (String) row[2];
        int userId = (Integer) row[3];

        System.out.println("idx = " + idx);

    }
}
