package com.prathamesh.hibernatemapping.onetoone;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
/**
 * 
 * 
 * onetoone bidirectional-------onetoone+jointable
 * @author prathamesh
 *
 */
public class BiDirectionalWay2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Address6 ad1=new Address6(1, 411028, "pune",null);
		Address6 ad2=new Address6(2, 411029, "mumbai",null);
		Address6 ad3=new Address6(3, 411030, "nagpur",null);
		Employee6 e1=new Employee6(10, "abc", ad1);
		Employee6 e2=new Employee6(20, "pqr", ad2);
		Employee6 e3=new Employee6(30, "xyz", ad3);
		ad1.setEmployee(e1);
		ad2.setEmployee(e2);
		ad3.setEmployee(e3);
				Configuration configuration = new Configuration().configure("/hibernate.cfg.xml");
				SessionFactory sessionFactory=configuration.buildSessionFactory();
				Session session=sessionFactory.openSession();
				Transaction transaction =session.beginTransaction();
				session.save(e1);
				session.save(e2);
				session.save(e3);
				session.flush();
				transaction.commit();
			}

		}
		


	@Entity
	@Table
	class Employee6 {

		@Id
		private int empId;
		private String empName;
		
		
//		@JoinTable(name="EmpMappingTable",joinColumns = @JoinColumn(name="empId"),inverseJoinColumns = @JoinColumn(name ="addressId"))
//		@PrimaryKeyJoinColumn
		@OneToOne(cascade=CascadeType.ALL)
		@JoinTable(name="EmpMappingTable",joinColumns = @JoinColumn(name="empId"),inverseJoinColumns = @JoinColumn(name ="addressId"))
		private Address6 address;
		
		public Employee6() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Employee6(int empId, String empName, Address6 address) {
			super();
			this.empId = empId;
			this.empName = empName;
			this.address = address;
		}
		public int getEmpId() {
			return empId;
		}
		public void setEmpId(int empId) {
			this.empId = empId;
		}
		public String getEmpName() {
			return empName;
		}
		public void setEmpName(String empName) {
			this.empName = empName;
		}
		public Address6 getAddress() {
			return address;
		}
		public void setAddress(Address6 address) {
			this.address = address;
		}
		@Override
		public String toString() {
			return "Employee [empId=" + empId + ", empName=" + empName + ", address=" + address + "]";
		}
		
	}

	@Entity
	@Table
	class Address6 {

		@Id
		private int addressId;
		private int pinCode;
		private String city;
		
		@OneToOne(cascade=CascadeType.ALL)
		private Employee6 employee;

		public Address6() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Address6(int addressId, int pinCode, String city, Employee6 employee) {
			super();
			this.addressId = addressId;
			this.pinCode = pinCode;
			this.city = city;
			this.employee = employee;
		}

		public int getAddressId() {
			return addressId;
		}

		public void setAddressId(int addressId) {
			this.addressId = addressId;
		}

		public int getPinCode() {
			return pinCode;
		}

		public void setPinCode(int pinCode) {
			this.pinCode = pinCode;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public Employee6 getEmployee() {
			return employee;
		}

		public void setEmployee(Employee6 employee) {
			this.employee = employee;
		}

		@Override
		public String toString() {
			return "Address6 [addressId=" + addressId + ", pinCode=" + pinCode + ", city=" + city + ", employee="
					+ employee + "]";
		} 
	}
