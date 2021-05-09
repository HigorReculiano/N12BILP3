package ec.ftt.model;

import java.util.Objects;

//JavaBean >> Serializable, Geters Seters, default contructor, [toString, equals, hash]
//POJO - Plain Old Java Objects

//https://www.devmedia.com.br/use-a-serializacao-em-java-com-seguranca/29012
//https://sites.google.com/site/sureshdevang/java-bean-v-s-pojo
//https://pt.wikipedia.org/wiki/Plain_Old_Java_Objects
//https://pt.wikipedia.org/wiki/JavaBeans

public class Role {

	private long id;
	private String description;

	public Role() {

	}

	public Role(String id, String description) {
		super();
		setId(id);
		setDescription(description);

	}

	public Role(long id, String description) {
		super();
		setId(id);
		setDescription(description);

	}

	public Role(String description) {
		super();
		setDescription(description);

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

		if (id.length() == 0)
			setId(0);
		else
			setId(Long.valueOf(id));
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, description);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Role)) {
			return false;
		}
		Role other = (Role) obj;
		return id == other.id && Objects.equals(description, other.description);
	}
}
