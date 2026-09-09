package net.edupilot.userservice.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import net.edupilot.commonlibrary.JwtUtil.JwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        System.out.println("===== JWT FILTER CALLED =====");
        System.out.println("Request URI: " + request.getRequestURI());

        String token = null;

        // 1. Check Authorization header
        String authHeader = request.getHeader("Authorization");

        System.out.println("Authorization header: " + authHeader);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            token = authHeader.substring(7);

            System.out.println("JWT found in Authorization header");

        } else {

            // 2. Check JWT cookie
            Cookie[] cookies = request.getCookies();

            if (cookies != null) {

                for (Cookie cookie : cookies) {

                    System.out.println(
                            "Cookie found: " + cookie.getName()
                    );

                    if ("jwt".equals(cookie.getName())) {

                        token = cookie.getValue();

                        System.out.println("JWT found in cookie");

                        break;
                    }
                }
            }
        }

        // 3. No token
        if (token == null || token.isBlank()) {

            System.out.println("NO JWT FOUND");

            filterChain.doFilter(request, response);
            return;
        }

        try {

            // 4. Validate token
            if (jwtUtil.validateToken(token)) {

                Long userId = jwtUtil.extractUserId(token);
                String role = jwtUtil.extractRole(token);

                System.out.println("JWT VALID");
                System.out.println("User ID: " + userId);
                System.out.println("Role: " + role);

                // 5. Create Authentication
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userId,
                                null,
                                List.of(
                                        new SimpleGrantedAuthority(
                                                "ROLE_" + role
                                        )
                                )
                        );

                // 6. Store authentication in SecurityContext
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authentication);

                System.out.println(
                        "Authentication set successfully"
                );

            } else {

                System.out.println("JWT INVALID OR EXPIRED");
            }

        } catch (Exception e) {

            System.out.println(
                    "JWT ERROR: " + e.getClass().getName()
            );

            System.out.println(
                    "JWT ERROR MESSAGE: " + e.getMessage()
            );

            SecurityContextHolder.clearContext();
        }

        // 7. Continue request
        filterChain.doFilter(request, response);
    }
}
