package shop.mtcoding.blog.user;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data       // Getter, Setter 포함
@Entity     //  Entity를 찾아 Reflection. 해당필드 분석. 만듬. 실무X. 개발모드에소만 쓰기
@Table(name = "user_tb")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //  AUTO_INCREMENT
    private int id;

    @Column(unique = true)                  //  유니크
    private String username;

    @Column(length = 60, nullable = false)  //  60자이상, null 입력 금지
    private String password;
    private String email;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
