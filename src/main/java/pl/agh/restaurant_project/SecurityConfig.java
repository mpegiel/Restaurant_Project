package pl.agh.restaurant_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pl.agh.restaurant_project.domain.UserSecurity;
import pl.agh.restaurant_project.service.CustomUserDetailsService;
import pl.agh.restaurant_project.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/passwordRestoring").permitAll()
                .antMatchers("/changeofpassword").permitAll()
                .antMatchers("/reset_password_form").permitAll()
                .antMatchers("/reset_password").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/waiter/**").hasAnyRole("ADMIN", "WAITER")
                .antMatchers("/cook/**").hasAnyRole("ADMIN", "COOK")
                .antMatchers("/accountant/**").hasAnyRole("ADMIN", "ACCOUNTANT")
                .antMatchers("/users/**").hasAnyRole("ADMIN", "ACCOUNTANT")
                .antMatchers("/bill/**").hasAnyRole("ADMIN", "WAITER", "ACCOUNTANT")
                .antMatchers("/reservation/**").hasAnyRole("ADMIN", "WAITER")
                .antMatchers("/warehouse/**").hasAnyRole("ADMIN", "COOK", "ACCOUNTANT")
                .antMatchers("/menu/**").hasAnyRole("ADMIN", "WAITER","COOK")
                .antMatchers("/orders/**").hasAnyRole("ADMIN", "WAITER")

                .anyRequest().authenticated().and().formLogin().loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login");

        http.headers()
                .frameOptions().sameOrigin()
                .httpStrictTransportSecurity().disable();

        http.csrf().ignoringAntMatchers("/api/events/*");

    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(authProvider());
    }
}

