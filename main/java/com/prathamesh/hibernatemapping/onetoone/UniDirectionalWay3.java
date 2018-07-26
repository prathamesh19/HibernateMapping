package com.prathamesh.hibernatemapping.onetoone;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * 
 * onetoone unidirectional-----------onetoone+primarykeyjoincolumn
 * @author prathamesh
 *
 */
public class UniDirectionalWay3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Address3 ad1=new Address3(1, 411028, "pune");
		Address3 ad2=new Address3(2, 411029, "mumbai");
		Address3 ad3=new Address3(3, 411030, "nagpur");
		Employee3 e1=new Employee3(10, "abc", ad1);
		Employee3 e2=new Employee3(20, "pqr", ad2);
		Employee3 e3=new Employee3(30, "xyz", ad3);
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
	class Employee3 {

		@Id
		private int empId;
		private String empName;
		
		
//		@JoinTable(name="EmpMappingTable",joinColumns = @JoinColumn(name="empId"),inverseJoinColumns = @JoinColumn(name ="addressId"))
		@OneToOne(cascade=CascadeType.ALL)
		@PrimaryKeyJoinColumn
		private Address3 address;
		
		public Employee3() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Employee3(int empId, String empName, Address3 address) {
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
		public Address3 getAddress() {
			return address;
		}
		public void setAddress(Address3 address) {
			this.address = address;
		}
		@Override
		public String toString() {
			return "Employee [empId=" + empId + ", empName=" + empName + ", address=" + address + "]";
		}
		
	}

	@Entity
	@Table
	class Address3 {

		@Id
		private int addressId;
		private int pinCode;
		private String city;
		
		public Address3() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Address3(int addressId, int pinCode, String city) {
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

