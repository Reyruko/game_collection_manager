package app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth ->
                auth.requestMatchers(
                        "/",
                        "/register",
                        "/register/success",
                        "/login",
                        "/css/**",
                        "/js/**",
                        "/images/**"
                ).permitAll()

                .requestMatchers("/admin/**")
                .hasRole("ADMIN")

                .requestMatchers("/games/**")
                .authenticated()

                .anyRequest()
                .authenticated())

                .formLogin(login ->
                        login.loginPage("/login")
                                .defaultSuccessUrl("/home", true)
                                .failureUrl("/login?error")
                                .permitAll())

                .logout(logout ->
                        logout.logoutUrl("/logout")
                                .logoutSuccessUrl("/")
                );
        return http.build();
    }
}
