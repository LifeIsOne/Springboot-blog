package shop.mtcoding.blog.board;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Table(name = "board_tb")
@Data
@Entity     //  Table 생성하기 위해 필요한 어노테이션
public class Board {    //  User 1 -> board

    @Id     //  Primary key 설정
    @GeneratedValue     // auto_increment 전략
    private int id;
    private String title;
    private String content;

    private int userId; //  Table이 만들어질 때 user_id

    private LocalDateTime createdAt;
}
