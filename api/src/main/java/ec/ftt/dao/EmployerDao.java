package ec.ftt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ec.ftt.model.Employer;
import ec.ftt.util.DBUtil;

public class EmployerDao {

    private Connection connection;

    public EmployerDao() {
        connection = DBUtil.getConnection();
    } //employerDao

    public void addemployer(Employer employer) {
    	try {
    		
    		System.out.println("Here we are...");
    		
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO ftt.employer (NAME, age, login) VALUES (?, ?, ?)");
            
            // Parameters start with 1
            preparedStatement.setString(1, employer.getName());
            preparedStatement.setString(2, employer.getAge());
            preparedStatement.setString(3, employer.getLogin());

            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } //addemployer
    
    public void deleteemployer(Long id) {
    	
    	Employer employer = new Employer();
    	employer.setId(id);
    	
    	deleteemployer(employer);
    	
    } // deleteemployer long

    public void deleteemployer(Employer employer) {
        try {
        	PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM employer WHERE ID=?");

            preparedStatement.setLong(1, employer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } //deleteemployer

    public void updateemployer(Employer employer) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE employer SET NAME=?, " 
                    		                          + "age=?, " 
                    		                          + "login=? " 
                                               + "WHERE ID=?");
                 
            // Parameters start with 1
            preparedStatement.setString(1, employer.getName());
            preparedStatement.setString(2, employer.getAge());
            //preparedStatement.setDate(3, (java.sql.Date)employer.getDob());
            preparedStatement.setString(3, employer.getLogin());
            
            preparedStatement.setLong(4, employer.getId());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } //updateemployer

    public List<Employer> getAllemployer() {
        
    	List<Employer> employerList = new ArrayList<Employer>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM ftt.employer");
            while (rs.next()) {
                
            	Employer employer = new Employer();
                
            	employer.setId(rs.getLong("ID"));
                employer.setName(rs.getString("NAME"));
                employer.setAge(rs.getString("age"));
                //employer.setDob(rs.getDate("DOB"));
                employer.setLogin(rs.getString("login"));

                employerList.add(employer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employerList;
    } //getAllemployer

    public Employer getemployerById(Long id) {
    	
    	Employer employer = new Employer();
    	employer.setId(id);
    	
    	return getemployerById(employer);
    	
    } // getemployerById long
    
    
    	
    public Employer getemployerById(Employer employer) {

    	Employer employerOutput = new Employer();
        
    	try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * from employer WHERE ID=?");
            
            preparedStatement.setLong(1, employer.getId());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	employerOutput.setId(rs.getLong("ID"));
            	employerOutput.setName(rs.getString("NAME"));
            	employerOutput.setAge(rs.getString("age"));
            	//employerOutput.setDob(rs.getDate("DOB"));
            	employerOutput.setLogin(rs.getString("login"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employerOutput;
    } //getemployerById
    
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
    
} //employerDao