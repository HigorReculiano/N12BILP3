package ec.ftt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ec.ftt.model.Company;
import ec.ftt.util.DBUtil;

public class CompanyDao {

    private Connection connection;

    public CompanyDao() {
        connection = DBUtil.getConnection();
    } //companyDao

    public void addCompany(Company company) {
    	try {
    		
    		System.out.println("Here we are...");
    		
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO company (name, cnpj) VALUES (?, ?)");
            
            // Parameters start with 1
            preparedStatement.setString(1, company.getName());
            preparedStatement.setString(2, company.getCnpj());

            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } //addcompany
    
    public void deleteCompany(Long id) {
    	
    	Company company = new Company();
    	company.setId(id);
    	
    	deleteCompany(company);
    	
    } // deletecompany long

    public void deleteCompany(Company company) {
        try {
        	PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM company WHERE ID=?");

            preparedStatement.setLong(1, company.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } //deletecompany

    public void updatecompany(Company company) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE company SET name=?, " 
                    		                          + "cnpj=?, "
                                               + "WHERE id=?");
                 
            // Parameters start with 1
            preparedStatement.setString(1, company.getName());
            preparedStatement.setString(2, company.getCnpj());
      
            preparedStatement.setLong(3, company.getId());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } //updatecompany

    public List<Company> getAllCompany() {
        
    	List<Company> companyList = new ArrayList<Company>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM company");
            while (rs.next()) {
                
            	Company company = new Company();
                
            	company.setId(rs.getLong("id"));
                company.setName(rs.getString("name"));
                company.setCnpj(rs.getString("cnpj"));

                companyList.add(company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return companyList;
    } //getAllcompany

    public Company getCompanyById(Long id) {
    	
    	Company company = new Company();
    	company.setId(id);
    	
    	return getCompanyById(company);
    	
    } // getCompanyById long
    
    
    	
    public Company getCompanyById(Company company) {

    	Company companyOutput = new Company();
        
    	try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * from company WHERE ID=?");
            
            preparedStatement.setLong(1, company.getId());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	companyOutput.setId(rs.getLong("id"));
            	companyOutput.setName(rs.getString("name"));
            	companyOutput.setCnpj(rs.getString("cnpj"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return companyOutput;
    } //getCompanyById
    
    public String getDbDate() {

    	String output="";
    	
    	try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT now() NOW");
            
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	output = rs.getString("NOW");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return output;
    } //getDbDate
    
} //companyDao