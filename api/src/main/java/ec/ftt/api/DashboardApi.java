package ec.ftt.api;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ec.ftt.dao.DashboardDao;

@WebServlet("/dashboard")
public class DashboardApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DashboardDao dashboardDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DashboardApi() {
		super();
		// TODO Auto-generated constructor stub
		this.dashboardDao = new DashboardDao();
	}
	
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.setAccessControlHeaders(response);
		response.setStatus(HttpServletResponse.SC_OK);
	}
	
	protected void setAccessControlHeaders (HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		this.setAccessControlHeaders(response);
		List<Object> dashboardData = this.dashboardDao.getEmployersByCompany();
		response.getWriter().append(new Gson().toJson(dashboardData));
	} // doGet

}
