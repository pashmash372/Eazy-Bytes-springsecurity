package dev.pashmash.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        /**
         *  Below is the custom security configurations
         */

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards").authenticated()
                        .requestMatchers("/notices", "/contact", "/register").permitAll()).formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();

        /**
         *  Configuration to deny all the requests
         */
        /*http.authorizeHttpRequests(requests -> requests.anyRequest().denyAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();*/

        /**
         *  Configuration to permit all the requests
         */
        /*http.authorizeHttpRequests(requests -> requests.anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();*/
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//
///*//      Approach 1 where we use withDefaultPasswordEncoder() method
////		while creating the user details
//        UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("12345").authorities("admin").build();
//        UserDetails user = User.withDefaultPasswordEncoder().username("user").password("12345").authorities("read").build();
//        return new InMemoryUserDetailsManager(admin, user);*/
//
////     Approach 2 where we use NoOpPasswordEncoder Bean
////		while creating the user details
////        UserDetails admin = User.withUsername("admin").password("12345").authorities("admin").build();
////        UserDetails user = User.withUsername("user").password("12345").authorities("read").build();
////        return new InMemoryUserDetailsManager(admin, user);
//
//    }

    /**
     * NoOpPasswordEncoder is not recommended for production usage.
     * Use only for non-prod.
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

//     @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }
}
