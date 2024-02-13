package shop.mtcoding.blog._core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration  //  컨퍼넌트 스캔 - 메모리에 안뜨니까 시켜줘야 함
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();     //  IoC등록, Security가 로그인할 때 어떤 Hash로 비교해야 하는지 인지함
    }

    @Bean
    public WebSecurityCustomizer ignore(){  //  매번 바뀜.. 외울 수 없으니 이해해 복붙 / 정적 자원. security filter제외시키기
        return w -> w.ignoring().requestMatchers("/static/**","/h2-console/**");
    }

    @Bean   //  메모리에 안뜸 - Bean해줌
    SecurityFilterChain configure(HttpSecurity http) throws Exception {    //   HttpSecurity 외우셈, 이해하는게 아님

        http.csrf(c -> c.disable());

        http.authorizeHttpRequests(authorize -> {

            authorize.requestMatchers(RegexRequestMatcher.regexMatcher("/board/\\d+")).permitAll() //  허용을 먼저 해준 후, 제한걸기
                    .requestMatchers("/user/**","/board/**").authenticated()
                    .anyRequest().permitAll();

        });

        http.formLogin(f ->{    //  httpSecurityFormLoginConfigurer
            f.loginPage("/loginForm").loginProcessingUrl("/login").defaultSuccessUrl("/").failureForwardUrl("/loginForm");
        });

        return http.build();
    }
}
