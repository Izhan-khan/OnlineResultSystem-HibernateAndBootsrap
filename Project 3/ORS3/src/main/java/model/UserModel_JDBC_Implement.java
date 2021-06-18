package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import dto.UserDTO;
import exception.ApplicationException;
import exception.DatabaseException;
import exception.DuplicateRecordException;
import exception.RecordNotFoundException;
import utility.EmailBuilder;
import utility.EmailMessage;
import utility.EmailUtility;
import utility.JDBCDataSource;

/**
 * JDBC Implementation of UserModel
 *
 * @author SUNRAYS Technologies
 * @version 1.0
 * 
 */
public class UserModel_JDBC_Implement implements UserModel_Interface {
    private static Logger log = Logger.getLogger(UserModel_JDBC_Implement.class);

    /**
     * Find next PK of user
     *
     * @throws DatabaseException
     */
    public Integer nextPK() throws DatabaseException {
        log.info("Model nextPK Started");
        Connection conn = null;
        int pk = 0;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn
                    .prepareStatement("SELECT MAX(ID) FROM USER");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                pk = rs.getInt(1);
            }
            rs.close();

        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new DatabaseException("Exception : Exception in getting PK");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.info("Model nextPK End");
        return pk + 1;
    }

    /**
     * Add a User
     *
     * @param dto
     * @throws ApplicationException
     *
     */
    public long add(UserDTO dto) throws ApplicationException,
            DuplicateRecordException {
        log.info("Model add Started");
        Connection conn = null;
        int pk = 0;

        UserDTO existDto = findByLogin(dto.getLogin());

        if (existDto != null) {
            throw new DuplicateRecordException("Login Id already exists");
        }

        try {
            conn = JDBCDataSource.getConnection();
            pk = nextPK();
            // Get auto-generated next primary key
            System.out.println(pk + " in ModelJDBC");
            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("INSERT INTO USER VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
            pstmt.setInt(1, pk);
            pstmt.setString(2, dto.getName());
            pstmt.setString(3, dto.getLogin());
            pstmt.setString(4, dto.getPassword());
            pstmt.setDate(5, new java.sql.Date(dto.getDob().getTime()));
            pstmt.setString(6, dto.getMobileNo());
            pstmt.setLong(7, dto.getRoleId());
            pstmt.setString(8, dto.getGender());
            pstmt.setString(9, dto.getCreatedBy());
            pstmt.setString(10, dto.getModifiedBy());
            pstmt.setTimestamp(11,(dto.getCreatedDatetime()));
            pstmt.setTimestamp(12,(dto.getModifiedDatetime()));
            pstmt.executeUpdate();
            conn.commit(); // End transaction
            pstmt.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            try {
                conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new ApplicationException(
                        "Exception : add rollback exception " + ex.getMessage());
            }
            throw new ApplicationException("Exception : Exception in add User");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.info("Model add End");
        return pk;
    }

    /**
     * Delete a User
     *
     * @param dto
     * @throws ApplicationException
     */
    public void delete(UserDTO dto) throws ApplicationException {
        log.info("Model delete Started");
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("DELETE FROM USER WHERE ID=?");
            pstmt.setLong(1, dto.getId());
            pstmt.executeUpdate();
            conn.commit(); // End transaction
            pstmt.close();

        } catch (Exception e) {
            log.error("Database Exception..", e);
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException(
                        "Exception : Delete rollback exception "
                                + ex.getMessage());
            }
            throw new ApplicationException(
                    "Exception : Exception in delete User");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.info("Model delete Started");
    }

    /**
     * Find User by Login
     *
     * @param login
     *            : get parameter
     * @return dto
     * @throws ApplicationException
     */

   public UserDTO findByLogin(String login) throws ApplicationException {
        log.info("Model findByLogin Started");
        StringBuffer sql = new StringBuffer(
                "SELECT * FROM USER WHERE LOGIN=?");
        UserDTO dto = null;
        Connection conn = null;
        System.out.println("sql" + sql);

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, login);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                dto = new UserDTO();
                dto.setId(rs.getLong(1));
                dto.setName(rs.getString(2));
                dto.setLogin(rs.getString(3));
                dto.setPassword(rs.getString(4));
                dto.setDob(rs.getDate(5));
                dto.setMobileNo(rs.getString(6));
                dto.setRoleId(rs.getLong(7));
                dto.setGender(rs.getString(8));
                dto.setCreatedBy(rs.getString(9));
                dto.setModifiedBy(rs.getString(10));
                dto.setCreatedDatetime(rs.getTimestamp(11));
                dto.setModifiedDatetime(rs.getTimestamp(12));

            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting User by login");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.info("Model findByLogin End");
        return dto;
    }

    /**
     * Find User by PK
     *
     * @param pk
     *            : get parameter
     * @return dto
     * @throws ApplicationException
     */
    public UserDTO findByPK(long pk) throws ApplicationException {
        log.info("Model findByPK Started");
        StringBuffer sql = new StringBuffer("SELECT * FROM USER WHERE ID=?");
        UserDTO dto = null;
        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setLong(1, pk);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                dto = new UserDTO();
                dto.setId(rs.getLong(1));
                dto.setName(rs.getString(2));
                dto.setLogin(rs.getString(3));
                dto.setPassword(rs.getString(4));
                dto.setDob(rs.getDate(5));
                dto.setMobileNo(rs.getString(6));
                dto.setRoleId(rs.getLong(7));
                dto.setGender(rs.getString(8));
                dto.setCreatedBy(rs.getString(9));
                dto.setModifiedBy(rs.getString(10));
                dto.setCreatedDatetime(rs.getTimestamp(11));
                dto.setModifiedDatetime(rs.getTimestamp(12));


            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting User by pk");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.info("Model findByPK End");
        return dto;
    }

    /**
     * Update a user
     *
     * @param dto
     * @throws ApplicationException,DuplicateRecordException
     */
    public void update(UserDTO dto) throws ApplicationException,
            DuplicateRecordException {
        log.info("Model update Started");
        Connection conn = null;

        UserDTO dtoExist = findByLogin(dto.getLogin());
        // Check if updated LoginId already exist
        if (dtoExist != null && !(dtoExist.getId() == dto.getId())) {
            throw new DuplicateRecordException("LoginId is already exist");
        }

        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("UPDATE  USER SET NAME=?,LOGIN=?,PASSWORD=?,DOB=?,MOBILE_NO=?,ROLE_ID=?,GENDER=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
            pstmt.setString(1, dto.getName());
            pstmt.setString(2, dto.getLogin());
            pstmt.setString(3, dto.getPassword());
            pstmt.setDate(4,  new java.sql.Date(dto.getDob().getTime()));
            pstmt.setString(5, dto.getMobileNo());
            pstmt.setLong(6, dto.getRoleId());
            pstmt.setString(7, dto.getGender());
            pstmt.setString(8, dto.getCreatedBy());
            pstmt.setString(9, dto.getModifiedBy());
            pstmt.setTimestamp(10,(dto.getCreatedDatetime()));
            pstmt.setTimestamp(11,(dto.getModifiedDatetime()));
            pstmt.setLong(12, dto.getId());
            pstmt.executeUpdate();
            conn.commit(); // End transaction
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Database Exception..", e);
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException(
                        "Exception : Delete rollback exception "
                                + ex.getMessage());
            }
            throw new ApplicationException("Exception in updating User ");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.info("Model update End");
    }

    /**
     * Search User
     *
     * @param dto
     *            : Search Parameters
     * @throws ApplicationException
     */
    public List search(UserDTO dto) throws ApplicationException {
        return search(dto, 0, 0);
    }

    /**
     * Search User with pagination
     *
     * @return list : List of Users
     * @param dto
     *            : Search Parameters
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     *
     * @throws ApplicationException
     */
    public List search(UserDTO dto, int pageNo, int pageSize)
            throws ApplicationException {
        log.info("Model search Started");
        StringBuffer sql = new StringBuffer("SELECT * FROM  USER WHERE 1=1");

        if (dto != null) {
            if (dto.getId() > 0) {
                sql.append(" AND id = " + dto.getId());
            }
            if (dto.getName() != null && dto.getName().length() > 0) {
                sql.append(" AND NAME like '" + dto.getName() + "%'");
            }
            if (dto.getLogin() != null && dto.getLogin().length() > 0) {
                sql.append(" AND LOGIN like '" + dto.getLogin() + "%'");
            }
            if (dto.getPassword() != null && dto.getPassword().length() > 0) {
                sql.append(" AND PASSWORD like '" + dto.getPassword() + "%'");
            }
            if (dto.getDob() != null && dto.getDob().getDate() > 0) {
                sql.append(" AND DOB = " + dto.getGender());
            }
            if (dto.getMobileNo() != null && dto.getMobileNo().length() > 0) {
                sql.append(" AND MOBILE_NO = " + dto.getMobileNo());
            }
            if (dto.getRoleId() > 0) {
                sql.append(" AND ROLE_ID = " + dto.getRoleId());
            }
            if (dto.getGender() != null && dto.getGender().length() > 0) {
                sql.append(" AND GENDER like '" + dto.getGender() + "%'");
            }

        }

        // if page size is greater than zero then apply pagination
        if (pageSize > 0) {
            // Calculate start record index
            pageNo = (pageNo - 1) * pageSize;

            sql.append(" Limit " + pageNo + ", " + pageSize);
            // sql.append(" limit " + pageNo + "," + pageSize);
        }

        System.out.println(sql);
        ArrayList list = new ArrayList();
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                dto = new UserDTO();
                dto.setId(rs.getLong(1));
                dto.setName(rs.getString(2));
                dto.setLogin(rs.getString(3));
                dto.setPassword(rs.getString(4));
                dto.setDob(rs.getDate(5));
                dto.setMobileNo(rs.getString(6));
                dto.setRoleId(rs.getLong(7));
                dto.setGender(rs.getString(8));
                dto.setCreatedBy(rs.getString(9));
                dto.setModifiedBy(rs.getString(10));
                dto.setCreatedDatetime(rs.getTimestamp(11));
                dto.setModifiedDatetime(rs.getTimestamp(12));


                list.add(dto);
            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in search user");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.info("Model search End");
        return list;
    }

    /**
     * Get List of User
     *
     * @return list : List of User
     * @throws ApplicationException
     */
    public List list() throws ApplicationException {
        return list(0, 0);
    }

    /**
     * Get List of User with pagination
     *
     * @return list : List of users
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws ApplicationException
     */
    public List list(int pageNo, int pageSize) throws ApplicationException {
        log.info("Model list Started");
        ArrayList list = new ArrayList();
        StringBuffer sql = new StringBuffer("select * from  USER");
        // if page size is greater than zero then apply pagination
        if (pageSize > 0) {
            // Calculate start record index
            pageNo = (pageNo - 1) * pageSize;
            sql.append(" limit " + pageNo + "," + pageSize);
        }

        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                UserDTO dto = new UserDTO();
                dto.setId(rs.getLong(1));
                dto.setName(rs.getString(2));
                dto.setLogin(rs.getString(3));
                dto.setPassword(rs.getString(4));
                dto.setDob(rs.getDate(5));
                dto.setMobileNo(rs.getString(6));
                dto.setRoleId(rs.getLong(7));
                dto.setGender(rs.getString(8));
                dto.setCreatedBy(rs.getString(9));
                dto.setModifiedBy(rs.getString(10));
                dto.setCreatedDatetime(rs.getTimestamp(11));
                dto.setModifiedDatetime(rs.getTimestamp(12));


                list.add(dto);
            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting list of users");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.info("Model list End");
        return list;

    }

    /**
     * @param id
     *            : long id
     * @param old
     *            password : String oldPassword
     * @param new password : String newPassword
     * @throws ApplicationException
     */
    public UserDTO authenticate(String login, String password)
            throws ApplicationException {
        log.info("Model authenticate Started");
        StringBuffer sql = new StringBuffer(
                "SELECT * FROM USER WHERE LOGIN = ? AND PASSWORD = ?");
        UserDTO dto = null;
        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                dto = new UserDTO();
                dto.setId(rs.getLong(1));
                dto.setName(rs.getString(2));
                dto.setLogin(rs.getString(3));
                dto.setPassword(rs.getString(4));
                dto.setDob(rs.getDate(5));
                dto.setMobileNo(rs.getString(6));
                dto.setRoleId(rs.getLong(7));
                dto.setGender(rs.getString(8));
                dto.setCreatedBy(rs.getString(9));
                dto.setModifiedBy(rs.getString(10));
                dto.setCreatedDatetime(rs.getTimestamp(11));
                dto.setModifiedDatetime(rs.getTimestamp(12));

                System.out.println(dto.getLogin() +" "+ dto.getPassword()+" in model jdbc");
                
            }
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException("Exception : Exception in get roles");

        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.info("Model authenticate End");
        return dto;
    }


    /**
     * @param id
     *            : long id
     * @param old
     *            password : String oldPassword
     * @param newpassword
     *            : String newPassword
     * @throws ApplicationException,RecordNotFoundException
     */
    public boolean changePassword(Long id, String oldPassword,
            String newPassword) throws RecordNotFoundException,
            ApplicationException {

        log.info("model changePassword Started");
        boolean flag = false;
        UserDTO dtoExist = null;

        dtoExist = findByPK(id);
        if (dtoExist != null && dtoExist.getPassword().equals(oldPassword)) {
            dtoExist.setPassword(newPassword);
            try {
                update(dtoExist);
            } catch (DuplicateRecordException e) {
                log.error(e);
                throw new ApplicationException("LoginId is already exist");
            }
            flag = true;
        } else {
            throw new RecordNotFoundException("Login not exist");
        }

        HashMap<String, String> map = new HashMap<String, String>();

        map.put("login", dtoExist.getLogin());
        map.put("password", dtoExist.getPassword());
        map.put("name", dtoExist.getName());

        String message = EmailBuilder.getChangePasswordMessage(map);

        EmailMessage msg = new EmailMessage();

        msg.setTo(dtoExist.getLogin());
        msg.setSubject("SUNARYS ORS Password has been changed Successfully.");
        msg.setMessage(message);
        msg.setMessageType(EmailMessage.HTML_MSG);

        EmailUtility.sendMail(msg);

        log.info("Model changePassword End");
        return flag;

    }

    public UserDTO updateAccess(UserDTO dto) throws ApplicationException {
        return null;
    }

    /**
     * Register a user
     *
     * @param dto
     * @throws ApplicationException
     * @throws DuplicateRecordException
     *             : throws when user already exists
     *            
     */
    public long registerUser(UserDTO dto) throws ApplicationException,
            DuplicateRecordException {

        log.info("Model add Started");

        long pk = add(dto);

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("login", dto.getLogin());
        map.put("password", dto.getPassword());
        map.put("name",dto.getName());
        
        String message = EmailBuilder.getUserRegistrationMessage(map);

        EmailMessage msg = new EmailMessage();

        msg.setTo(dto.getLogin());
        msg.setSubject("Registration is successful for ORS Project SUNRAYS Technologies");
        msg.setMessage(message);
        msg.setMessageType(EmailMessage.HTML_MSG);

        EmailUtility.sendMail(msg);
        return pk;
    }

    /**
     * Reset Password of User with auto generated Password
     *
     * @return boolean : true if success otherwise false
     * @param login
     *            : User Login
     * @throws ApplicationException
     */
    public boolean resetPassword(UserDTO dto) throws ApplicationException {

        String newPassword = String.valueOf(new Date().getTime()).substring(0,
                4);
        UserDTO userData = findByPK(dto.getId());
        userData.setPassword(newPassword);

        try {
            update(userData);
        } catch (DuplicateRecordException e) {
            return false;
        }

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("login", dto.getLogin());
        map.put("password", dto.getPassword());
        map.put("name", dto.getName());

        String message = EmailBuilder.getForgetPasswordMessage(map);

        EmailMessage msg = new EmailMessage();

        msg.setTo(dto.getLogin());
        msg.setSubject("Password has been reset");
        msg.setMessage(message);
        msg.setMessageType(EmailMessage.HTML_MSG);

        EmailUtility.sendMail(msg);

        return true;
    }

    /**
     * Send the password of User to his Email
     *
     * @return boolean : true if success otherwise false
     * @param login
     *            : User Login
     * @throws ApplicationException
     * @throws RecordNotFoundException
     *             : if user not found
     */
    public boolean forgetPassword(String login) throws ApplicationException,
            RecordNotFoundException {
        UserDTO userData = findByLogin(login);
        boolean flag = false;

        if (userData == null) {
            throw new RecordNotFoundException("Email Id Does not matched.");

        }

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("login", userData.getLogin());
        map.put("password", userData.getPassword());
        map.put("name", userData.getName());

        String message = EmailBuilder.getForgetPasswordMessage(map);
       
        EmailMessage msg = new EmailMessage();
        msg.setTo(login);
        msg.setSubject("SUNARYS ORS Password reset");
        msg.setMessage(message);
        msg.setMessageType(EmailMessage.HTML_MSG);
        EmailUtility.sendMail(msg);
        flag = true;

        return flag;
    }

}