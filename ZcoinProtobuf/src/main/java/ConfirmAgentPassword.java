import java.io.IOException;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import DatabaseInterface.Mysql;
import TemporaryStorageInJvm.CurrentUser;
import TemporaryStorageInJvm.UserInformations.UserDetails.Builder;

@WebServlet("/ConfirmAgentPassword")
public class ConfirmAgentPassword extends HttpServlet 
{
	 protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	 {
		 response.setContentType("text/html;charset=UTF-8");
		 Mysql mysql=new Mysql();
		
		 try 
		 {
	            Builder Account=CurrentUser.getBuilder();           
	            String Password = request.getParameter("password");  
	           
	           System.out.println(Account);
				
	            if(Password.equals(Account.getPassword()))
	            {
	            	// updating password for particular user by admin
	            	
	            	
	            	String query="select * from userinformation where zid='"+Account.getAgentUserZid()+"'";
		            ResultSet rs=mysql.mysqlSelect(query);
		            
		            if(rs.next())
		            {
			            	String updatepassword="update userinformation set password='"+Account.getAgentUserNewPassword()+"' where zid='"+Account.getAgentUserZid()+"'";
			    			mysql.mysqlUpdate(updatepassword);
			    			Account.setAgentUserZid(0);
				            Account.setAgentUserNewPassword("");
				            Account.setMessage("userpasswordrestsuccess");
				            JSONObject obj=new JSONObject();
				            obj.put("Status","Success");
		      	       	  	response.getWriter().print(obj);
		            }
		            else
		            {
		            	 	Account.setMessage("EnteredZidNotPresentToResetPassword");
				            JSONObject obj=new JSONObject();
				            obj.put("Status","Success");
		      	       	  	response.getWriter().print(obj);
		            }
	            }
	            else
	            {
	            	  JSONObject obj=new JSONObject();
	            	  Account.setMessage("WrongPassword");
	      	       	  obj.put("Status","WrongPassword");
	      	       	  response.getWriter().print(obj);
	            }
		 }
		 catch(Exception ex)
	     {
			 System.out.println("Exception");
	            System.out.println(ex);
	     }
	 }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		processRequest(request, response);
	}

}