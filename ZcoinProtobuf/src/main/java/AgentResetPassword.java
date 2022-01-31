import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import TemporaryStorageInJvm.CurrentUser;
import TemporaryStorageInJvm.UserInformations.UserDetails.Builder;

@WebServlet("/AgentResetPassword")
public class AgentResetPassword extends HttpServlet 
{
	 protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	 {
		 response.setContentType("text/html;charset=UTF-8");
		 try 
		 {
	            Builder Account=CurrentUser.getBuilder();
	            int ZidOfUser = Integer.parseInt(request.getParameter("zidofuser"));
	            String password=request.getParameter("usernewpassword");
	            
	            //set in jvm
	            Account.setAgentUserZid(ZidOfUser);
	            Account.setAgentUserNewPassword(password);    
	        
	            JSONObject obj=new JSONObject();
				obj.put("status","success");
				response.getWriter().print(obj);
	            
		 }
		 catch(Exception ex)
	     {
			 System.out.println("Exception");
	         System.out.println(ex);
	     }
	 }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}