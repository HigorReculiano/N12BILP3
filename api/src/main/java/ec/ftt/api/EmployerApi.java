package ec.ftt.api;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ec.ftt.dao.EmployerDao;
import ec.ftt.dao.RoleDao;
import ec.ftt.model.Employer;
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

@WebServlet("/employer")
public class EmployerApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployerDao employerDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployerApi() {
		super();
		this.employerDao = new EmployerDao();
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
		String employerId = request.getParameter("id");
		Gson gson = new Gson();

		if (Validator.isEmpty(employerId)) {
			List<Employer> employers = this.employerDao.getAllemployer();
			response.getWriter().append(gson.toJson(employers));
		} else {
			long id = Long.valueOf(employerId);
			Employer employer = this.employerDao.getemployerById(id);
			if (employer.getId() == 0) {
				Errors.notFound(response, "Employer with ID: "+ employerId+ " doesnt exists.");
				return;
			}
			response.getWriter().append(gson.toJson(employer));
		}
	} // doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Employer employer = Helper.getObjectFromJson(request.getReader(), Employer.class);

			RoleDao roleDao = new RoleDao();
			// ensure every employer added by API has employer role
			employer.setRoleId(roleDao.getRoleByDescription("employer").getId());

			if (Validator.isEmpty(employer.getLogin()) || Validator.isEmpty(employer.getName())
					|| employer.getCompanyId() <= 0) {
				response = Errors.badRequest(response, "Check if all required fields are correctly filled !");
				return;
			}

			this.employerDao.addEmployer(employer);
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
		try {
			String employerId = request.getParameter("id");

			if (Validator.isEmpty(employerId)) {
				Errors.badRequest(response, "Employer ID can not be empty");
				return;
			}
			
			long id = Long.valueOf(employerId);
			Employer employer = this.employerDao.getemployerById(id);
			if (employer.getId() == 0) {
				Errors.notFound(response, "Employer with ID: "+ employerId+ " doesnt exists.");
				return;
			}
			
			Employer employerChanges = Helper.getObjectFromJson(request.getReader(), Employer.class);
			
			if(Objects.isNull(employerChanges)) {
				Errors.badRequest(response, "Employer JSON is null");
				return;
			}
			
			Helper.merge(employer, employerChanges);
			this.employerDao.updateemployer(employer);
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
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String employerId = request.getParameter("id");

		if (Validator.isEmpty(employerId))
			response = Errors.badRequest(response, "Employer ID can not be empty !");

		Long employerIdInt = Long.valueOf(employerId);
		Employer e = this.employerDao.getemployerById(employerIdInt);

		if (e.getId() == 0)
			Errors.notFound(response, "Employer with ID:" + employerId + " doesn't exists");
		else {
			this.employerDao.deleteemployer(employerIdInt);
			response.setStatus(204);
		}
	}

}
