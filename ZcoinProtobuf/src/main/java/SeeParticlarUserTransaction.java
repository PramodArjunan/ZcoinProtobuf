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

@WebServlet("/SeeParticlarUserTransaction")
public class SeeParticlarUserTransaction extends HttpServlet 
{
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	 {
		 response.setContentType("text/html;charset=UTF-8");
		 Mysql mysql=new Mysql();
		 JSONArray TransactionOfUsers = new JSONArray();
		 try 
		 {	    
			 //storing deposit amount in jvm
	            
	            int  zid=0;
	            zid= Integer.parseInt(request.getParameter("seezid"));
	            String query="select * from transactions where userzid='"+zid+"'";
	            ResultSet rs1=mysql.mysqlSelect(query);
	            
	            System.out.println(zid);
	            int count=0;
	            while(rs1.next())
	            {
	            	count+=1; 
	            }
	            if(count!=0)
	            {
	            	String query1="select * from transactions where userzid='"+zid+"'";
	 	            ResultSet rs=mysql.mysqlSelect(query1);
	 	            
	 	           while(rs.next())
		            {
			    
	 	        	   	JSONObject obj=new JSONObject();
						
						Integer userzid=rs.getInt("userzid");
						
						obj.put("userzid",userzid);
		
						String transaction=rs.getString("transaction");
							
						String dateandtime=rs.getTimestamp("dateandtime").toString(); 
						
						obj.put("dateandtime",dateandtime);
						
						obj.put("transaction",transaction);
					
						Integer transactionamount=rs.getInt("transactionamount");
						
						obj.put("transactionamount",transactionamount);
				
						Integer sendzid=rs.getInt("sendzid");
						
						obj.put("sendzid",sendzid);
						
						Integer balance=rs.getInt("balance");
				
						obj.put("balance",balance);
					
						obj.put("status","success");
						
						TransactionOfUsers.add(obj);
		            }
	 	      	
	 	           	response.getWriter().print(TransactionOfUsers);
	  	       			
		       }
	            else
	            {
	            	String query2="select * from userinformation where zid='"+zid+"'";
	 	            ResultSet rs2=mysql.mysqlSelect(query2);
	 	            if(rs2.next())
		            {
	 	            	JSONObject obj=new JSONObject();
	 	            	obj.put("status","NoActionPerformedByZid");
	 	            	TransactionOfUsers.add(obj);
	 	            	
	 	            	response.getWriter().print(TransactionOfUsers);
		            }
	 	            else
	 	            {
	 	            	JSONObject obj=new JSONObject();
	 	            	obj.put("status","ZidNotPresent");
	 	            	TransactionOfUsers.add(obj);
	 	            	
	 	            	response.getWriter().print(TransactionOfUsers);
	 	            }
	            }
	            
	
		 }
		 catch(Exception ex)
	     {
			 System.out.println("error");
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