import java.io.IOException;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import DatabaseInterface.Mysql;

@WebServlet("/AllUsers")

public class AllUsers extends HttpServlet 
{

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html;charset=UTF-8");
		Mysql mysql=new Mysql();
		System.out.println("Allusers");		
		JSONArray TransactionOfUsers = new JSONArray();
		
		try 		
		{
			 String query="select * from userinformation";			 
			 ResultSet rs=mysql.mysqlSelect(query);		
		     while(rs.next())
	         {		    
					JSONObject obj=new JSONObject();					
					String name=rs.getString("name");					
					obj.put("name",name);	
					String email=rs.getString("email");						
					obj.put("email",email);					
					long phone=rs.getLong("phone");					
					obj.put("phone",phone);
					Integer zid=rs.getInt("zid");
					obj.put("zid",zid);
					String userhid=rs.getString("hid");
					obj.put("hid",userhid);
					Integer rc=rs.getInt("rc");
					obj.put("rc",rc);
					Integer access=rs.getInt("access");
					obj.put("access",access);
					Integer type=rs.getInt("type");
					obj.put("type",type);
					TransactionOfUsers.add(obj);
	           }
		       response.getWriter().print(TransactionOfUsers);
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