package shop.mtcoding.blog.user;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_tb")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //  AUTO_INCREMENT
    private int id;
    private String username;
    private String password;
    private String email;

}
