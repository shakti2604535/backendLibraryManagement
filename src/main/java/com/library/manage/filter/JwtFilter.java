package com.library.manage.filter;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;

import com.library.manage.exception.ResourceNotFoundException;
import com.library.manage.service.CustomUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//@CrossOrigin(origins="http://localhost:4200",allowedHeaders = "*")
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomUserDetailsService service;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        
        String token = null;
        String userName = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            try {
            userName = jwtUtil.extractUsername(token);
            }
            catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
//                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
//                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT Token has expired");
            }
            catch(SignatureException e){
//            	httpServletResponse.sendError(404, userName);
            	 System.out.println("Invalid");
//            	httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
//                throw   new SignatureException("Invalid");
            }catch(MalformedJwtException e) {
           	 System.out.println("Invalid");

//            	httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
//          				throw new MalformedJwtException("Invalid Token");
            }
        }

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = service.loadUserByUsername(userName);

            if (jwtUtil.validateToken(token, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}