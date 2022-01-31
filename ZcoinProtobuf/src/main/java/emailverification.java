 import java.io.IOException;
 import org.json.simple.JSONObject;    
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import TemporaryStorageInJvm.CurrentUser;
import TemporaryStorageInJvm.UserInformations.UserDetails;

import DatabaseInterface.Mysql;
@WebServlet("/emailverification")
public class emailverification extends HttpServlet
{

	 protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	 {
		 response.setContentType("text/html;charset=UTF-8");
		 CurrentUser currentUser=new CurrentUser();
		// UserCreation Create=new UserCreation();
		 Mysql mysql=new Mysql();
		 try 
		 {
	            String Email = request.getParameter("email");
	            String query="select * from userinformation where email='"+Email+"'";
	            ResultSet rs=mysql.mysqlSelect(query);
	            JSONObject obj=new JSONObject();
	            
	            //if the email is present in the database then redirecting to password page
	            if(rs.next())
	            { 
	            	UserDetails.Builder Create=UserDetails.newBuilder();
	            	Create.setName(rs.getString("name"));
	            	Create.setPhone(rs.getLong("phone"));
	            	Create.setEmail(rs.getString("email"));
	            	Create.setHid(rs.getString("hid"));
	            	Create.setRc(rs.getInt("rc"));
	            	Create.setZid(rs.getInt("zid"));
	            	Create.setPassword(rs.getString("password"));
	            	Create.setType(rs.getInt("type"));
	            	Create.setAccess(rs.getBoolean("access"));
	            	Create.build();
	            	currentUser.setBuilder(Create);
	            	
    	       	  	
	            	
	            	
    	       	  	if(rs.getBoolean("access")!=true && rs.getBoolean("type")!=true)
    	       	  	{
    	       	  		//agent didnt give access to customer
    	       	  		
    	       	  		obj.put("Status","AccessNotGivenByAgent");
    	       	  	 	response.getWriter().print(obj);
    	       	  	}
    	       	  	else
    	       	  	{
    	       	  		//email verification successfull
    	       	  		
	    	       	  	obj.put("Status","Success");
		       	  		response.getWriter().print(obj);
    	       	  	}
	            }
	            else
	            {
	            	//if the email is not present in the database then display error in the html page
	            	
	            	obj.put("Status","NotaValidEmail");
	       	  		response.getWriter().print(obj);
	            }
	          
	         
		 }
		 catch(Exception ex)
	     {
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
