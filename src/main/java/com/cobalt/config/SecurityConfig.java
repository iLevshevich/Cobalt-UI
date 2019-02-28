package com.cobalt.config;

import com.cobalt.helpers.UserHelper;
import com.cobalt.helpers.xml.entities.User;
import com.cobalt.helpers.xml.entities.Users;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String REALM_NAME = "COBALT_REALM";

    private final UserHelper helper;

    @Autowired
    public SecurityConfig(@NonNull final UserHelper helper) {
        this.helper = helper;
    }

    @Override
    protected void configure(@NonNull final AuthenticationManagerBuilder auth) throws Exception {
        final Users users = helper.getUsers();
        Objects.requireNonNull(users);

        for (final User user : users.getUser()) {
            auth.inMemoryAuthentication()
                    .withUser(user.getName())
                    .password(passwordEncoder().encode(user.getPassword()))
                    .roles(user.getRole());
        }
    }

    @Override
    protected void configure(@NonNull final HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .ignoringAntMatchers("/management/**", "/api/**")
                .csrfTokenRepository(withHttpOnly());

        http
                .authorizeRequests()
                .antMatchers("/").anonymous()
                .antMatchers("/management/**").hasAnyRole("ADMIN")
                .antMatchers("/api/**").hasAnyRole("ADMIN", "USER")
                .and()
                .httpBasic().realmName(REALM_NAME)
                .authenticationEntryPoint(new BasicAuthenticationEntryPoint() {
                    @Override
                    public void commence(@NonNull final HttpServletRequest request,
                                         @NonNull final HttpServletResponse response,
                                         @NonNull final AuthenticationException authException) throws IOException, ServletException {
                        {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.addHeader("WWW-Authenticate", "x-Basic realm=\"Client ID and secret did not authenticate.\"");
                        }
                        final PrintWriter writer = response.getWriter();
                        writer.println("HTTP Status 401 : " + authException.getMessage());
                    }

                    @Override
                    public void afterPropertiesSet() throws Exception {
                        {
                            setRealmName(REALM_NAME);
                        }
                        super.afterPropertiesSet();
                    }
                })
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private CookieCsrfTokenRepository withHttpOnly() {
        final CookieCsrfTokenRepository result = new CookieCsrfTokenRepository();
        Objects.requireNonNull(result);
        {
            result.setCookieHttpOnly(true);
        }
        return result;
    }
}
