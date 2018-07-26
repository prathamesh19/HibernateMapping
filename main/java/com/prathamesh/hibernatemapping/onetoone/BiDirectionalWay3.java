package com.prathamesh.hibernatemapping.onetoone;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * 
 * onetoone bidirectional-----------onetoone+primarykeyjoincolumn
 * @author prathamesh
 *
 */
public class BiDirectionalWay3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Address7 ad1=new Address7(1, 411028, "pune",null);
		Address7 ad2=new Address7(2, 411029, "mumbai",null);
		Address7 ad3=new Address7(3, 411030, "nagpur",null);
		Employee7 e1=new Employee7(10, "abc", ad1);
		Employee7 e2=new Employee7(20, "pqr", ad2);
		Employee7 e3=new Employee7(30, "xyz", ad3);
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
	class Employee7 {

		@Id
		private int empId;
		private String empName;
		
		
//		@JoinTable(name="EmpMappingTable",joinColumns = @JoinColumn(name="empId"),inverseJoinColumns = @JoinColumn(name ="addressId"))
//		@PrimaryKeyJoinColumn
//		@JoinTable(name="EmpMappingTable",joinColumns = @JoinColumn(name="empId"),inverseJoinColumns = @JoinColumn(name ="addId"))
		
		@OneToOne(cascade=CascadeType.ALL)
		@PrimaryKeyJoinColumn
		private Address7 address;
		
		public Employee7() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Employee7(int empId, String empName, Address7 address) {
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
		public Address7 getAddress() {
			return address;
		}
		public void setAddress(Address7 address) {
			this.address = address;
		}
		@Override
		public String toString() {
			return "Employee [empId=" + empId + ", empName=" + empName + ", address=" + address + "]";
		}
		
	}

	@Entity
	@Table
	class Address7 {

		@Id
		private int addressId;
		private int pinCode;
		private String city;
		
		@OneToOne(cascade=CascadeType.ALL)
		private Employee7 employee;

		public Address7() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Address7(int addressId, int pinCode, String city, Employee7 employee) {
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

		public Employee7 getEmployee() {
			return employee;
		}

		public void setEmployee(Employee7 employee) {
			this.employee = employee;
		}

		@Override
		public String toString() {
			return "Address7 [addressId=" + addressId + ", pinCode=" + pinCode + ", city=" + city + ", employee="
					+ employee + "]";
		}
		
	}