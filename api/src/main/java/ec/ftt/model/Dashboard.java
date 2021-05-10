package ec.ftt.model;

import java.util.Objects;

//JavaBean >> Serializable, Geters Seters, default contructor, [toString, equals, hash]
//POJO - Plain Old Java Objects

//https://www.devmedia.com.br/use-a-serializacao-em-java-com-seguranca/29012
//https://sites.google.com/site/sureshdevang/java-bean-v-s-pojo
//https://pt.wikipedia.org/wiki/Plain_Old_Java_Objects
//https://pt.wikipedia.org/wiki/JavaBeans


public class Dashboard {

	private String company;
	private Integer count;
	
	public Dashboard() {
		
	}
	public Dashboard(String company, Integer count) {
		super();
		this.setCompany(company);
		this.setCount(count);
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}

}
