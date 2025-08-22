package com.example.jlmart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Connect {

    public static Connection con ;
    public static Statement stmt ;
    public int state;
    public  Connect(){
        state=1;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jlmart", "root", "jamila");
            stmt = con.createStatement();
        }catch (Exception e){
            state=0;
            System.out.println(e.getMessage());
        }
    }
}
