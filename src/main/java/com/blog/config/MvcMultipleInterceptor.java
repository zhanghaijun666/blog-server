package com.blog.config;

import com.blog.utils.PathUtils;
import com.blog.utils.ResourceUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

//ResourceHttpRequestHandler
public class MvcMultipleInterceptor implements HandlerInterceptor {

    private String UIDir;
    private String mediaType;

    public MvcMultipleInterceptor(String UIDir, String mediaType) {
        this.UIDir = UIDir;
        this.mediaType = mediaType;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!StringUtils.equals(request.getMethod(), "GET")) {
            return true;
        }
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        if (path == null) {
            return true;
        }
        File file = new File(PathUtils.joinPath(PathUtils.getBlogServerPath(), this.UIDir, path));
        if (!file.exists() && file.getName().startsWith("packed-")) {
            file = new File(PathUtils.joinPath(PathUtils.getBlogServerPath(), this.UIDir, path + ".txt"));
        }
        if (!file.exists() || !file.canRead()) {
            return true;
        }
        try (OutputStream outputStream = response.getOutputStream()) {
            response.setContentType(mediaType);
            ResourceUtils.writeResource(file, outputStream);
        }
        return false;
    }
}
