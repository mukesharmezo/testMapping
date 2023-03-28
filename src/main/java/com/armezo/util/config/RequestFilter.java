package com.armezo.util.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.armezo.util.utility.JwtTokenUtility;

import io.jsonwebtoken.ExpiredJwtException;

public class RequestFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtTokenUtility tokenUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String requestTokenHandler = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;

		if (requestTokenHandler != null && requestTokenHandler.startsWith("Bearer ")) {
			jwtToken = requestTokenHandler.substring(7);
			try {
				username = tokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (ExpiredJwtException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Invalid Access Token");
		}

		// After token validation
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				if (tokenUtil.validateToken(jwtToken, userDetails)) {
					UsernamePasswordAuthenticationToken upaToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					upaToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(upaToken);
				}
		}
		filterChain.doFilter(request, response);
		
	}

}
