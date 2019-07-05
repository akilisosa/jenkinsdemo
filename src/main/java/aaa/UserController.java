package aaa;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Models.IsAdmin;
import Models.Riem;
import Models.User;
import Models.UserInfo;
import dao.DaoService;

public class UserController {

	 DaoService dao = new DaoService();
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("Login" );
//		response.getWriter().append("UserController-login");
		String user = request.getParameter("email");
		String pass = request.getParameter("password");
		System.out.println(user);
		System.out.println(pass);
		User oldUser = new User(user, pass);
		
		System.out.println("this is user " + oldUser);
		IsAdmin thisGuy = dao.readUser(oldUser); //returns id and admin level
		System.out.println(thisGuy);
		
		Gson gson = new Gson();
		String json = gson.toJson(thisGuy);
		
		PrintWriter out = response.getWriter();
		out.write(json.toString());
		out.flush();
		
	}
	
	public void getUserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userId = request.getParameter("userId");
		System.out.println("this is in getUserInfo " + userId);
		int result = Integer.parseInt(userId);			
		UserInfo info = dao.readInfo(result);
		System.out.println(info);
		Gson gson = new Gson();
		String json = gson.toJson(info);
		PrintWriter out = response.getWriter();
		out.write(json.toString());
		out.flush();
		
	}
	
	public void updateUserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
	
		String userId = request.getParameter("userId");
		String prName = request.getParameter("preferedName");
		String lfName = request.getParameter("legalFirstName");
		String llName = request.getParameter("legalLastName");
		String street = request.getParameter("Street");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zipCode");
		String extraNotes = request.getParameter("extraNotes");
		
		System.out.println(userId);
		int result = Integer.parseInt(userId);
		
		UserInfo newInfo = new UserInfo();
		newInfo.setUserInfoID(result);
		newInfo.setPreferedName(prName);
		newInfo.setFirstName(lfName);
		newInfo.setLastName(llName);
		newInfo.setStreet(street);
		newInfo.setCity(city);
		newInfo.setState(state);
		newInfo.setZipCode(zip);
		newInfo.setExtraNotes(extraNotes);
	
					
		String UserName = dao.updateInfo(newInfo);
		System.out.println(UserName);
		Gson gson = new Gson();
		String json = gson.toJson(UserName);
		PrintWriter out = response.getWriter();
		out.write(json.toString());
		out.flush();
		
	}
	
	public void createClaim(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		 
		 String userId = request.getParameter("userId");
		 String claimAmount = request.getParameter("claimAmount");
		 String claimDesc = request.getParameter("claimUrl");
		 String claimUrl = request.getParameter("claimUrl");
		 System.out.println("inside create claim");
		 int result = Integer.parseInt(userId);
		 int result2 = Integer.parseInt(claimAmount);
		 
		 Riem mike = new Riem();
		 mike.setClaimAmount(result2);
		 mike.setUser_id(result);
		 mike.setImage(claimUrl);
		 mike.setClaimDesc(claimDesc);
		 
		 int success = dao.createRiembursment(mike);
		 System.out.println("was reim success? "+ success);
		 
		 Gson gson = new Gson();
			String json = gson.toJson(mike);
			PrintWriter out = response.getWriter();
			out.write(json.toString());
			out.flush();
		 
		 
		 
	}
	
	public void showClaims(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("is it getting here");
		List<Riem> allClaims = dao.read_all_Riembursments();
		System.out.println(allClaims);
		 Gson gson = new Gson();
			String json = gson.toJson(allClaims);
			PrintWriter out = response.getWriter();
			out.write(json.toString());
			out.flush();
		 
		
	}
	
	public void showMyClaims(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 String userId = request.getParameter("userId");
		 int u_id = Integer.parseInt(userId);
		 List<Riem> myClaims = dao.read_user_Riembursments(u_id);
		System.out.println("please tell me it can do this");
		 Gson gson = new Gson();
			String json = gson.toJson(myClaims);
			PrintWriter out = response.getWriter();
			out.write(json.toString());
			out.flush();
	}
	
	public void updateClaims(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 String userId = request.getParameter("rid");
		 int rid = Integer.parseInt(userId);
		 String reimStat = request.getParameter("status");
		 int status = Integer.parseInt(reimStat);
		 
		 int verify = dao.updateRiembursment(rid, status);
		 
		 Gson gson = new Gson();
			String json = gson.toJson(verify);
			PrintWriter out = response.getWriter();
			out.write(json.toString());
			out.flush();
		 
	}
		
	public void readAllUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("is it getting here");
		List<User> allUsers = dao.readAllUsers();
		System.out.println(allUsers);
		 Gson gson = new Gson();
			String json = gson.toJson(allUsers);
			PrintWriter out = response.getWriter();
			out.write(json.toString());
			out.flush();
	}
	
	
	
	public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("register");
		response.getWriter().append("UserController-register");
	}

	public void search(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	
	
}
