package shop.mtcoding.blog.user;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Table(name = "user_tb")
@Data       //  Getter, Setter 포함
@Entity     //  Entity를 찾아 Reflection. 해당필드 분석. 만듬. 실무X. 개발모드에소만 쓰기
public class User {
    @Id     //  Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //  AUTO_INCREMENT
    private int id;
    private String username;
    private String password;
    private String email;

    //  카멜 표기법으로 만들면 DB는 언더스코어 기법, created_at으로 만들어진다.
    private LocalDateTime createdAt;
}
