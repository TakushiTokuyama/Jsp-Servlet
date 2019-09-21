package registration;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UsersDAO;
import person.User;


@WebServlet("/SignUpCtr")
public class SignUpCtr extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("newId");
		String password = request.getParameter("newPassword");
		String name = request.getParameter("name");

		loginCheckLogic newCheck = new loginCheckLogic();
		boolean Result = newCheck.checkIdPass(id , password);
		//入力値が正しい
		if(Result == true) {

		UsersDAO dao = new UsersDAO();

		boolean sqlCheckResult = dao.execute(id , password);
		//id password がsqlに登録されている
		if(sqlCheckResult == true) {

			request.setAttribute("errorMessage", "既に登録されています");
			RequestDispatcher d = request.getRequestDispatcher
					("/WEB-INF/jsp/signUp.jsp");
			d.forward(request , response);

		}else { //id passwor がsqlに新規登録されていない
		User user = new User(id , password , name);

		dao.signUp(user);

		request.setAttribute("resultMessage", "登録に成功しました");

		RequestDispatcher d = request.getRequestDispatcher
				("/index.jsp");
		d.forward(request , response);
		}
		//入力値が正しくない
		}else {

			request.setAttribute("errorMessage", "正しい値を入力してください");
			RequestDispatcher d = request.getRequestDispatcher
					("/WEB-INF/jsp/signUp.jsp");
			d.forward(request , response);

		}
	}
}
