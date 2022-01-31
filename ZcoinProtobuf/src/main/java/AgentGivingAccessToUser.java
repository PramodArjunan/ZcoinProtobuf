import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DatabaseInterface.Mysql;
import TemporaryStorageInJvm.CurrentUser;
import TemporaryStorageInJvm.UserInformations.UserDetails.Builder;

@WebServlet("/AgentGivingAccessToUser")

public class AgentGivingAccessToUser extends HttpServlet 
{

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html;charset=UTF-8");
		
		Mysql mysql=new Mysql();
		
		// agent giving access to user login

		try 
		{
			String email = request.getParameter("id");
			
			Builder Account=CurrentUser.getBuilder();
			 
			 Account.setMessage("AccessGivenSuccessfully");
			 Account.setAccessGivenTo(email);
			 
			// update the access for the particular user by admin
			
			String updateaccess="update userinformation set access=true where email='"+email+"'";
			mysql.mysqlUpdate(updateaccess);
			
			response.sendRedirect("Profile.html");  	
		} 
		catch (Exception ex) 
		{
			System.out.println(ex);
		} 
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException
	{
		processRequest(request, response);
	}
}