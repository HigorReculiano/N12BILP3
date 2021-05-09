package ec.ftt.api;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ec.ftt.dao.RoleDao;
import ec.ftt.model.Role;
import ec.ftt.model.Role;
import ec.ftt.model.Role;
import ec.ftt.util.Errors;
import ec.ftt.util.Helper;
import ec.ftt.util.Validator;

/**
 * Servlet implementation class employerApi
 * 
 * CRUD -
 * 
 */

// TODO: PROJETO: CRIAR CRUD WEB + GRÁFICO PARA MAIS UMA TABELA COM MAIS CAMPOS PARA N1 2B
// TODO: PROJETO: PROJETO INDIVIDUAL OU NO MÁXIMO EM DUPLAS (EM DUPLAS 2 TABELAS)
// TODO: PROJETO: JavaScript Valina - CRUD em uma página - employer "fetch"
// TODO: PROJETO: Gerar gráfico com "Chart.js" https://www.chartjs.org/
// TODO: PROJETO: Trabalhar bem mensagens de erro da WEB API com try catch

@WebServlet("/role")
public class RoleApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RoleDao roleDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RoleApi() {
		super();
		// TODO Auto-generated constructor stub
		this.roleDao = new RoleDao();
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
		RoleDao roleDao = new RoleDao();
		Gson gson = new Gson();
		String roleId = request.getParameter("id");

		if (Validator.isEmpty(roleId)) {
			List<Role> roles = roleDao.getAllRole();
			response.getWriter().append(gson.toJson(roles));
		} else {
			long id = Long.valueOf(roleId);
			Role role = roleDao.getRoleById(id);
			response.getWriter().append(gson.toJson(role));
		}
	} // doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			this.setAccessControlHeaders(response);
			Role role = Helper.getObjectFromJson(request.getReader(), Role.class);
			if (Validator.isEmpty(role.getDescription())) {
				response = Errors.badRequest(response, "Check if all required fields are correctly filled !");
				return;
			}

			this.roleDao.addRole(role);
			response.setStatus(204);
		} catch (SQLException e) {
			if (e instanceof SQLIntegrityConstraintViolationException) {
				Errors.conflict(response, "unique key login already exists !");
			}
			e.printStackTrace();
		} catch (Exception e) {
			Errors.serverError(response, e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.setAccessControlHeaders(response);
		String roleId = request.getParameter("id");

		if (Validator.isEmpty(roleId)) {
			response = Errors.badRequest(response, "Role ID can not be empty !");
		} else {
			Role employer = Helper.getObjectFromJson(request.getReader(), Role.class);

			response.getWriter().append(new Gson().toJson(employer));
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.setAccessControlHeaders(response);
		String roleId = request.getParameter("id");

		if (Validator.isEmpty(roleId))
			response = Errors.badRequest(response, "Role ID can not be empty !");
		else {
			Long roleIdInt = Long.valueOf(request.getParameter("employer-id"));

			RoleDao ud = new RoleDao();

			ud.deleteRole(roleIdInt);

			response.getWriter().append(request.getParameter("employer-id") + " employer removido");
		}
	}

}
