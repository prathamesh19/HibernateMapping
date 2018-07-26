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
 * onetoone unidirectional---------onetoone+jointable
 * @author prathamesh
 *
 */
public class UniDirectionalWay2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Address2 ad1=new Address2(1, 411028, "pune");
		Address2 ad2=new Address2(2, 411029, "mumbai");
		Address2 ad3=new Address2(3, 411030, "nagpur");
		Employee2 e1=new Employee2(10, "abc", ad1);
		Employee2 e2=new Employee2(20, "pqr", ad2);
		Employee2 e3=new Employee2(30, "xyz", ad3);
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
	class Employee2 {

		@Id
		private int empId;
		private String empName;
		
//		@PrimaryKeyJoinColumn
		
		@OneToOne(cascade=CascadeType.ALL)
		@JoinTable(name="EmpMappingTable",joinColumns = @JoinColumn(name="empId"),inverseJoinColumns = @JoinColumn(name ="addressId"))
		private Address2 address;
		
		public Employee2() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Employee2(int empId, String empName, Address2 address) {
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
		public Address2 getAddress() {
			return address;
		}
		public void setAddress(Address2 address) {
			this.address = address;
		}
		@Override
		public String toString() {
			return "Employee [empId=" + empId + ", empName=" + empName + ", address=" + address + "]";
		}
		
	}

	@Entity
	@Table
	class Address2 {

		@Id
		private int addressId;
		private int pinCode;
		private String city;
		
		public Address2() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Address2(int addressId, int pinCode, String city) {
			super();
			this.addressId = addressId;
			this.pinCode = pinCode;
			this.city = city;
		}
		@Override
		public String toString() {
			return "Address [addressId=" + addressId + ", pinCode=" + pinCode + ", city=" + city + "]";
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
	}

