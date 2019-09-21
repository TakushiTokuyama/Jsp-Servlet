package schedule;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.PlanDAO;
import person.User;

@WebServlet("/UpDataCtr")
public class UpDataCtr extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("id");
		int number = Integer.parseInt(request.getParameter("number"));

		HttpSession session =request.getSession();
		ArrayList<User> loginUser = (ArrayList<User>) session.getAttribute("loginUserList");
		int loginNumber = loginUser.get(0).getNumber();
		//ログインしているのが投稿者
		if(loginNumber == number) {

			PlanDAO DAO = new PlanDAO();
			ArrayList<Plan> SSList= DAO.findOne(id, number);

			ServletContext application = this.getServletContext();

			application.setAttribute("SSList" , SSList );

			RequestDispatcher d = request.getRequestDispatcher
					("/WEB-INF/jsp/edit.jsp");
			d.forward(request , response);
		//ログインしているのが投稿者ではない
		}else {

			request.setAttribute("errorMessage", "自分の投稿しか編集できません。");
			RequestDispatcher d = request.getRequestDispatcher
					("/WEB-INF/jsp/main.jsp");
			d.forward(request , response);

		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		int id = Integer.parseInt(request.getParameter("id"));
		String planTime = request.getParameter("planTime");
		String planCategory = request.getParameter("planCategory");
		String firstTime = request.getParameter("firstTime");
		String lastTime = request.getParameter("lastTime");
		String planText = request.getParameter("planText");
		//入力なし
		if(planTime == "" || firstTime == "" || lastTime == "") {
			request.setAttribute("errorMessage", "日付と時間を入力してください");
			RequestDispatcher d = request.getRequestDispatcher
					("/WEB-INF/jsp/edit.jsp");
			d.forward(request , response);
		}

		boolean result = inputCheckLogic.planTimeCalc(planTime);
		boolean result2 = inputCheckLogic.timeCalc(firstTime , lastTime);
		//今と入力値を比べた結果
		if(result == false) {

			request.setAttribute("errorMessage", "正しい日付を入力してください");
			RequestDispatcher d = request.getRequestDispatcher
					("/WEB-INF/jsp/edit.jsp");
			d.forward(request , response);

		//今と入力値を比べた結果
		}else if(result2 == false){

			request.setAttribute("errorMessage", "正しい時間を入力してください。");
			RequestDispatcher d = request.getRequestDispatcher
					("/WEB-INF/jsp/edit.jsp");
			d.forward(request , response);

		}else {

		Plan plan = new Plan(id , planTime , planCategory , firstTime , lastTime , planText);

		PlanDAO dao = new PlanDAO();

		dao.upData(plan);

		ArrayList<Plan> planList = dao.findAll();

		ServletContext application = this.getServletContext();

		application.setAttribute("planList" , planList );

		RequestDispatcher d = request.getRequestDispatcher
					("/WEB-INF/jsp/main.jsp");
	    d.forward(request , response);

		}
	}
}
