/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.filter;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = "")
public class adminFilter implements Filter {

    /**
     * Filtert die resultierende Seite
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Erzeugen des Wrapper-Objektes
        ResponseWrapper wrapper = new ResponseWrapper((HttpServletResponse) response);

        // Proceed with the filter chain
        chain.doFilter(request, wrapper);

        // Retrieve the response content
        String content = wrapper.getContent();

        // Remove the <div> with a specific id or class
        String modifiedContent = content.replaceAll("(?s)<div[^>]*class=\"order-overview-container\".*?</div>", "");
        modifiedContent = modifiedContent.replaceAll("(?s)<div[^>]*class=\"change-menu-container\".*?</div>", "");

        // Write the modified content back to the response
        PrintWriter out = response.getWriter();
        out.write(modifiedContent);

        // Flush and close the output
        out.close();
    }
}

/**
 * Wrapper class for buffering the response.
 */
class ResponseWrapper extends HttpServletResponseWrapper {

    private CharArrayWriter buffer;

    public ResponseWrapper(HttpServletResponse response) {
        super(response);
        buffer = new CharArrayWriter();
    }

    @Override
    public PrintWriter getWriter() {
        return new PrintWriter(buffer);
    }

    public String getContent() {
        return buffer.toString();
    }
}
