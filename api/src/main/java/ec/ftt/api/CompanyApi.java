package ec.ftt.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ec.ftt.dao.CompanyDao;
import ec.ftt.model.Company;
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

@WebServlet("/company")
public class CompanyApi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CompanyApi() {
		super();
		// TODO Auto-generated constructor stub
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
		Employer employer = Helper.getObjectFromJson(request.getReader(), Employer.class);

		if (Validator.isEmpty(employer.getLogin()) || Validator.isEmpty(employer.getName())) {
			response = Errors.badRequest(response, "Check if all required fields are correctly filled !");
			return;
		}
		response.getWriter().append(new Gson().toJson(employer));
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Ajustar errors com try catch

		String companyId = request.getParameter("id");

		if (Validator.isEmpty(companyId)) {
			response = Errors.badRequest(response, "Employer ID can not be empty !");
		} else {
			Employer employer = Helper.getObjectFromJson(request.getReader(), Employer.class);

			response.getWriter().append(new Gson().toJson(employer));
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// https://www.tutorialspoint.com/servlets/servlets-http-status-codes.htm

		// TODO Verificar se está enviando o parametro
		// TODO Verificar se o parametro é null
		// TODO Se o ID já foi apagado
		// TODO Verificar se o ID não existe...
		// TODO Usar try cath para propagar erro appropriadamente...
		// TODO क्या आप इस कोड को अपने जीवन की महिला को दिखाने की हिम्मत करेंगे ???
		// TODO మీ జీవితంలోని స్త్రీకి ఈ కోడ్ చూపించడానికి మీకు ధైర్యం ఉందా ???

		// Reference:
		// https://www.tutorialspoint.com/servlets/servlets-http-status-codes.htm
		//
		String companyId = request.getParameter("id");

		if (Validator.isEmpty(companyId))
			response = Errors.badRequest(response, "Employer ID can not be empty !");
		else {
			Long companyIdInt = Long.valueOf(request.getParameter("employer-id"));

			CompanyDao ud = new CompanyDao();

			ud.deleteCompany(companyIdInt);

			response.getWriter().append(request.getParameter("employer-id") + " employer removido");
		}
	}

}
