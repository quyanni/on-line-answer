package qinkai.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "authorityFilter", urlPatterns = { "/*" })
public class AuthorityFilter implements Filter {

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	/*	HttpServletRequest req = (HttpServletRequest) request;
		String requestPath = req.getRequestURL().toString().toLowerCase();
		HttpSession session = req.getSession();
		if (session.getAttribute("student") == null
				&& session.getAttribute("teacher") == null
				&& session.getAttribute("admin") == null
				&& !requestPath.contains("index")
				&& !requestPath.contains("login")
				&& !requestPath.contains("register")) {
			request.setAttribute("msg", "您还没有登录！请先登录！");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			chain.doFilter(request, response);
		}*/
		chain.doFilter(request, response);
	}

	public void destroy() {
	}
}
