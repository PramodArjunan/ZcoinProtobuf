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
import TemporaryStorageInJvm.CurrentUser;
import TemporaryStorageInJvm.UserInformations.UserDetails.Builder;

@WebServlet("/WholeTransactions")

public class WholeTransactions extends HttpServlet 
{

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html;charset=UTF-8");
		Mysql mysql=new Mysql();
		JSONArray TransactionOfUsers = new JSONArray();
		Builder UserCreation=CurrentUser.getBuilder();
		try 
		{
			 String query="select * from transactions where userzid='"+UserCreation.getZid()+"'";
			 System.out.println(UserCreation.getZid());
			 ResultSet rs=mysql.mysqlSelect(query);
		
		     while(rs.next())
	            {
					JSONObject obj=new JSONObject();	
					Integer userzid=rs.getInt("userzid");
					obj.put("userzid",userzid);
					String transaction=rs.getString("transaction");
					obj.put("transaction",transaction);
					String dateandtime=rs.getTimestamp("dateandtime").toString(); 
					obj.put("dateandtime",dateandtime);
					Integer transactionamount=rs.getInt("transactionamount");
					obj.put("transactionamount",transactionamount);
					Integer sendzid=rs.getInt("sendzid");
					obj.put("sendzid",sendzid);					
					Integer balance=rs.getInt("balance");
					obj.put("balance",balance);
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