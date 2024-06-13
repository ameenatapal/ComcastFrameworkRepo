package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.protocol.Resultset;
import com.mysql.jdbc.Driver;

public class DataBaseUtility {
     
	Connection con;
	public void getDbconnection(String url,String username,String password) throws Throwable
	{
		try
		{
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		
		Connection con= DriverManager.getConnection(url,username,password);
		}catch(Exception e)
		{
			}
		}
	
	
	//default DB connect
	public void getDbconnection() throws Throwable
	{
		try
		{
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		
		Connection con= DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects\", \"root@%\", \"root");
		}catch(Exception e)
		{
			}
		}
	
	public void closeDbConnection() throws SQLException
	{
		try
		{
		con.close();
		}catch(Exception e)
		{
			
		}
	}
	
	public Resultset executeSelectQuery(String query)throws Throwable
	{
		ResultSet result=null;
		try
		{
		Statement stat = con.createStatement();
		result=stat.executeQuery(query);
		}catch(Exception e)
		{
			}
		return (Resultset) result;
		}
	public int executeNonselectQuery(String query) {
		int result= 0; 
		
		try
		{
		Statement stat = con.createStatement();
		result=stat.executeUpdate(query);
		}catch(Exception e)
		{
			}
		return result;
		
	}
	
	
}
