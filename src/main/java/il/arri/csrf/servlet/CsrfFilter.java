package il.arri.csrf.servlet;

import lombok.SneakyThrows;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Alexander Krasnyanskiy
 */
public class CsrfFilter implements Filter {

    @SneakyThrows
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) {
        if (!((HttpServletRequest) req).getSession().isNew()) {
            //
            // We are here only if the client knows about the session
            //
            Cookie[] cookies = ((HttpServletRequest) req).getCookies();
            if (cookies != null) {
                String tokenFromCookies = findCookieValue(cookies, "MY-CSRF-TOKEN");
                if (tokenFromCookies != null) {
                    String tokenFromHeader = req.getParameter("MY-CSRF-TOKEN");
                    if (tokenFromHeader == null || !tokenFromHeader.equals(tokenFromCookies)) {
                        req.getRequestDispatcher("/WEB-INF/views/busted.jsp").forward(req, resp);
                    }
                }
            }
        }

        chain.doFilter(req, resp);
    }

    private String findCookieValue(Cookie[] cookies, String csrfKey) {
        for (Cookie cookie : cookies)
            if (csrfKey.equals(cookie.getName()))
                return cookie.getValue();

        return null;
    }

    public void init(FilterConfig filterConfig) {
        // NOP
    }

    public void destroy() {
        // NOP
    }
}