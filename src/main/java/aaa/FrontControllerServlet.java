package aaa;




import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class FrontControllerServlet
 */
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		setAccessControlHeaders(response);
		UserController ucont = new UserController();
		String requestURI = request.getRequestURI();
		System.out.println("RequestURI:" + requestURI);
		
		String contextPath = request.getContextPath();
		System.out.println("ContextPath:" + contextPath);
		
		String path = requestURI.substring(contextPath.length());
		System.out.println("path:" + path);

		switch (path) {
		case "/login.do":
			System.out.println("LoginServlet");
			ucont.login(request, response);
			break;
			
		case "/getUserInfo.do":
			System.out.println("UserInfoServlet");
			ucont.getUserInfo(request, response);
			break;
			
		case "/updateUserInfo.do":
			System.out.println("updateUserInfoServlet");
			ucont.updateUserInfo(request, response);
			break;
		case "/createClaim.do":
			System.out.println("createClaimServlet");
			ucont.createClaim(request, response);
			break;
			
		case "/showMyClaims.do":
			System.out.println("showMyClaimsServlet");
			try {
				ucont.showMyClaims(request, response);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("i threw up a catch");
			}
			break;
		
		case "/showAllClaims.do":
			System.out.println("showAllClaimsServlet");
			try {
				ucont.showClaims(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		
		case "/updateClaims.do":
			System.out.println("updateClaimsServlet");
			ucont.updateClaims(request, response);
			break;
		
		case "/readAllUsers.do":
			System.out.println("readAllUsers");
			ucont.readAllUsers(request, response);
			break;
		}
		
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		setAccessControlHeaders(response);
		String requestURI = request.getRequestURI();
		System.out.println("RequestURI:" + requestURI);
		
		String contextPath = request.getContextPath();
		System.out.println("ContextPath:" + contextPath);
		
		String path = requestURI.substring(contextPath.length());
		System.out.println("path:" + path);

		switch (path) {
		case "/login.do":
			System.out.println("LoginServlet");
		//	UserController.login(request, response);

			break;
		case "/register.do":
			System.out.println("RegisterServlet");
			//UserController.register(request, response);
			break;
		}
	}
	
	private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET");
    }

}