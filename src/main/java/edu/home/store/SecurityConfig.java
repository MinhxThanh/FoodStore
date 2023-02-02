package edu.home.store;

import edu.home.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserService userService;

    @Bean
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
        System.out.println("userDetailsService: " + auth.userDetailsService(userService));
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

//        authorize requests
        http.authorizeRequests()
                .antMatchers("/order/**").authenticated()
                .anyRequest().permitAll();
//        login form
        http.formLogin()
                .loginPage("/security/login/form")
                .loginProcessingUrl("/security/login")
                .defaultSuccessUrl("/security/login/success", false)
                .failureUrl("/security/login/error");
//        remember me
        http.rememberMe()
                .userDetailsService(userService)
                .tokenValiditySeconds(7 * 24 * 60 * 60) // expiration time: 7 days
                .key("AbcdefghiJklmNoPqRstUvXyz");
//        wrong role or controller fail
        http.exceptionHandling()
                .accessDeniedPage("/security/unauthorized");
//       logout
        http.logout()
                .logoutUrl("/security/logout")
                .logoutSuccessUrl("/security/logout/success")
                .deleteCookies("JSESSIONID", "remember-me").invalidateHttpSession(true);

//        OAuth2
        http.oauth2Login()
                .loginPage("/security/login/form")
                .defaultSuccessUrl("/security/oauth2Login/success", true)
                .failureUrl("/security/login/error")
                .authorizationEndpoint()
                .baseUri("/oauth2/authorization");

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() throws Exception {
        return web -> web.ignoring().antMatchers(HttpMethod.OPTIONS, "/assets/**");
    }
}
