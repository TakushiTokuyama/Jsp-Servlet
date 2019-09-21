package registration;

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
import DAO.UsersDAO;
import person.User;
import schedule.Plan;


@WebServlet("/LoginCtr")
public class LoginCtr extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher d = request.getRequestDispatcher
				("/WEB-INF/jsp/signUp.jsp");
		d.forward(request , response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("id");
		String password = request.getParameter("password");

		loginCheckLogic check = new loginCheckLogic();
		boolean result = check.checkIdPass(id , password);
		//入力値が正しい
		if(result == true) {

			UsersDAO userDao = new UsersDAO();

			boolean sqlResult = userDao.execute(id , password);
			//新規登録されている
			if(sqlResult == true) {

			PlanDAO planDao = new PlanDAO();
			UsersDAO usersDao = new UsersDAO();

			ArrayList<User> loginUserList = usersDao.findAll(id , password);

			HttpSession session =request.getSession();

			session.setAttribute("loginUserList" , loginUserList );

			ArrayList<Plan> planList = planDao.findAll();

			ServletContext application = this.getServletContext();

		    application.setAttribute("planList" , planList );

			RequestDispatcher d = request.getRequestDispatcher
					("/WEB-INF/jsp/main.jsp");
			d.forward(request , response);
			//新規登録されていない
			}else{

				request.setAttribute("errorMessage", "登録されていません");
				RequestDispatcher d = request.getRequestDispatcher
						("/index.jsp");
				d.forward(request , response);

			}
		//入力値が正しくない
		}else {

			request.setAttribute("errorMessage", "IDまたはパスワードが正しく入力されていません");
			RequestDispatcher d = request.getRequestDispatcher
					("/index.jsp");
			d.forward(request , response);

		}
	}
}
