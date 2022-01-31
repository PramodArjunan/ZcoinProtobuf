import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import TemporaryStorageInJvm.CurrentUser;
import TemporaryStorageInJvm.UserInformations.UserDetails.Builder;

@WebServlet("/ProfilePage")

public class ProfilePage extends HttpServlet 
{

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html;charset=UTF-8");
	
		Builder UserCreation=CurrentUser.getBuilder();
		
		// agent giving access to user login
		JSONArray employees = new JSONArray();
		try 
		{
			for(int i=0;i<2;i++)
			{
				JSONObject obj=new JSONObject();
				System.out.println("Entered");
				obj.put("Name",UserCreation.getName());
				obj.put("Email",UserCreation.getEmail());
				obj.put("Phone",UserCreation.getPhone());
				obj.put("Hid",UserCreation.getHid());
				obj.put("Rc",UserCreation.getRc());
				obj.put("Zid",UserCreation.getZid());
				obj.put("type",UserCreation.getType());
				employees.add(obj);
			}
			response.getWriter().print(employees);}
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