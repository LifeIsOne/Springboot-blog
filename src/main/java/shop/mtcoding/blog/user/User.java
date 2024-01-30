package shop.mtcoding.blog.user;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table (name="user_tb")
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (unique = true)
    private String username;

    @Column(length = 30, nullable = false)
    private String password;
    private String email;
}
