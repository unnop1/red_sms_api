package com.nt.red_sms_api.Auth;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization logic, if needed
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //Authorization
        String requestHeader = request.getHeader("Authorization");
        //Bearer 2352345235sdfrsfgsdfsdf
        logger.info(" Header :  {}", request);
        String username = null;
        String token = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer")) {

            //looking good
            token = requestHeader.substring(7);
            try {

                username = this.jwtHelper.getUsernameFromToken(token);

            } catch (IllegalArgumentException e) {
                logger.info("Illegal Argument while fetching the username !!");
                e.printStackTrace();
            } catch (ExpiredJwtException e) {
                logger.info("Given jwt token is expired !!");
                e.printStackTrace();
            } catch (MalformedJwtException e) {
                logger.info("Some changed has done in token !! Invalid Token");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            logger.info("Invalid Header Value !! ");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            //fetch user detail from username
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            Boolean validateToken = this.jwtHelper.validateToken(token, userDetails);
            if (validateToken) {
                //set the authentication
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                // Create custom authentication details
                CustomAuthenticationDetails authenticationDetails = new CustomAuthenticationDetails(request);
                authentication.setDetails(authenticationDetails);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                logger.info("Validation fails !!");
            }
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup logic, if needed
    }
    
    // Custom authentication details class
    private static class CustomAuthenticationDetails {
        private final String remoteAddress;
        private final String sessionId;

        public CustomAuthenticationDetails(HttpServletRequest request) {
            this.remoteAddress = request.getRemoteAddr();
            this.sessionId = request.getSession() != null ? request.getSession().getId() : null;
        }

        public String getRemoteAddress() {
            return remoteAddress;
        }

        public String getSessionId() {
            return sessionId;
        }
    }
}
