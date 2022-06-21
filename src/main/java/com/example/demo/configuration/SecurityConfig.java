package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // Lazy to prevent circular dependency
    @Autowired
    @Lazy
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/registration**",
                        "/home**",
                        "/books/**/**",
                        "/forgot_password**",
                        "/reset_password**",
                        "/js/**",
                        "/css/**",
                        "/img/**")

                .permitAll()
                .anyRequest()
                .authenticated()
                // .and()
                // .oauth2Login()
                .and()
                .formLogin()
                .loginPage("/signin")
                .permitAll()
                .defaultSuccessUrl("/home", true)
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/signin?logout")
                .permitAll()
                .and()
                .csrf().disable().cors();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // public void onAuthenticationSuccess(HttpServletRequest request,
    // HttpServletResponse response,
    // Authentication authentication) throws IOException, ServletException {

    // String queryString = request.getQueryString();
    // if (queryString == null) {
    // response.setStatus(200);
    // } else if (!queryString.contains("redirectUrl=")) {
    // response.sendRedirect("/home");
    // } else {
    // queryString = URLDecoder.decode(queryString.replace("url=", ""), "utf-8");
    // response.sendRedirect(queryString);
    // }
    // }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
}
