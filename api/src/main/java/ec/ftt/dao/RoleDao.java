package ec.ftt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ec.ftt.model.Role;
import ec.ftt.util.DBUtil;

public class RoleDao {

    private Connection connection;

    public RoleDao() {
        connection = DBUtil.getConnection();
    } //roleDao

    public void addRole(Role role) {
    	try {
    		
    		System.out.println("Here we are...");
    		
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO role (name, cnpj) VALUES (?)");
            
            // Parameters start with 1
            preparedStatement.setString(1, role.getDescription());

            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } //addrole
    
    public void deleteRole(Long id) {
    	
    	Role role = new Role();
    	role.setId(id);
    	
    	deleteRole(role);
    	
    } // deleterole long

    public void deleteRole(Role role) {
        try {
        	PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM role WHERE ID=?");

            preparedStatement.setLong(1, role.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } //deleterole

    public void updaterole(Role role) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE role SET description=? "
                                               + "WHERE id=?");
                 
            // Parameters start with 1
            preparedStatement.setString(1, role.getDescription());
            preparedStatement.setLong(2, role.getId());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } //updaterole

    public List<Role> getAllRole() {
        
    	List<Role> roleList = new ArrayList<Role>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM role");
            while (rs.next()) {
                
            	Role role = new Role();
                
            	role.setId(rs.getLong("id"));
                role.setDescription(rs.getString("description"));

                roleList.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roleList;
    } //getAllrole

    public Role getRoleById(Long id) {
    	
    	Role role = new Role();
    	role.setId(id);
    	
    	return getRoleById(role);
    	
    } // getRoleById long
    
    
    	
    public Role getRoleById(Role role) {

    	Role roleOutput = new Role();
        
    	try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * from role WHERE ID=?");
            
            preparedStatement.setLong(1, role.getId());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	roleOutput.setId(rs.getLong("id"));
            	roleOutput.setDescription(rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roleOutput;
    } //getRoleById
    
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
    
} //roleDao