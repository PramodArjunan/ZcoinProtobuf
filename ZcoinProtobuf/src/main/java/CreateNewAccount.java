import java.io.IOException;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DatabaseInterface.Mysql;

@WebServlet("/CreateNewAccount")
public class CreateNewAccount extends HttpServlet {
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	 {
		 response.setContentType("text/html;charset=UTF-8");
		 Mysql mysql=new Mysql();
		 try 
		 {	   
			 	
	            String UserName=request.getParameter("name");
	            Long UserPhone=Long.parseLong(request.getParameter("phone"));
	            String UserEmail=request.getParameter("email");
	            String UserHid=request.getParameter("hid");
	            int UserRc=Integer.parseInt(request.getParameter("rc"));
	            String UserPassword =request.getParameter("password");
	         
	            String query="select * from userinformation where email='"+UserEmail+"'";
	            ResultSet rs=mysql.mysqlSelect(query);                  
	            
	            String query1="select * from userinformation where hid='"+UserHid+"'";
	            ResultSet rs1=mysql.mysqlSelect(query1);
	                
	            if(rs.next())
	            {	            	
	            	// entered email is already present in the database
	            	response.getWriter().print("EmailAlreadyPresent");
	            }
	            else if(rs1.next())
	            {
	            	//entered hid is already present in the database
	            	response.getWriter().print("HidAlreadyPresent");
   	            }
	            else
	            {
	            	//enter the new user in the database
	            	String queryinsert="insert into userinformation(name,phone,email,hid,rc,password) values('"+UserName+"','"+UserPhone+"','"+UserEmail+"','"+UserHid+"','"+UserRc+"','"+UserPassword+"')";
	            	mysql.mysqlUpdate(queryinsert);
	            	response.getWriter().print("Success");
	            }
	            
	          
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
