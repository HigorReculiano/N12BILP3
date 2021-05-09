package ec.ftt.model;

import java.util.Objects;

//JavaBean >> Serializable, Geters Seters, default contructor, [toString, equals, hash]
//POJO - Plain Old Java Objects

//https://www.devmedia.com.br/use-a-serializacao-em-java-com-seguranca/29012
//https://sites.google.com/site/sureshdevang/java-bean-v-s-pojo
//https://pt.wikipedia.org/wiki/Plain_Old_Java_Objects
//https://pt.wikipedia.org/wiki/JavaBeans


public class Employer {

	private long id;
	private String name,
	               age,
	               company,
				   role;
	private long companyId,
				 roleId;
	
	public Employer() {
		
	}
	public Employer(String id, String name, String age) {
		super();
		setId(id);
		setName(name);
		setAge(age);
	}
	
	public Employer(long id, String name, String age) {
		super();
		setId(id);
		setName(name);
		setAge(age);
	}
	
	public Employer(String name, String age) {
		super();
		setName(name);
		setAge(age);
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		
		if (id.length()==0)
			setId(0);
		else
			setId(Long.valueOf(id));
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", companyId=" + companyId + ", roleId=" + roleId +"]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Employer)) {
			return false;
		}
		Employer other = (Employer) obj;
		return Objects.equals(age, other.age) && id == other.id
				&& Objects.equals(name, other.name);
	}
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long company_id) {
		this.companyId = company_id;
	}
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
