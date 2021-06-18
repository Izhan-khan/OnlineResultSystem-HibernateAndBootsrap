package test;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import dto.CourseDTO;
import exception.ApplicationException;
import exception.DuplicateRecordException;
import model.CourseModel_Hibernate_Implement;
import model.CourseModel_Interface;
import model.CourseModel_JDBC_Implement;

/**
 * Course Model Test classes
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * 
 * 
 */
public class CourseModel_Test {

	static CourseDTO dto = new CourseDTO();
	
	static CourseModel_Interface model = new CourseModel_JDBC_Implement();
	
	public static void main(String[] args) {
		 
		testSearch();
		
	}

	
	public static void testAdd() {

		dto.setName("Civl");
		dto.setDescription("Study of Machines");
		dto.setDuration(4L);
		dto.setCreatedBy("Admin");
		dto.setModifiedBy("Admin");
		dto.setCreatedDatetime(Timestamp.from(Instant.now()));
		dto.setModifiedDatetime(Timestamp.from(Instant.now()));
		
		
		try {
			model.add(dto);
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}
	
	
	}
 
	public static void testFindByPK() {
		
			try {
				CourseDTO dto =model.findByPK(2);
				
				System.out.println(dto.getId());
				System.out.println(dto.getName());
				System.out.println(dto.getDescription());
				System.out.println(dto.getDuration());
				
			} catch (ApplicationException e) {
				e.printStackTrace();
			}

	}
	
	public static void testFindByName() {	 

		try {
			CourseDTO dto =model.findByName("B.E");
			
			System.out.println(dto.getId());
			System.out.println(dto.getName());
			System.out.println(dto.getDescription());
			System.out.println(dto.getDuration());
			
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testDelete() {
		
		dto.setId(5L);
		
		try {
			model.delete(dto);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	public static void testSearch() {
		
		dto.setDuration(3l);
		List list = null; 
		try {
		list =	 model.search(dto);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator it = list.iterator();
		
		while(it.hasNext()) {
			dto = (CourseDTO) it.next();
			System.out.println(dto.getName());
			System.out.println(dto.getDescription());
			System.out.println(dto.getDuration());
			
		}
	}
	
	public static void testList() {
		
		List list = null; 
		try {
		list =	 model.list();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator it = list.iterator();
		
		while(it.hasNext()) {
			dto = (CourseDTO) it.next();
			System.out.println(dto.getName());
			System.out.println(dto.getDescription());
			System.out.println(dto.getDuration());
			
		}
	}

	public static void testUpdate() {
		

		dto.setName("Astrology");
		dto.setDescription("Study of Space");
		dto.setDuration(4L);
		dto.setCreatedBy("Admin");
		dto.setModifiedBy("Admin");
		dto.setCreatedDatetime(Timestamp.from(Instant.now()));
		dto.setModifiedDatetime(Timestamp.from(Instant.now()));
		dto.setId(4L);
		
		try {
			model.update(dto);
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}
		
	}	

	
}	
	