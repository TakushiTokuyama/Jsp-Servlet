package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import person.User;

public class UsersDAO {
	//データベース接続に関する情報
	private final String JDBC_URL = "jdbc:h2:file:C:/data/example";
	private final String DB_USER = "sa";
	private final String DB_PASS = "1";

	public ArrayList<User> findAll(String loginId , String loginPassword){

		ArrayList<User> userList = new ArrayList<>();

		try(Connection conn = DriverManager.getConnection(
				JDBC_URL,DB_USER,DB_PASS)) {

			String sql = "SELECT NUMBER , ID , PASSWORD , NAME FROM SSUSERS";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();

			while(rs.next()) {

				int number = rs.getInt("NUMBER");
				String id = rs.getString("ID");
				String password = rs.getString("PASSWORD");
				String name = rs.getString("NAME");

				if(loginId.equals(id) && loginPassword.equals(password)) {
				User user = new User(number , id , password , name);
				userList.add(user);
				}
			}

			}catch (SQLException e) {//error
			 e.printStackTrace();
			 return null;
	}
	return userList;
	}

	public boolean signUp(User user) {

		try(Connection conn = DriverManager.getConnection(
				JDBC_URL,DB_USER,DB_PASS)) {

			String sql = "INSERT INTO SSUSERS(ID , PASSWORD , NAME) VALUES(?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

		    pStmt.setString(1, user.getId());
		    pStmt.setString(2, user.getPassword());
		    pStmt.setString(3, user.getName());

		    pStmt.executeUpdate();//実行

		}catch (SQLException e) {//error
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean execute(String id ,String password ) {

		try(Connection conn = DriverManager.getConnection(
				JDBC_URL,DB_USER,DB_PASS)) {

			String sql = "SELECT * FROM SSUSERS WHERE ID = ? AND PASSWORD = ? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);

		    pStmt.setString(1, id);
		    pStmt.setString(2, password);

		    ResultSet rs = pStmt.executeQuery();//実行

		    if(rs.next()) {
		   	return true;
		    }else {
		    return false;
		    }

		}catch (SQLException e) {//error
			e.printStackTrace();
			return false;
		}
	}

}