package com.myshopping.ShopHub.Security;

import com.myshopping.ShopHub.Entity.AppUsers;
import com.myshopping.ShopHub.Service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtVerify extends OncePerRequestFilter {
    @Autowired
    private Token token_class;
    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader("Authorization");
        if (header == null || !(header.startsWith("Bearer"))) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = header.split("Bearer ")[1];
        String username;
        try {
            username = token_class.getUsernameFromToken(token);
        } catch (ExpiredJwtException ex) {
            throw new BadCredentialsException("TOKEN_EXPIRED", ex);
        } catch (MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            throw new BadCredentialsException("INVALID_TOKEN", ex);
        }
        if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            AppUsers user1 = userService.findUserByEmail(username);
            if (user1.getIs_active() == false) {
                throw new LockedException("User is Blocked");
            }
            UserDetailImpl user2 = new UserDetailImpl(user1);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                    = new UsernamePasswordAuthenticationToken(user2, null, user2.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        }
        filterChain.doFilter(request,response);

    }
}
