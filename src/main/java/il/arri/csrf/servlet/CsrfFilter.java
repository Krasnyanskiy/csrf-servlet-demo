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
        //
        //if the client knows about the session
        //
        if (!((HttpServletRequest) req).getSession().isNew()) {
            Cookie[] cookies = ((HttpServletRequest) req).getCookies();
            if (cookies != null) {
                String tokenFromCookies = findCookieValue(cookies, "MY-CSRF-TOKEN");
                if (tokenFromCookies != null) {

                    //String tokenFromHeader
                    // = ((HttpServletRequest) req).getHeader("My-CSRF-TOKEN");

                    String tokenFromHeader = req.getParameter("MY-CSRF-TOKEN");

                    if (tokenFromHeader == null || !tokenFromHeader.equals(tokenFromCookies)) {
                        //err.printf("Go away, dirty hacker! %n[cookies_token=%s, request_token=%s]%n", tokenFromCookies, tokenFromHeader);
                        req.getRequestDispatcher("/WEB-INF/views/busted.jsp").forward(req, resp);
                    }

                    //out.println("Matched! (cookies token is the same as request token)");
                    //req.getRequestDispatcher("/WEB-INF/views/bingo.jsp").forward(req, resp);
                }
            }
        }

        chain.doFilter(req, resp);
    }

    private String findCookieValue(Cookie[] cookies, String key) {
        for (Cookie cookie : cookies)
            if (key.equals(cookie.getName()))
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