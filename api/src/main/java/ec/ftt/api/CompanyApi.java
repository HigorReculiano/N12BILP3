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

import ec.ftt.dao.CompanyDao;
import ec.ftt.model.Company;
import ec.ftt.util.Errors;
import ec.ftt.util.Helper;
import ec.ftt.util.Validator;

@WebServlet("/company")
public class CompanyApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CompanyDao companyDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CompanyApi() {
		super();
		// TODO Auto-generated constructor stub
		this.companyDao = new CompanyDao();
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
	
	
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.setAccessControlHeaders(response);
		response.setStatus(HttpServletResponse.SC_OK);
	}
	
	protected void setAccessControlHeaders (HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		response.setHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.setAccessControlHeaders(response);
		CompanyDao companyDao = new CompanyDao();
		Gson gson = new Gson();
		String companyId = request.getParameter("id");

		if (Validator.isEmpty(companyId)) {
			List<Company> companies = companyDao.getAllCompany();
			response.getWriter().append(gson.toJson(companies));
		} else {
			long id = Long.valueOf(companyId);
			Company company = companyDao.getCompanyById(id);
			response.getWriter().append(gson.toJson(company));
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
			Company company = Helper.getObjectFromJson(request.getReader(), Company.class);
			if (Validator.isEmpty(company.getName()) || Validator.isEmpty(company.getCnpj())) {
				response = Errors.badRequest(response, "Check if all required fields are correctly filled !");
				return;
			}

			this.companyDao.addCompany(company);
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
			this.setAccessControlHeaders(response);
			String companyId = request.getParameter("id");

			if (Validator.isEmpty(companyId)) {
				Errors.badRequest(response, "Company ID can not be empty");
				return;
			}

			long id = Long.valueOf(companyId);
			Company company = this.companyDao.getCompanyById(id);
			if (company.getId() == 0) {
				Errors.notFound(response, "Company with ID: " + companyId + " doesnt exists.");
				return;
			}

			Company companyChanges = Helper.getObjectFromJson(request.getReader(), Company.class);

			if (Objects.isNull(companyChanges)) {
				Errors.badRequest(response, "Company JSON is null");
				return;
			}

			Helper.merge(company, companyChanges);
			this.companyDao.updatecompany(company);
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
		this.setAccessControlHeaders(response);
		String companyId = request.getParameter("id");

		if (Validator.isEmpty(companyId))
			response = Errors.badRequest(response, "Employer ID can not be empty !");

		Long companyIdInt = Long.valueOf(companyId);
		Company e = this.companyDao.getCompanyById(companyIdInt);

		if (e.getId() == 0)
			Errors.notFound(response, "Employer with ID:" + companyId + " doesn't exists");
		else {
			this.companyDao.deleteCompany(companyIdInt);
			response.setStatus(204);
		}
	}

}
