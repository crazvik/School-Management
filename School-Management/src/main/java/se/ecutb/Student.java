package se.ecutb;

import java.util.Objects;

public class Student {
	private int id;
	private String name;
	private String email;
	private String address;
	
	public Student(int id, String name, String email, String address) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.address = address;
	}
	
	public void setId(int n) {
		id = n;
	}
	
	public int getId() {
		return id;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}
	
	public void setEmail(String mail) {
		email = mail;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setAddress(String ad) {
		address = ad;
	}
	
	public String getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", address='" + address + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Student student = (Student) o;
		return id == student.id && Objects.equals(name, student.name) && Objects.equals(email, student.email) && Objects.equals(address, student.address);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, email, address);
	}
}