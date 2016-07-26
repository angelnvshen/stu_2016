package filter.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by CHANEL on 2016/3/30.
 */
public class EnCodeFilter implements Filter {

    Map<String, String> params = new HashMap<String, String>();

    public void init(FilterConfig filterConfig) throws ServletException {
        Enumeration<String> names = filterConfig.getInitParameterNames();
        while (names.hasMoreElements()){
            String name = names.nextElement();
            params.put(name, filterConfig.getInitParameter(name));
        }
        System.out.println("EncodeFilter init");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String encodeCoding = params.get("EncodeCoding");
        servletRequest.setCharacterEncoding(encodeCoding);
        servletResponse.setCharacterEncoding(encodeCoding);
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("EncodeFilter doFilter");
    }

    public void destroy() {
        System.out.println("EncodeFilter destroy");
    }
}
