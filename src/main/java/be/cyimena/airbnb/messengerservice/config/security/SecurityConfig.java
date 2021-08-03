package be.cyimena.airbnb.messengerservice.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/stomp").permitAll() // websocket authorization
                // messages
                .antMatchers(HttpMethod.GET, "/api/v1/messenger/messages").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/messenger/messages").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/messenger/messages/by/conversations/{id}").permitAll()
                // participations
                .antMatchers(HttpMethod.GET, "/api/v1/messenger/messages/by/participations").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/messenger/messages/by/participations").permitAll()
                // conversations
                .antMatchers(HttpMethod.GET, "/api/v1/messenger/conversations/{id}").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/messenger/conversations/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/messenger/conversations/by/participations/{id}").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/messenger/conversations/by/participations/{id}").permitAll()
                // notifications
                .antMatchers(HttpMethod.GET, "/api/v1/messenger/notifications/by/users/{id}").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/messenger/notifications").permitAll()
                .anyRequest().authenticated();
    }

    /**
     * Apply CORS configuration before Spring Security.
     * By default, "http.cors" take a bean called corsConfigurationSource.
     * @implNote https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#cors
     * @return a CORS configuration source.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
