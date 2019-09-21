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


@WebServlet("/MainCtr")
public class MainCtr extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String planTime = request.getParameter("planTime");
		String planCategory = request.getParameter("planCategory");
		String firstTime = request.getParameter("firstTime");
		String lastTime = request.getParameter("lastTime");
		String planText = request.getParameter("planText");
		
		//入力なし
		if(planTime == "" || firstTime == "" || lastTime == "") {
			
			request.setAttribute("errorMessage", "日付と時間を入力してください");
			RequestDispatcher d = request.getRequestDispatcher
					("/WEB-INF/jsp/main.jsp");
			d.forward(request , response);
			
		}

		boolean result = inputCheckLogic.planTimeCalc(planTime);
		boolean result2 = inputCheckLogic.timeCalc(firstTime , lastTime);
		//今と入力した値を比べた結果
		if(result == false) {
			
			request.setAttribute("errorMessage", "正しい日付を入力してください");
			RequestDispatcher d = request.getRequestDispatcher
					("/WEB-INF/jsp/main.jsp");
			d.forward(request , response);
			
		}
		//今と入力した値を比べた結果
		else if(result2 == false){
			
			request.setAttribute("errorMessage", "正しい時間を入力してください。");
			RequestDispatcher d = request.getRequestDispatcher
					("/WEB-INF/jsp/main.jsp");
			d.forward(request , response);
			
		}else {

		HttpSession session =request.getSession();

		ArrayList<User> loginUser = (ArrayList<User>) session.getAttribute("loginUserList");

		String name = loginUser.get(0).getName();
		int number = loginUser.get(0).getNumber();

		Plan plan = new Plan(number , name , planTime , planCategory , firstTime , lastTime , planText);

		PlanDAO dao = new PlanDAO();

		dao.posting(plan);

		ArrayList<Plan> planList = dao.findAll();

		ServletContext application = this.getServletContext();

		application.setAttribute("planList" , planList );

		RequestDispatcher d = request.getRequestDispatcher
				("/WEB-INF/jsp/main.jsp");
		d.forward(request , response);
		}
	}

}
