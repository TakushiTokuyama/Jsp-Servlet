package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import schedule.Plan;

public class PlanDAO {
	//データベース接続に関する情報
	private final String JDBC_URL = "jdbc:h2:file:C:/data/example";
	private final String DB_USER = "sa";
	private final String DB_PASS = "1";

	public ArrayList<Plan> findAll(){
		ArrayList<Plan> planList = new ArrayList<>();

	try(Connection conn = DriverManager.getConnection(
			JDBC_URL,DB_USER , DB_PASS)){

		String sql = "SELECT * FROM SS ORDER BY PLANTIME , FIRSTTIME ASC";
		PreparedStatement pStmt = conn.prepareStatement(sql);

		ResultSet rs = pStmt.executeQuery();

		while(rs.next()) {

			int id = rs.getInt("ID");
			int number = rs.getInt("NUMBER");
			String name = rs.getString("NAME");
			String planTime = rs.getString("PLANTIME");
		    String planCategory = rs.getString("PLANCATEGORY");
		    String firstTime = rs.getString("FIRSTTIME");
		    String lastTime = rs.getString("LASTTIME");
			String planText = rs.getString("PLANTEXT");
			Plan plan = new Plan(id , number , name ,  planTime , planCategory , firstTime , lastTime, planText);
			planList.add(plan);

		}
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return planList;
	}

	public boolean posting(Plan plan) {

		try(Connection conn = DriverManager.getConnection(JDBC_URL , DB_USER , DB_PASS))
		{

			String sql = "INSERT INTO SS(NUMBER , NAME , PLANTIME, PLANCATEGORY , FIRSTTIME , LASTTIME , PLANTEXT) VALUES(?,?,?,?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1,plan.getNumber());
			pStmt.setString(2,plan.getName());
			pStmt.setString(3,plan.getPlanTime());
			pStmt.setString(4,plan.getPlanCategory());
			pStmt.setString(5,plan.getFirstTime());
			pStmt.setString(6,plan.getLastTime());
			pStmt.setString(7,plan.getPlanText());

			int result = pStmt.executeUpdate();

			if(result != 1) {
				return false;
			}
	}catch (SQLException e) {
		e.printStackTrace();
		return false;
	}
	return true;
	}

    public boolean DeliteOne(int id , int number){

		try(Connection conn = DriverManager.getConnection(
				JDBC_URL , DB_USER , DB_PASS)) {

			String sql = "DELETE FROM SS WHERE ID = ? AND NUMBER = ? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1,id);
			pStmt.setInt(2,number);
			int result=pStmt.executeUpdate();

			if(result != 1){
				return false;
			}
	    }catch (SQLException e) {
	    	e.printStackTrace();
	    	return false;
	    }
		return true;
        }

    public ArrayList<Plan> findCategory(){

		ArrayList<Plan> twitterList = new ArrayList<Plan>();

		try(Connection conn = DriverManager.getConnection(JDBC_URL , DB_USER , DB_PASS))
		{

			String sql = "SELECT * FROM SS ORDER BY PLANCATEGORY ASC";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {

				int id = rs.getInt("ID");
				int number = rs.getInt("NUMBER");
				String name = rs.getString("NAME");
				String planTime = rs.getString("PLANTIME");
				String planCategory  = rs.getString("PLANCATEGORY");
				String firstTime = rs.getString("FIRSTTIME");
				String lastTime = rs.getString("LASTTIME");
				String planText = rs.getString("PLANTEXT");
				Plan plan = new Plan(id ,number , name ,  planTime , planCategory , firstTime , lastTime , planText);
				twitterList.add(plan);

			}

		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
       return twitterList;
	}

    public ArrayList<Plan> findName(){

		ArrayList<Plan> twitterList = new ArrayList<Plan>();

		try(Connection conn = DriverManager.getConnection(JDBC_URL , DB_USER , DB_PASS))
		{

			String sql = "SELECT * FROM SS ORDER BY NAME ASC";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();

			while(rs.next()) {

				int id = rs.getInt("ID");
				int number = rs.getInt("NUMBER");
				String name = rs.getString("NAME");
				String planTime = rs.getString("PLANTIME");
				String planCategory  = rs.getString("PLANCATEGORY");
				String firstTime = rs.getString("FIRSTTIME");
				String lastTime = rs.getString("LASTTIME");
				String planText = rs.getString("PLANTEXT");
				Plan plan = new Plan(id , number , name ,  planTime , planCategory , firstTime , lastTime , planText);
				twitterList.add(plan);
			}

		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
       return twitterList;
	}

    public ArrayList<Plan> findPlanTimeFirstTime(){

		ArrayList<Plan> twitterList = new ArrayList<Plan>();

		try(Connection conn = DriverManager.getConnection(JDBC_URL , DB_USER , DB_PASS))//データベース接続
		{

			String sql = "SELECT * FROM SS ORDER BY PLANTIME , FIRSTTIME ASC";//SELECT文
			PreparedStatement pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();//SELECT実行

			while(rs.next()) {

				int id = rs.getInt("ID");
				int number = rs.getInt("NUMBER");
				String name = rs.getString("NAME");
				String planTime = rs.getString("PLANTIME");
				String planCategory  = rs.getString("PLANCATEGORY");
				String firstTime = rs.getString("FIRSTTIME");
				String lastTime = rs.getString("LASTTIME");
				String planText = rs.getString("PLANTEXT");
				Plan plan = new Plan(id , number , name ,  planTime , planCategory , firstTime , lastTime , planText);
				twitterList.add(plan);

			}

		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
       return twitterList;
	}

    public ArrayList<Plan> findOne(String postId , int loginNumber) {

    	ArrayList<Plan> SSList = new ArrayList<Plan>();

    	try(Connection conn = DriverManager.getConnection(JDBC_URL , DB_USER , DB_PASS))
		{

    		String sql = "SELECT * FROM SS WHERE ID = ? AND NUMBER = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1,postId);
			pStmt.setInt(2,loginNumber);

			ResultSet rs = pStmt.executeQuery();

			while(rs.next()) {

				int id = rs.getInt("ID");
				int number = rs.getInt("NUMBER");
				String name = rs.getString("NAME");
				String planTime = rs.getString("PLANTIME");
				String planCategory  = rs.getString("PLANCATEGORY");
				String firstTime = rs.getString("FIRSTTIME");
				String lastTime = rs.getString("LASTTIME");
				String planText = rs.getString("PLANTEXT");
				Plan plan = new Plan(id , number , name , planTime , planCategory , firstTime , lastTime , planText);
				SSList.add(plan);

			}

    	}catch(SQLException e) {
    		e.printStackTrace();
    		System.out.println("更新失敗");
    	}
    	return SSList;
    }

    public boolean upData(Plan plan) {

    	try(Connection conn = DriverManager.getConnection(JDBC_URL , DB_USER , DB_PASS))
		{

    		String sql = "UPDATE SS SET  PLANTIME = ? , PLANCATEGORY = ? , FIRSTTIME = ? , LASTTIME = ? , PLANTEXT = ? WHERE ID = ? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1,plan.getPlanTime());
			pStmt.setString(2,plan.getPlanCategory());
			pStmt.setString(3,plan.getFirstTime());
			pStmt.setString(4,plan.getLastTime());
			pStmt.setString(5,plan.getPlanText());
			pStmt.setInt(6,plan.getId());

			int result = pStmt.executeUpdate();

			if(result != 1){
				return false;
			}
	    }catch (SQLException e) {
	    	e.printStackTrace();
	    	return false;
	    }
		return true;
        }
}
