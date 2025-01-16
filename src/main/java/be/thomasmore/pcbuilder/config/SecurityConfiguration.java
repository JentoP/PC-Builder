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
//    dataSource(dataSource): we geven de default datasource voor onze applicatie door als datasouce
//    Noot: een datasource is een object dat gebruikt wordt om te connecteren met een database
    private DataSource dataSource;


    @Value("${security.h2-console-needed}")
    private boolean h2ConsoleNeeded;


    //    Functie jdbcUserDetailsManager: hiermee zeggen we Spring dat we jdbc
//    Authentication willen gebruiken. Dat betekent dat Spring de users en passwords uit
//    de database zal halen
//    Noot: jdbc = java database connectivity
    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager() {
        //        userDetailsManager.setUsersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?");
//        userDetailsManager.setAuthoritiesByUsernameQuery("SELECT username, authority FROM authorities WHERE username = ?");
        return new JdbcUserDetailsManager(dataSource);
    }

    //    Functie passwordEncoder: hiermee geven we aan welke soort encrypte we willen
//    gebruiken om de passwoorden op te slaan in de database. Als je dit niet doet dan zal het login proces niet werken.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        HandlerMappingIntrospector introspector = new HandlerMappingIntrospector();
        MvcRequestMatcher.Builder mvcMatcherBuilder =
                new MvcRequestMatcher.Builder(introspector);

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(mvcMatcherBuilder.pattern("/admin/**")).hasAuthority("ADMIN")
                .requestMatchers(mvcMatcherBuilder.pattern("/user/register")).permitAll()
                .requestMatchers(mvcMatcherBuilder.pattern("/user/**")).hasAnyAuthority("USER", "ADMIN")
                .anyRequest().permitAll());
        //        enables login form when login page is accessed
        http.formLogin(form -> form.loginPage("/user/login").permitAll());

//        enables logout
        http.logout(form -> form.logoutUrl("/user/logout"));

//        enables h2-console
        if (h2ConsoleNeeded) {
            http.csrf(csrf -> csrf.ignoringRequestMatchers(toH2Console()));
            http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        }
        return http.build();
    }
}