package info.sjd.servlet.filter;

import info.sjd.service.backEndService.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain)
            throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        final String login = request.getParameter("login");
        final String password = request.getParameter("password");

        final HttpSession session = request.getSession();

        if (nonNull(session) && nonNull(session.getAttribute("login")) && nonNull(session.getAttribute("password"))){

            request.getRequestDispatcher("/WEB-INF/jsp/catalog.jsp").forward(request, response);

        } else if (UserService.findByNameAndPassword(login, password) != null){
            request.getSession().setAttribute("password", password);
            request.getSession().setAttribute("login", login);

            request.getRequestDispatcher("/WEB-INF/jsp/catalog.jsp").forward(request, response);

        } else {
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}