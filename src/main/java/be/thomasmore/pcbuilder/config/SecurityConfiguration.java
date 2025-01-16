package be.thomasmore.pcbuilder.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import javax.sql.DataSource;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Autowired
    private DataSource dataSource;

    @Value("${security.h2-console-needed:false}")
    private boolean h2ConsoleNeeded;

    // JDBC Authentication configuration
    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager() {
        return new JdbcUserDetailsManager(dataSource);
    }

    // Password encoding using BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Security filter chain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // MVC matcher for path-based security
        HandlerMappingIntrospector introspector = new HandlerMappingIntrospector();
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(mvcMatcherBuilder.pattern("/admin/**")).hasAuthority("ADMIN") // Admin access
                .requestMatchers(mvcMatcherBuilder.pattern("/user/register")).permitAll()     // Public registration
                .requestMatchers(mvcMatcherBuilder.pattern("/user/**")).hasAnyAuthority("USER", "ADMIN") // User access
                .anyRequest().permitAll()); // Default: allow all other requests

        // Login and logout configuration
        http.formLogin(form -> form
                        .loginPage("/user/login").permitAll()) // Custom login page
                .logout(form -> form
                        .logoutUrl("/user/logout")
                        .logoutSuccessUrl("/user/login?logout").permitAll()); // Logout redirect
        http.formLogin(form -> form
                .defaultSuccessUrl("/account", true)); // Redirect to /account post-login

        // Enable H2 console access conditionally
        if (h2ConsoleNeeded) {
            http.csrf(csrf -> csrf.ignoringRequestMatchers(toH2Console())) // Disable CSRF for H2 console
                    .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)); // Allow frames
        }

        return http.build();
    }
}