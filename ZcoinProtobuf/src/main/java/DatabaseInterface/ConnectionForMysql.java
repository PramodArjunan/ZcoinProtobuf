package DatabaseInterface;

import java.sql.Connection;
import java.sql.DriverManager;
public class ConnectionForMysql
{
	
	//mysql connection
	
    static Connection con = null;
    static
    {
        String url = "jdbc:mysql://localhost:3306/zcoin";
        String user = "root";
        String pass = "pramod";
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static Connection getConnection()
    {
        return con;
    }
}