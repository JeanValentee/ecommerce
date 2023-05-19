package gbd.ecommerce.config;

import gbd.ecommerce.services.user.LoadUserByUsername;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoadUserByUsername loadUserByUsername;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/resources/**", "/plugins/**").permitAll()
                .antMatchers("/", "/signup").anonymous()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/").defaultSuccessUrl("/home", true)
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authentication) throws Exception {
        authentication.userDetailsService(loadUserByUsername).passwordEncoder(new BCryptPasswordEncoder());
    }
}
