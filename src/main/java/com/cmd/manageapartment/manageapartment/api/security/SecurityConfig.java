package com.cmd.manageapartment.manageapartment.api.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private JWTAuthEntryPoint jwtAuthEntryPoint;

    private CustomUserDetailService customUserDetailService;

    @Autowired
    public SecurityConfig(CustomUserDetailService customUserDetailService, JWTAuthEntryPoint jwtAuthEntryPoint) {
        this.customUserDetailService = customUserDetailService;
        this.jwtAuthEntryPoint = jwtAuthEntryPoint;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable).exceptionHandling(exception ->{
                    exception.authenticationEntryPoint(jwtAuthEntryPoint);

                }).sessionManagement(session ->{
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .authorizeHttpRequests(auth -> {
//                    auth.requestMatchers("/api/apartments/**").permitAll();
                    auth.requestMatchers("/api/auth/**").permitAll();
                    auth.requestMatchers("/api/test").permitAll();
                    //Auth for specific role
//                    auth.requestMatchers(HttpMethod.POST, "/api/residents/**").permitAll();
                    auth.requestMatchers(HttpMethod.POST).hasAnyRole("ADMIN");
//                    auth.requestMatchers(HttpMethod.POST).permitAll(); //Just for test
                    auth.requestMatchers("/api/admin/**").hasAnyRole("ADMIN");
                    auth.requestMatchers(HttpMethod.GET).permitAll();
                    auth.requestMatchers(HttpMethod.PUT).hasAnyRole("ADMIN");
                    auth.requestMatchers(HttpMethod.POST, "/api/notifications/send-fee-notification").hasAnyRole("ADMIN");
//                    auth.requestMatchers(HttpMethod.POST).permitAll();
                    //Auth for delete

//                    auth.requestMatchers(HttpMethod.DELETE).hasAnyRole("ADMIN");
                    auth.requestMatchers(HttpMethod.DELETE).permitAll();
                    //For testing
                    auth.requestMatchers("api/residents").permitAll();
                    auth.anyRequest().authenticated();
                })
                .httpBasic(Customizer.withDefaults());
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("password")
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user = User.builder()
//                .username("user")
//                .password("password")
//                .roles("USER").build();
//
//        return new InMemoryUserDetailsManager(admin, user);
//
//    }
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();

    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter();
    }



}



