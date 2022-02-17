/*
 *
 *  *  *  * ---------------------------------------------------------------------------------------------
 *  *  *  *  *  Copyright (c) IJSE-intern. All rights reserved.
 *  *  *  *  *  Licensed under the MIT License. See License.txt in the project root for license information.
 *  *  *  *  --------------------------------------------------------------------------------------------/
 *
 */

package com.example.customercrud.filter;

import com.example.customercrud.service.impl.MyUserService;
import com.example.customercrud.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 5/28/2021
 **/

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private String loginEmail;

    @Autowired
    private MyUserService myUserDetails;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String email = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("mPos ")){
            jwt = authorizationHeader.substring(5);
            email = jwtUtil.extractEmail(jwt);
        }
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.myUserDetails.loadUserByUsername(email);
            if (jwtUtil.validateToken(jwt, userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        setLoginEmail(email);
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }

    public void setLoginEmail(String email){
        loginEmail = email;
    }

    public String getLoginEmail(){
        return loginEmail;
    }
}
