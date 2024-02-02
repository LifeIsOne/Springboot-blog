package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository //  자동으로 객체생성
public class UserRepository {

    private EntityManager em;   //  컴포지션해서 적어

    //  얘를 생성해야함. Default 생서자가 없잖아. 생성할 때마다 파라메터 넣어야 함.
    //  Dependency Injection(의존성 주입)
    public UserRepository(EntityManager em){
        this.em = em;
    }

    @Transactional  // 트랜잭션 설정.
    public void save (UserRequest.JoinDTO requestDTO){
        Query query = em.createNativeQuery("INSERT INTO user_tb(username, password, email) VALUES (?, ?, ?)");
        query.setParameter(1, requestDTO.getUsername());
        query.setParameter(2, requestDTO.getPassword());
        query.setParameter(3, requestDTO.getEmail());

        query.executeUpdate();
    }

    @Transactional  //  model
    public void saveV2 (UserRequest.JoinDTO requestDTO){
        User user = new User();
        user.setUsername(requestDTO.getUsername());
        user.setPassword(requestDTO.getPassword());
        user.setEmail(requestDTO.getEmail());

        em.persist(user);
    }

    public User findByUsernameAndPassword(UserRequest.LoginDTO requestDTO) {
        Query query = em.createNativeQuery("SELECT * FROM user_tb  WHERE username=? AND password=?", User.class);
        query.setParameter(1, requestDTO.getUsername());
        query.setParameter(2, requestDTO.getPassword());

        try{
            User user = (User) query.getSingleResult();
            return user;
        }catch (Exception e){
            return null;
        }

    }

    public User findByUsername(String username) {
        Query query = em.createNativeQuery("SELECT * FROM user_tb  WHERE username=?", User.class);
        query.setParameter(1, username);

        try{
            User user = (User) query.getSingleResult();
            return user;
        }catch (Exception e){
            return null;
        }
    }
}
