package com.fine.hug.config;

import com.fine.hug.config.auth.PrincipalDetailsService;
import com.fine.hug.config.jwt.JwtAuthenticationFilter;
import com.fine.hug.config.jwt.JwtAuthorizationFilter;
import com.fine.hug.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PrincipalDetailsService principalDetailsService;
    private final CorsFilter corsFilter;
    private final UserRepository userRepository;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception { // 유저 인증정보를 가지고 있음
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    private static final String[] PUBLIC = new String[] {
            "/error", "/login", "/logout", "/api/v1/createUserBasic", "/api/v1/createUserDoctor", "/"
    };

    @Override
    public void configure(WebSecurity web) throws Exception {
        // swagger 관련 리소스 시큐리티 필터 제거
        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**");
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations()); //스프링 부트가 제공하는 static 리소스들의 기본위치를 다 가져와서 스프링 시큐리티에서 제외
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
//                .addFilterBefore(new MyFilter1(), SecurityContextPersistenceFilter.class)
                .addFilter(corsFilter) //@CrossOrigin(인증X), 시큐리티 필터에 등록 인증(O)
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository))
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //세션 사용 X
                .and()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers(PUBLIC).permitAll()
                .antMatchers("/api/v1/admin/**")
                .access("hasRole('ADMIN')")
                .antMatchers("/api/v1/user/**")
                .access("hasRole('BASIC')")
                .antMatchers("/api/v1/doctor/**")
                .access("hasRole('DOCTOR')")
                .anyRequest().authenticated()
                .and()
                .formLogin().disable()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }

}
