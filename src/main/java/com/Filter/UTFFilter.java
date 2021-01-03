package com.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
//utf-8转换
public class UTFFilter implements Filter {
    String encoding;

    public void destroy() {
        this.encoding = null;
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        if (encoding == null)
            encoding = "utf-8";
        //设置请求编码
        req.setCharacterEncoding(encoding);
        //过滤传递
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

        //获取过滤器配置的初始化参数
        this.encoding = config.getInitParameter("encoding");
    }

}
