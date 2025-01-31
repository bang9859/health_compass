package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import user.action.SearchHospital;
import user.model.Bookmark;
import user.model.User;
import user.model.UserDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter("/*")
public class AuthFilter extends HttpFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		String uri = req.getRequestURI();
		System.out.println("request uri : "+uri);

		
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("log");
		if(user != null && uri.equals("/bookmark")) {
			UserDao userDao = UserDao.getInstance();
			List<String> list = userDao.allSearchBookmark(user.getUserCode());
			List<Bookmark> bookmarkList = new ArrayList<Bookmark>();
			SearchHospital sh = new SearchHospital();
			for(int i=0; i<list.size(); i++) {
				bookmarkList.add(sh.searchHospital(list.get(i)));
			}
			session.setAttribute("bookmark", bookmarkList);
		} else if (user == null && (uri.equals("/mypage") || uri.equals("/delete") || uri.equals("/bookmark"))) {
			res.sendRedirect("/login");
		}
		// 인증이 필요없는 것들은 제외
		
		chain.doFilter(request, response);
	}


}
