import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import TemporaryStorageInJvm.CurrentUser;
import TemporaryStorageInJvm.UserInformations.UserDetails.Builder;

@WebServlet("/Deposittransaction")
public class Deposittransaction extends HttpServlet 
{

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{
			response.setContentType("text/html;charset=UTF-8");
		 try 
		 {	    
			 	//storing deposit amount in jvm	
	        
			 	Builder Account=CurrentUser.getBuilder();
	            int Deposit = Integer.parseInt(request.getParameter("deposit"));
	            Account.setDepost(Deposit);     

	           
	            JSONObject obj=new JSONObject();
    	  		obj.put("Status","Success");
    	  	 	response.getWriter().print(obj);
  	       	  	
		 }
		 catch(Exception ex)
	     {
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