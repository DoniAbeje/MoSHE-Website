package com.doniabeje.moshewebsite.configuration;

import com.doniabeje.moshewebsite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS);

        http.authorizeRequests()
                .antMatchers("/addVacancy", "/vacancies", "/editVacancy/**", "/addTender", "/tenders", "/editTender/**",
                        "/addNews", "/news", "/editNews/**", "/services", "/deleteService/**", "/editService/**", "/addService",
                        "/links", "/deleteLink/**", "/editLink/**", "/addLink",
                        "/deleteNews/**", "/deleteTender/**", "/deleteVacancy/**", "/documents", "/addDocument", "/editDocument/**", "/deleteService/**",
                        "/deleteDocument/**", "/updateGeneralInformation", "/searchVacancies", "/searchTenders", "/searchNews", "/searchDocuments"
                        , "/deleteDocumentType/**", "/editDocumentType/**", "/addDocumentType"
                        , "/deleteJob/**", "/editJob/**", "/addJob", "/updateGeneralInformation", "/suggestions", "/deleteSuggestion/**"
                        , "/persons", "/deletePerson/**", "/editPerson/**", "/addPerson").hasAnyAuthority("ADMIN")
                .antMatchers("/register").hasAnyAuthority("SUPER-ADMIN")
                .antMatchers("/visitor/**", "/", "/viewNews/**", "/login").permitAll().and().formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/news").and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login");

    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {

        webSecurity.ignoring()
                .antMatchers("/resources/**", "/files/**", "/static/**", "/css/**", "/js/**", "/img/**", "/fonts/**");

    }

}