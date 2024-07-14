package org.SWP391_FUHL_SE1825.FDMS.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(request -> request.requestMatchers("/images/**","/img/**","/js/**","/landing", "/css/**", "/Content/**", "/fonts/**", "/vendor/**", "/public-api/**")
                .permitAll()
                .requestMatchers("/admin/**","/manager/**","/add","/create","/delete","/active")
                .hasAuthority("ROLE_ADMIN")
                .requestMatchers("/manager/**")
                .hasAuthority("ROLE_MANAGER")
                .anyRequest()
                .authenticated()
        );
        http.formLogin(form -> form.loginPage("/login")
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error=true")
                .permitAll()
        );
        http.logout(form -> form.logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .clearAuthentication(true).permitAll()
        );
        http.csrf(AbstractHttpConfigurer::disable);
        http.exceptionHandling(customizer -> customizer
                .accessDeniedHandler(accessDeniedHandler));
        return http.build();

    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
