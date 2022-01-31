import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import TemporaryStorageInJvm.CurrentUser;
import TemporaryStorageInJvm.UserInformations.UserDetails.Builder;


@WebServlet("/TransactionMessage")
public class TransactionMessage extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	 {
		 response.setContentType("text/html;charset=UTF-8");
		 JSONObject obj=new JSONObject();
		 Builder UserCreation=CurrentUser.getBuilder();
		 String message=UserCreation.getMessage();
		 String zid=UserCreation.getAccessGivenTo();
		 System.out.println("1");
		 UserCreation.setMessage("");
		 System.out.println("1");
		 UserCreation.setAccessGivenTo("");
		 System.out.println("1");
         
		
		 
		 obj.put("Status",message);
  		 obj.put("AccessGivenTo",zid);
		 response.getWriter().print(obj);
	 }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
