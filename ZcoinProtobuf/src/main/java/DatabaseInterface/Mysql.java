package DatabaseInterface;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.*;
public class Mysql 
{
	Connection con=ConnectionForMysql.getConnection();
	
	//Select query in mysql
	
	public ResultSet mysqlSelect(String query) throws Exception
	{
	        if(con!=null)
	        {
	            Statement st=con.createStatement();
	            ResultSet rs = st.executeQuery(query);
	            return rs;   
	        }
	        return null;
	}
	
	//update query in mysql
	
	public void mysqlUpdate(String query) throws Exception
	{
		  	if(con!=null)
	        {
	            Statement st=con.createStatement();
	            st.executeUpdate(query); 
	        }   
	}
	
	//insert query in mysql
	
	public void mysqlInsert(String query) throws Exception
	{
		  	if(con!=null)
	        {
	            Statement st=con.createStatement();
	            st.executeUpdate(query); 
	        }   
	}
}
