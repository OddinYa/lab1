package org.lab1.filters;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.IOException;
import java.io.PrintWriter;

public class AppResponseWrapper extends HttpServletResponseWrapper {
    private final PrintWriter printWriter;

    public AppResponseWrapper(HttpServletResponse response, PrintWriter printWriter) {
        super(response);
        this.printWriter = printWriter;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return printWriter;
    }

}
