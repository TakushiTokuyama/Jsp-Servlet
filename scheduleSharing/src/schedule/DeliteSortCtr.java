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


@WebServlet("/DeliteSortCtr")
public class DeliteSortCtr extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        int number = Integer.parseInt(request.getParameter("number"));

        PlanDAO dao = new PlanDAO();

			HttpSession session =request.getSession();
			
			ArrayList<User> loginUser = (ArrayList<User>) session.getAttribute("loginUserList");
			
			int loginNumber = loginUser.get(0).getNumber();
			//ログインしているのが投稿者
			if(loginNumber == number) {

			dao.DeliteOne(id , number);

			}else {
			//ログインしているのが投稿者ではない
			request.setAttribute("errorMessage", "自分の投稿しか消せません。");
			RequestDispatcher d = request.getRequestDispatcher
					("/WEB-INF/jsp/main.jsp");
			d.forward(request , response);
			
		}

		ArrayList<Plan> planList = dao.findAll();

		ServletContext application = this.getServletContext();

		application.setAttribute("planList" , planList );

		RequestDispatcher d = request.getRequestDispatcher
					("/WEB-INF/jsp/main.jsp");
	    d.forward(request , response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String sort = request.getParameter("sort");
      
		if(sort.equals("category")){

		PlanDAO dao = new PlanDAO();

		ArrayList<Plan> planList = dao.findCategory();

		ServletContext application = this.getServletContext();

		application.setAttribute("planList" , planList );

		RequestDispatcher d = request.getRequestDispatcher
					("/WEB-INF/jsp/main.jsp");
	    d.forward(request , response);

		}

		if(sort.equals("name")) {

		PlanDAO dao = new PlanDAO();

		ArrayList<Plan> planList = dao.findName();

		ServletContext application = this.getServletContext();

		application.setAttribute("planList" , planList );

		RequestDispatcher d = request.getRequestDispatcher
						("/WEB-INF/jsp/main.jsp");
		   d.forward(request , response);

		}

		if(sort.equals("deadLine")) {

			PlanDAO dao = new PlanDAO();

			dao.findPlanTimeFirstTime();

			ArrayList<Plan> planList = dao.findPlanTimeFirstTime();

			ServletContext application = this.getServletContext();

			application.setAttribute("planList" , planList );

			RequestDispatcher d = request.getRequestDispatcher
							("/WEB-INF/jsp/main.jsp");
			   d.forward(request , response);

			}

	}

}
