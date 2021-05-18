package Connect_DB;

import java.sql.*;

public class Connect_DB {
    static Connection con;
    static Statement stmt;
    static ResultSet rs;
    
    public void Use_DB(){
        try{
            con = DriverManager.getConnection("jdbc:mysql://118.67.130.238:3306/cse_swde?useUnicode=true&characterEncoding=UTF-8&characterSetResults=UTF-8", "test", "password");
            stmt = con.createStatement();
        }catch (SQLException e){
                e.printStackTrace();
        }
        
    }
    public void Command_Execute(String str){
        try{
            stmt.execute(str);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public ResultSet Command_ExecuteQuery(String str){
        try{
            rs = stmt.executeQuery(str);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return rs;
    }
    public void Command_ExecuteUpdate(String str){
        try{
            stmt.executeUpdate(str);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
