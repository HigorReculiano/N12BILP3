package ec.ftt.dao;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ec.ftt.model.Dashboard;
import ec.ftt.model.Employer;
import ec.ftt.util.DBUtil;

public class DashboardDao {

	private Connection connection;

	public DashboardDao() {
		connection = DBUtil.getConnection();
	} // roleDao

	public List<Object> getEmployersByCompany() {
		List<Object> employerPerCompany = new ArrayList<Object>();
		
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT DISTINCT  COUNT(*) as count, c.name as company FROM employer e"
					+ " INNER JOIN company c ON c.id = e.company_id"
					+ " GROUP BY c.name");
			while (rs.next()) {
				String company = rs.getString("company");
				Integer count = rs.getInt("count");
				
				Dashboard dash = new Dashboard(company,count);
				
				employerPerCompany.add(dash);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employerPerCompany;
	} // getAllrole

} // roleDao