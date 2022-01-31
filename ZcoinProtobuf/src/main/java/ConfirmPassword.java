import java.io.IOException;
import java.sql.ResultSet;
import DatabaseInterface.Mysql;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import TemporaryStorageInJvm.CurrentUser;
import TemporaryStorageInJvm.UserInformations.UserDetails.Builder;

@WebServlet("/ConfirmPassword")
public class ConfirmPassword extends HttpServlet 
{
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	 {
		 response.setContentType("text/html;charset=UTF-8");
		 Mysql mysql=new Mysql();
		 try 
		 {  
	            Builder Account=CurrentUser.getBuilder();         
	            Builder UserCreation=CurrentUser.getBuilder();
	            String Password = request.getParameter("password");
	            // check password is corect or not
	            if(Password.equals(UserCreation.getPassword()))
	            {
	            	if(UserCreation.getDepost()!=0)
	            	{
	            		   
				           System.out.println("Entered");
				            int balance=UserCreation.getRc()+UserCreation.getDepost();
				            Account.setRc(balance);
				          
				            String insert="insert into transactions(userzid,transaction,transactionamount,balance) values('"+UserCreation.getZid()+"','Deposit','"+UserCreation.getDepost()+"','"+balance+"')";
				            mysql.mysqlInsert(insert);
				            Account.setDepost(0);
				            
				            String update="update userinformation set rc='"+UserCreation.getRc()+"' where email='"+UserCreation.getEmail()+"'";
				            mysql.mysqlUpdate(update);
				          
				            Account.setMessage("DepositSuccess");
				            
				            JSONObject obj=new JSONObject();
			       	  		obj.put("Status","DepositSuccess");
			       	  	 	response.getWriter().print(obj);
	            	}		
	            	//withdraw 
	            	else if(UserCreation.getWithdraw()!=0)
	            	{
	            		if(UserCreation.getWithdraw() >(UserCreation.getRc()))
			            {
	            			//if withdraw is greater than balance then display error
	            			    Account.setWithdraw(0);        			    
	            			    Account.setMessage("WithdrawFailedBalanceLow");
	            			    JSONObject obj=new JSONObject();
	        	       	  		obj.put("Status","WithdrawFailedBalanceLow");
	        	       	  		response.getWriter().print(obj);
	            			 
			            }
	            		else
	            		{
	            			//store in the database
		            		
				            int balance=UserCreation.getRc()-UserCreation.getWithdraw();
				            Account.setRc(balance);      
				            String insert="insert into transactions(userzid,transaction,transactionamount,balance) values('"+UserCreation.getZid()+"','Withdraw','"+UserCreation.getWithdraw()+"','"+balance+"')";
				            mysql.mysqlInsert(insert);				            
				            Account.setWithdraw(0);				         		                
			                String update="update userinformation set rc='"+UserCreation.getRc()+"' where email='"+UserCreation.getEmail()+"'";
				            mysql.mysqlUpdate(update);				            
				            Account.setMessage("WithdrawSuccess");				          
				            JSONObject obj=new JSONObject();
        	       	  		obj.put("Status","WithdrawSuccess");
        	       	  	 	response.getWriter().print(obj);			              
	            		}
	            	}
	            	
	            	else if(UserCreation.getZidAmount()!=0)
	            	{
	            		
	            		//getting the information of account to whom amount is sending
	            		
	            		String queryselect="select * from userinformation where zid='"+UserCreation.getSendZid()+"'";
	      	            ResultSet rs=mysql.mysqlSelect(queryselect);
			            int count=0;
			            while(rs.next())
			            {
			            	count+=1;
			            }
			            if(count==1)
			            {
		            		if(UserCreation.getZidAmount() >(UserCreation.getRc()))
				            {
		            			//if send amount is greater then balance then display error
		            			Account.setMessage("SendFailedLowBalance");           			
		            			  JSONObject obj=new JSONObject();
		        	       	  		obj.put("Status","SendFailedLowBalance");
		        	       	  	 	response.getWriter().print(obj);
				            }
		            		else
		            		{
					            int balance=UserCreation.getRc()-UserCreation.getZidAmount();					            
					            Account.setRc(balance);					            
					            //inserting transaction in transactions table					            
					            String insert="insert into transactions(userzid,transaction,transactionamount,sendzid,balance) values('"+UserCreation.getZid()+"','SendToZid','"+UserCreation.getZidAmount()+"','"+UserCreation.getSendZid()+"','"+balance+"')";
				    			mysql.mysqlInsert(insert);					            
					            //getting info of receiver 
				    			String query="select * from userinformation where zid='"+UserCreation.getSendZid()+"'";
				  	            ResultSet result=mysql.mysqlSelect(query);
					            int balanceOfSendZid=0;
					            if(result.next())
					            {
					            	balanceOfSendZid=result.getInt("rc");
					            }					            
					            balanceOfSendZid=balanceOfSendZid+UserCreation.getZidAmount();				            
					            //updating balance of receiver
					            String updaterc="update userinformation set rc='"+balanceOfSendZid+"' where zid='"+UserCreation.getSendZid()+"'";
				    			mysql.mysqlUpdate(updaterc);			    			
				                //inserting receiver transaction
				                String insertsendertrans="insert into transactions(userzid,transaction,transactionamount,sendzid,balance) values('"+UserCreation.getSendZid()+"','Received','"+UserCreation.getZidAmount()+"','"+UserCreation.getZid()+"','"+balanceOfSendZid+"')";
					    		mysql.mysqlInsert(insertsendertrans);
					            Account.setSendZid(0);
					            Account.setZidAmount(0);
					            //update the rc of sender
					            String updatesenderrc="update userinformation set rc='"+UserCreation.getRc()+"' where email='"+UserCreation.getEmail()+"'";
					            mysql.mysqlUpdate(updatesenderrc);
					            Account.setMessage("SendSuccess");
					            JSONObject obj=new JSONObject();
	        	       	  		obj.put("Status","SendSuccess");
	        	       	  	 	response.getWriter().print(obj);
		            		}
			            }
			            else
			            {
			            	//if password missmatch then display error
			            	   
			            	 Account.setMessage("SendZidNotPresent");			            	
					            Account.setSendZid(0);
					            Account.setZidAmount(0);					           
					            JSONObject obj=new JSONObject();
	        	       	  		obj.put("Status","SendZidNotPresent");
	        	       	  	 	response.getWriter().print(obj);	            			 
			            }
	            	}
	            }
	            else
	            {
	            		Account.setDepost(0);
	            		Account.setWithdraw(0);
	            		Account.setSendZid(0);
	            		Account.setZidAmount(0);            		
	            		Account.setMessage("WrongPassword");	            		            		
	            		JSONObject obj=new JSONObject();
      	       	  		obj.put("Status","WrongPassword");
      	       	  	 	response.getWriter().print(obj);        			 
	            }
		 }
		 catch(Exception ex)
	     {
			 System.out.println("Exception");
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