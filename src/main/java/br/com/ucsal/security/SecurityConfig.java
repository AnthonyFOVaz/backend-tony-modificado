package br.com.ucsal.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

// configuração central do Spring Security
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            // sem sessão — cada requisição é autenticada pelo token JWT
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // login público — doc. restrição 1.3
                .requestMatchers("/auth/**").permitAll()

                // gestão de usuários: só ADMIN
                .requestMatchers("/api/usuarios/**").hasAuthority("ADMIN")

                // cadastrar/inativar medicamento e repor estoque: só ADMIN — doc. admin item 4
                .requestMatchers(HttpMethod.POST, "/api/medicamentos").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PATCH, "/api/medicamentos/*/inativar").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/medicamentos/*/estoque").hasAuthority("ADMIN")

                // cadastrar/inativar profissional: só ADMIN — doc. admin item 3
                .requestMatchers(HttpMethod.POST, "/api/profissionais").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/profissionais/*/inativar").hasAuthority("ADMIN")

                // baixar estoque e criar requisição: só PROFISSIONAL_SAUDE — doc. profissional item 3
                .requestMatchers(HttpMethod.PATCH, "/api/medicamentos/*/baixar-estoque").hasAuthority("PROFISSIONAL_SAUDE") 
                .requestMatchers(HttpMethod.POST, "/api/requisicoes").hasAuthority("PROFISSIONAL_SAUDE")

                // cadastrar/inativar pacientes e registrar atendimentos: só PROFISSIONAL_SAUDE — doc. profissional item 2
                .requestMatchers(HttpMethod.POST, "/api/pacientes").hasAuthority("PROFISSIONAL_SAUDE")
                .requestMatchers(HttpMethod.PUT, "/api/pacientes/*/inativar").hasAuthority("PROFISSIONAL_SAUDE")
                .requestMatchers(HttpMethod.POST, "/api/atendimentos").hasAuthority("PROFISSIONAL_SAUDE")
                .requestMatchers(HttpMethod.PUT, "/api/atendimentos/*/encerrar").hasAuthority("PROFISSIONAL_SAUDE")

                // atualizar próprio cadastro: só PROFISSIONAL_SAUDE — doc. profissional item 1
                .requestMatchers(HttpMethod.PUT, "/api/profissionais/*/atualizar").hasAuthority("PROFISSIONAL_SAUDE")

                // GETs de consulta (relatórios, listagens): qualquer perfil autenticado — doc. admin item 5 e profissional item 4
                .anyRequest().authenticated()
            )
            // sem token: 401 — sem permissão: 403
            .exceptionHandling(ex -> ex
                .authenticationEntryPoint((req, res, e) -> res.sendError(401, "Não autenticado"))
                .accessDeniedHandler((req, res, e) -> res.sendError(403, "Acesso negado"))
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // permite apenas requisições vindas do APP-FRONT Angular — requisito 1.3.2 do TED
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of(
            "http://localhost:4200",
            "https://ucsalservice.duckdns.org",
            "http://ucsalservice.duckdns.org",
            "https://144.33.14.208",
            "http://144.33.14.208"
        ));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    // bean de encoding de senha usado pelo UsuarioService
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // disponibiliza o AuthenticationManager para o AuthController usar no login
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
