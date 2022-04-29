package com.waazon.backend.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import com.waazon.backend.filters.JwtFilter;
import com.waazon.backend.services.MyUserDetails.LoginUserDetailsService;

@Service
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    private final LoginUserDetailsService loginUserDetailsService;
    private final JwtFilter jwtFilter;

    @Autowired
    public SpringSecurityConfig(LoginUserDetailsService loginUserDetailsService, JwtFilter jwtFilter) {
        this.loginUserDetailsService = loginUserDetailsService;
        this.jwtFilter = jwtFilter;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginUserDetailsService);
    }

    @Override

    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/cats/**").permitAll()
                .antMatchers("/products/**").permitAll()
                .antMatchers("/admin/sellers/**").hasAnyAuthority("ADMIN", "BUYER")
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/buyer/**").hasAuthority("BUYER")
                .antMatchers("/seller/**").hasAuthority("SELLER")
                .antMatchers("/orders/**").hasAnyAuthority("SELLER", "BUYER")
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.cors();
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.headers().frameOptions().sameOrigin();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        //return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
