package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import service.LoginService;

/**
 *  
 * 
 */
public class LoginServlet extends HttpServlet {
	public void init(){
	getServletContext().setAttribute("count", 0);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//处理响应中文乱码
		response.setContentType("text/html;charset=utf-89");
			//获取提交的用户名和密码
			String name = request.getParameter("username");
			String pass = request.getParameter("password");
			//调用servicr把数据传入
			LoginService sc = new LoginService();
			User user=sc.login(name, pass);
			//进行判断
			if(user!=null){
				//登录成功
				/**记录登陆的次数 start */
				//获取登录的次数
				Integer count = (Integer) getServletContext().getAttribute("count");
				
					count++;
				
				getServletContext().setAttribute("count", count);
				//count++;
				//重定向
				response.sendRedirect("/day06_login/CountServlet");
				/**记录登陆的次数 end */
				
				
				
				
				//response.getWriter().write("欢迎您"+name);
			}else{
				
				response.getWriter().write("登录失败");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
