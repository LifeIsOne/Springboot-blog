package shop.mtcoding.blog.user;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
public class UserRepository {

    private EntityManager em;
    public UserRepository(EntityManager em) {
        this.em = em;
    }

    //  저장.INSERT.(Join)
    @Transactional      //  method를 트랜잭션 단위로 실행
    public void save (UserRequest.JoinDTO requestDTO){
        Query query = em.createNativeQuery("INSERT INTO user_tb(username, password, email) VALUES (?, ?, ?)");
        query.setParameter(1, requestDTO.getUsername());
        query.setParameter(2, requestDTO.getPassword());
        query.setParameter(3, requestDTO.getEmail());

        query.executeUpdate();
    }

    //  Model
    @Transactional
    public void saveV2 (UserRequest.JoinDTO requestDTO){
        User user = new User();
        user.setUsername(requestDTO.getUsername());
        user.setPassword(requestDTO.getPassword());
        user.setEmail(requestDTO.getEmail());

        em.persist(user);
    }

    //  조회.SELECT.(Login)
    public User findByUsernameAndPassword(UserRequest.LoginDTO requestDTO) {
        Query query = em.createNativeQuery("SELECT * FROM user_tb  WHERE username=? AND password=?", User.class);
        query.setParameter(1, requestDTO.getUsername());
        query.setParameter(2, requestDTO.getPassword());

        User user = (User) query.getSingleResult(); //  다운캐스팅
        return user;
    }
}
