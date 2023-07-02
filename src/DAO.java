import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAO {

    public ResultSet query(String sql){
        Connection connection = new Connect().getConn();

        ResultSet resultSet = null;
        try{
            Statement statement=connection.createStatement();
            resultSet = statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public Integer update(String sql){
        Connection connection = new Connect().getConn();

        Integer rows = null;
        try{
            Statement statement=connection.createStatement();
            rows = statement.executeUpdate(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows;
    }
}
