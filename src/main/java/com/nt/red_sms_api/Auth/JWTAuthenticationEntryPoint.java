package com.nt.red_sms_api.Auth;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JWTAuthenticationEntryPoint extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set the HTTP response status code to 401 (Unauthorized)
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // Optionally, you can also write a custom error message to the response body
        response.getWriter().write("Unauthorized access - Please authenticate first");
    }
}
