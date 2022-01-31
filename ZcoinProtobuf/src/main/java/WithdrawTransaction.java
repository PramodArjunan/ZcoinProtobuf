import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import TemporaryStorageInJvm.CurrentUser;
import TemporaryStorageInJvm.UserInformations.UserDetails.Builder;

@WebServlet("/WithdrawTransaction")
public class WithdrawTransaction extends HttpServlet 
{

		protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
		 {
			 response.setContentType("text/html;charset=UTF-8");
			 try 
			 {	    
				 //Stroing the withdraw amount in jvm and redirecting to confirmpassword page	            
		            Builder Account=CurrentUser.getBuilder();
		            int Withdraw = Integer.parseInt(request.getParameter("withdraw"));
		            Account.setWithdraw(Withdraw);     
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