package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    public List<Board> findAll(){
        Query query = em.createNativeQuery("SELECT * FROM board_tb ORDER BY id DESC", Board.class);
        return query.getResultList();
    }

    public BoardResponse.DetailDTO findById(int idx) {
        Query query = em.createNativeQuery("SELECT b.id, b.title, b.content, b.user_id, u.username FROM board_tb b INNER JOIN user_tb u ON b.user_id = u.id WHERE b.id = ?");
        query.setParameter(1, idx);

        Object[] row = (Object[]) query.getSingleResult();

        Integer id = (Integer) row[0];
        String title = (String) row[1];
        String content = (String) row[2];
        int userId = (Integer) row[3];
        String username = (String) row[4];

        System.out.println("id : "+id);
        System.out.println("title : "+title);
        System.out.println("content : "+content);
        System.out.println("userId : "+userId);
        System.out.println("username : "+username);

        BoardResponse.DetailDTO responseDTO = new BoardResponse.DetailDTO();
        responseDTO.setId(id);
        responseDTO.setTitle(title);
        responseDTO.setContent(content);
        responseDTO.setUserId(userId);
        responseDTO.setUsername(username);

        return responseDTO;
    }

    public void save(BoardRequest.SaveDTO requestDTO, int userId) {
        Query query = em.createNativeQuery("INSERT INTO board_tb(title, content, user_idm created_at) VALUES(?,?,?,now());");
        query.setParameter(1,requestDTO.getTitle());
        query.setParameter(2,requestDTO.getContent());
        query.setParameter(3,userId);

        query.executeUpdate();
    }
}