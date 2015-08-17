package il.arri.csrf.servlet;

import lombok.SneakyThrows;
import lombok.extern.java.Log;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static java.lang.String.format;

/**
 * @author Alexander Krasnyanskiy
 */
@Log
public class CsrfFilter implements Filter {

    @SneakyThrows
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) {


        if (!((HttpServletRequest) req).getSession().isNew()) {
            //
            // We are here only if the client knows about the session
            //
            Cookie[] cookies = ((HttpServletRequest) req).getCookies();
            if (cookies != null) {
                //
                // Get a secret token value
                //
                String tokenFromCookies = findCookieValue(cookies, "MY_CSRF_TOKEN");
                log.info(format("Cookies token: [%s]", tokenFromCookies));
                if (tokenFromCookies != null) {
                    //
                    // Retrieve a hidden parameter from request
                    //
                    String tokenFromRequest = req.getParameter("MY_CSRF_TOKEN");
                    log.info(format("Request token: [%s]", tokenFromRequest));

                    if (tokenFromRequest == null || !tokenFromRequest.equals(tokenFromCookies)) {
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