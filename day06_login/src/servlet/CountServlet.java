package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  
 * 
 */
public class CountServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//处理响应乱码
		response.setContentType("text/html;charset=utf-8");
		//获取登录的次数
		Integer cont = (Integer) getServletContext().getAttribute("count");
		
		response.getWriter().write("您是第"+cont+"次登录");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
