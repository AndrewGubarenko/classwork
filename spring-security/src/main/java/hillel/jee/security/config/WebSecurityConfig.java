package hillel.jee.security.config;

import static hillel.jee.security.domain.Role.RoleName.*;

import hillel.jee.security.domain.Role.RoleName;
import hillel.jee.security.services.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  AppUserDetailsService userDetailsService;

  @Value("${bcrypt.strength:20}")
  int bcryptStrength;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.debug(true);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
          .antMatchers("/css/**", "/js/**", "/about", "/register").permitAll()
          .antMatchers("/admin/**", "/h2-console/**").hasRole(ADMIN.shorten())
          .antMatchers("/protected").hasAnyRole(
              ADMIN.shorten(), MODERATOR.shorten())
          .anyRequest().authenticated()
        .and()
        .formLogin()
          .loginPage("/login").permitAll()
          .failureForwardUrl("/access-denied")
        .and()
        .logout().permitAll()
        .and()
        .exceptionHandling().accessDeniedPage("/access-denied")
        .and()
        .csrf().disable()
        .cors().disable()
        .headers().frameOptions().sameOrigin()
    ;
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(bcryptStrength);
  }

  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}
