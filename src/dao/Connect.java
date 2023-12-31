package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Vector;

public class Connect {
    Connection conn;
    public static String user;
    public static  String password;
    public Connection getConn() {
        try{//加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (Exception e){
            e.printStackTrace();
        }
        user = "root";//数据库登录名
        password ="root";//密码
        try { // 通过访问数据库的URL获取数据库连接对象
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/swingdemo?useUnicode=true&characterEncoding=utf8", user, password);
            System.out.println("数据库连接成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static void main(String[] args) { // 主方法，测试连接
        Connect c = new Connect(); // 创建本类对象
        c.getConn(); // 调用连接数据库的方法
        Vector<Integer> vector = new Vector<>();
        vector.add(1);

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        priorityQueue.offer(2);
        priorityQueue.offer(1);
        System.out.println(priorityQueue.peek());
    }
}
