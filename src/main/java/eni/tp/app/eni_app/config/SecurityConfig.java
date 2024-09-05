package eni.tp.app.eni_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
//
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//    1 pour indiquer que l'utilisateur est enabled
        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT email,password,1 FROM user WHERE email =?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT user.email, roles.ROLE FROM user INNER JOIN ROLES ON user.admin = roles.IS_ADMIN WHERE user.email=?");
        return jdbcUserDetailsManager;

    }

    @Bean
    public SecurityFilterChain web(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize

                        .requestMatchers("/logout").hasRole("MEMBRE")
                        .requestMatchers( "/login").permitAll()
                        .requestMatchers("/create-movie").hasAnyRole("ADMIN", "MEMBRE")
                        .requestMatchers("/list").authenticated()
                        .requestMatchers("/vendor/**").permitAll()
                        .requestMatchers("/images/**").permitAll()
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/acceuil").permitAll()
                        .requestMatchers("/miseEnSessionUser").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/details-films").authenticated()
                        .requestMatchers("/details/{id}").authenticated()

                );
//        http.formLogin(Customizer.withDefaults());
        http.formLogin(form ->
                form.loginPage("/login").defaultSuccessUrl("/miseEnSessionUser")
        );
        HeaderWriterLogoutHandler clearSiteData = new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(ClearSiteDataHeaderWriter.Directive.ALL));
        http.logout((logout) ->
                logout
                        . logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                        .logoutSuccessUrl("/login?logout")
                        .addLogoutHandler(clearSiteData));

        return http.build();


    }
}