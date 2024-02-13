package shop.mtcoding.blog._core.config.security;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog.user.User;
import shop.mtcoding.blog.user.UserRepository;

//  때려지는 조건. ·POST ·/login ·x-www-form-urlencoded ·키값이 username, password
@RequiredArgsConstructor
@Service
public class MyLoginService implements UserDetailsService {

    private final UserRepository userRepository;
    private final HttpSession session;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

        System.out.println("loadUserByUsername : " + username);
        User user = userRepository.findByUsername(username);

        if (user ==null){
            System.out.println("user는 null입니다.");
            return null;
        }else{
            System.out.println("user를 찾았습니다.");
            session.setAttribute("sessionUser",user);   //  ※ mustache 용도로만
            return new MyLoginUser(user);   //  SercurityContexHolder 여기 저장
        }
    }
}
