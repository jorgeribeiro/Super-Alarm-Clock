package model;

public class Student {
	private String ID;
	private String name;
	private Contact contact;
	
	public Student() {}
	
	public Student(String iD, String name, Contact contact) {
		super();
		ID = iD;
		this.name = name;
		this.contact = contact;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

}
