package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DAO {

    Connection connection = new Connect().getConn();

    public ResultSet query(String sql){

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
