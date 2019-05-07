package cn.WorkSubmit;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class IndexServlet
 */
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public IndexServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");		
		if (user==null) {
			response.getWriter().print("����û�е�½����<a href='/WorkSubmit/login.html'>��½</a>");
		}else {
//			response.getWriter().print("���ѵ�½����ӭ�㣬"+user.getUsername()+"!<br />");
//			response.getWriter().print("<a href='/WorkSubmit/Logout'>�˳�</a>");
			
			Cookie cookie=new Cookie("JSESSIONEID",session.getId());
			cookie.setMaxAge(60*30);
			cookie.setPath("/WorkSubmit");
			response.addCookie(cookie);
			
			response.sendRedirect("/WorkSubmit/form.jsp");			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
