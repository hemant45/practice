package com.example.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConnection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/localdbsql","root","root");
			
			Statement st = con.createStatement();
			ResultSet rs= st.executeQuery("select * from adaptor_entity_configuration");
			
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"---"+rs.getInt(2)+"---"+rs.getString(3));
		}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
