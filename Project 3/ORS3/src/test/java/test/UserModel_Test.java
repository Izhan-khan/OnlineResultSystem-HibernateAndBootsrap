package test;
 
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dto.UserDTO;
import exception.ApplicationException;
import exception.DuplicateRecordException;
import exception.RecordNotFoundException;
import model.ModelFactory;
import model.UserModel_Interface;
import utility.DataUtility;
 
/**
 * User Model Test classes
 *  
 * @author SUNRAYS Technologies
 * @version 1.0
 * 
 *  
 */
public class UserModel_Test {
 
    /**
     * Model object to test
     */
 
    // public static UserModelInt model = new UserModelHibImpl();
 
    public static UserModel_Interface model = ModelFactory.getInstance().getUserModel();
 
    /**
     * Main method to call test methods.
     *  
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args){
  //       testAdd();
//         testDelete();
//         testUpdate();
//         testFindByPK();
        // testFindByLogin();
        // testSearch();
         //testList();
         testAuthenticate();
        // testchangePassword();
        // testRegisterUser();
        // testforgetPassword();
       // testresetPassword();
    }
 
    /**
     * Tests add a User
     *  
     * @throws ParseException
     */
    public static void testAdd() {
 
        try {
            UserDTO dto = new UserDTO();
 
            // dto.setId(8L);
            
            for(int i=10;i<20;i++) {
            dto.setName("User "+i);
            dto.setLogin("user"+i+"@gmail.com");
            dto.setPassword("User@"+i);
            dto.setDob(Date.valueOf("1997-8-4"));
            dto.setRoleId(2l);
            dto.setGender("Male");
            dto.setMobileNo("9981"+Math.round(Math.random()*1000000));
            dto.setCreatedBy("Admin");
            dto.setModifiedBy("Admin");
            dto.setCreatedDatetime(Timestamp.from(Instant.now()));
            dto.setModifiedDatetime(Timestamp.from(Instant.now()));            
            long pk = model.add(dto);
            System.out.println("Successfully add "+i);
            System.out.println(dto.getDob());
            UserDTO addedDto = model.findByPK(pk);
            if (addedDto == null) {
                System.out.println("Test add fail");
            }
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        } catch (DuplicateRecordException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * Tests delete a User
     */
    public static void testDelete() {
 
        try {
            UserDTO dto = new UserDTO();
            long pk = 3L;
            dto.setId(pk);
            model.delete(dto);
            UserDTO deletedDto = model.findByPK(pk);
            if (deletedDto != null) {
                System.out.println("Test Delete fail");
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * Tests update a User
     */
    public static void testUpdate() {
 
        try {
            UserDTO dto = model.findByLogin("ranjitchoudha12ry20@gmail.com");
            dto.setMobileNo("9536565688");
 
            model.update(dto);
 
        } catch (ApplicationException e) {
            e.printStackTrace();
        } catch (DuplicateRecordException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * Tests find a User by PK.
     */
    public static void testFindByPK() {
        try {
            UserDTO dto = new UserDTO();
            long pk = 1L;
            dto = model.findByPK(pk);
            if (dto == null) {
                System.out.println("Test Find By PK fail");
            }
            System.out.println(dto.getId());
            System.out.println(dto.getName());
            System.out.println(dto.getLogin());
            System.out.println(dto.getPassword());
            System.out.println(dto.getDob());
            System.out.println(dto.getRoleId());
            System.out.println(dto.getGender());
            System.out.println(dto.getMobileNo());
            System.out.println(dto.getCreatedBy());
            System.out.println(dto.getModifiedBy());
            System.out.println(dto.getCreatedDatetime());
            System.out.println(dto.getModifiedDatetime());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
 
    }
 
    /**
     * Tests find a User by Login.
     */
    public static void testFindByLogin() {
        UserDTO dto = new UserDTO();
        try {
            dto = model.findByLogin("ranjitchoudhary20@gmail.com");
            if (dto == null) {
                System.out.println("Test Find By PK fail");
            }
            System.out.println(dto.getId());
            System.out.println(dto.getName());
            System.out.println(dto.getLogin());
            System.out.println(dto.getPassword());
            System.out.println(dto.getDob());
            System.out.println(dto.getRoleId());
            System.out.println(dto.getGender());
            System.out.println(dto.getMobileNo());
            System.out.println(dto.getCreatedBy());
            System.out.println(dto.getModifiedBy());
            System.out.println(dto.getCreatedDatetime());
            System.out.println(dto.getModifiedDatetime());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * Tests get List.
     */
    public static void testList() {
 
        try {
            UserDTO dto = new UserDTO();
            List list = new ArrayList();
            list = model.list(1, 10);
            if (list.size() < 0) {
                System.out.println("Test list fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                dto = (UserDTO) it.next();
                System.out.println(dto.getId());
                System.out.println(dto.getName());
                System.out.println(dto.getLogin());
                System.out.println(dto.getPassword());
                System.out.println(DataUtility.getDateString(dto.getDob()));
                System.out.println(dto.getRoleId());
                System.out.println(dto.getGender());
                System.out.println(dto.getMobileNo());
                System.out.println(dto.getCreatedBy());
                System.out.println(dto.getModifiedBy());
                System.out.println(dto.getCreatedDatetime());
                System.out.println(dto.getModifiedDatetime());
            }
 
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
 
 
    /**UserDTO dto = new UserDTO();
     * Tests get Search
     */
    public static void testSearch() {
 
        try {
            UserDTO dto = new UserDTO();
            List list = new ArrayList();
            // dto.setFirstName("ranjit");
            // dto.setLastName("ch");
            dto.setLogin("ranjitchoudhary20@gmail.com");
 
            list = model.search(dto, 0, 0);
            if (list.size() < 0) {
                System.out.println("Test Serach fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                dto = (UserDTO) it.next();
                System.out.println(dto.getId());
                System.out.println(dto.getName());
                System.out.println(dto.getLogin());
                System.out.println(dto.getPassword());
                System.out.println(dto.getDob());
                System.out.println(dto.getRoleId());
                System.out.println(dto.getGender());
                System.out.println(dto.getMobileNo());
                System.out.println(dto.getCreatedBy());
                System.out.println(dto.getModifiedBy());
                System.out.println(dto.getCreatedDatetime());
                System.out.println(dto.getModifiedDatetime());
            }
 
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
 
    }
 
    /**
     * Tests authenticate User.
     */
    public static void testAuthenticate() {
 
        try {
            UserDTO dto = new UserDTO();
            dto.setLogin("izhankhan002@gmail.com");
             dto.setPassword("12345");
            try {
				dto = model.authenticate(dto.getLogin(), dto.getPassword());
			} catch (RecordNotFoundException e) {
				e.printStackTrace();
			}
            if (dto != null) {
            	System.out.println(dto.getLogin()+" "+dto.getPassword());
            } else {
            }
 
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * Tests add a User
     * UserDTO dto = new UserDTO();
     * @throws ParseException
     */
    public static void testchangePassword()  {
 
        try {
            UserDTO dto = model.findByLogin("ranjitchoudhary20@gmail.com");
            String oldPassword = dto.getPassword();
            dto.setId(15l);
            dto.setPassword("rr");
            String newPassword = dto.getPassword();
            try {
                model.changePassword(15L, oldPassword, newPassword);
            } catch (RecordNotFoundException e) {
                e.printStackTrace();
            }
 
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
 
    }
 
    /**
     * Tests add a User register
     *  
     * @throws ParseException
     */
 
    public static void testRegisterUser() throws ParseException {
        try {
            UserDTO dto = new UserDTO();

            // dto.setId(8L);
            dto.setName("vipin");
            dto.setLogin("ranjitch11oudha12ry20@gmail.com");
            dto.setPassword("4444");
            dto.setDob(Date.valueOf("1997-8-4"));
            dto.setGender("Male");
            dto.setRoleId(2);
            long pk = model.registerUser(dto);
            System.out.println("Successfully register");
            System.out.println(dto.getName());
            System.out.println(dto.getLogin());
            System.out.println(dto.getDob());
            UserDTO registerDto = model.findByPK(pk);
            if (registerDto == null) {  
                System.out.println("Test add fail");
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        } catch (DuplicateRecordException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * Tests fogetPassword
     *  
     * @throws ParseException
     */
    public static void testforgetPassword() {
        try {
            boolean b = model.forgetPassword("ranjitchoudhary20@gmail.com");
 
            System.out.println("Suucess : Test Forget Password Success");
 
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
/**
     * Tests resetPassword
     *  
     * @throws ParseException
     */
    public static void testresetPassword()  {
        UserDTO dto = new UserDTO();
        try {
            dto = model.findByLogin("ranjitchoudhary20@gmail.com");
            if (dto != null) {
                boolean pass = model.resetPassword(dto);
                if (pass = false) {
                    System.out.println("Test Update fail");
                	}
            }
        }catch (ApplicationException e) {
            e.printStackTrace();
        }
 
    }
 
}