package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import dto.CollegeDTO;
import dto.StudentDTO;
import exception.ApplicationException;
import exception.DatabaseException;
import exception.DuplicateRecordException;
import utility.JDBCDataSource;

/**
 * JDBC Implementation of Student Model
 *
 * @author SUNRAYS Technologies
 * @version 1.0
 * 
 */
public class StudentModel_JDBC_Implement implements StudentModel_Interface {

    private static Logger log = Logger.getLogger(StudentModel_JDBC_Implement.class);

    /**
     * Find next PK of Student
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
                    .prepareStatement("SELECT MAX(ID) FROM STUDENT");
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
     * Add a Student
     *
     * @param dto
     * @throws ApplicationException
     *
     */
    public long add(StudentDTO dto) throws ApplicationException,
            DuplicateRecordException {
        log.info("Model add Started");
        Connection conn = null;
        
        // get College Name
        CollegeModel_Interface cModel = ModelFactory.getInstance().getCollegeModel();
        CollegeDTO collegeDTO = cModel.findByPK(dto.getCollegeId());
        
        StudentDTO duplicateName = findByEmailId(dto.getEmail());
        int pk = 0;
        
        if (duplicateName != null) {
            throw new DuplicateRecordException("Email already exists");
        }

        try {
            conn = JDBCDataSource.getConnection();
            pk = nextPK();
            // Get auto-generated next primary key
            System.out.println(pk + " in ModelJDBC");
            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("INSERT INTO STUDENT VALUES(?,?,?,?,?,?,?,?,?,?)");
            pstmt.setInt(1, pk);
            pstmt.setString(2, dto.getName());
            pstmt.setDate(3,  new java.sql.Date(dto.getDob().getTime()));
            pstmt.setString(4, dto.getMobileNo());
            pstmt.setString(5, dto.getEmail());
            pstmt.setLong(6, dto.getCollegeId());
            pstmt.setString(7, dto.getCreatedBy());
            pstmt.setString(8, dto.getModifiedBy());
            pstmt.setTimestamp(9,(dto.getCreatedDatetime()));
            pstmt.setTimestamp(10,(dto.getModifiedDatetime()));
            pstmt.executeUpdate();
            conn.commit(); // End transaction
            pstmt.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException(
                        "Exception : add rollback exception " + ex.getMessage());
            }
            throw new ApplicationException(
                    "Exception : Exception in add Student");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.info("Model add End");
        return pk;
    }

    /**
     * Delete a Student
     *
     * @param dto
     * @throws ApplicationException
     */
    public void delete(StudentDTO dto) throws ApplicationException {
        log.info("Model delete Started");
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("DELETE FROM STUDENT WHERE ID=?");
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
                    "Exception : Exception in delete Student");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.info("Model delete Started");
    }

    /**
     * Find User by Student
     *
     * @param Email
     *            : get parameter
     * @return dto
     * @throws ApplicationException
     */
 
    public StudentDTO findByEmailId(String Email) throws ApplicationException {
        log.info("Model findBy Email Started");
        StringBuffer sql = new StringBuffer(
                "SELECT * FROM STUDENT WHERE EMAIL=?");
        StudentDTO dto = null;
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, Email);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                dto = new StudentDTO();
                dto.setId(rs.getLong(1));
                dto.setName(rs.getString(2));
                dto.setDob(rs.getDate(3));
                dto.setMobileNo(rs.getString(4));
                dto.setEmail(rs.getString(5));
                dto.setCollegeId(rs.getLong(6));
                dto.setCreatedBy(rs.getString(7));
                dto.setModifiedBy(rs.getString(8));
                dto.setCreatedDatetime(rs.getTimestamp(9));
                dto.setModifiedDatetime(rs.getTimestamp(10));

            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting User by Email");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.info("Model findBy Email End");
        return dto;
    }

    /**
     * Find Student by PK
     *
     * @param pk
     *            : get parameter
     * @return dto
     * @throws ApplicationException
     */
 
    public StudentDTO findByPK(long pk) throws ApplicationException {
        log.info("Model findByPK Started");
        StringBuffer sql = new StringBuffer("SELECT * FROM STUDENT WHERE ID=?");
        StudentDTO dto = null;
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setLong(1, pk);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                dto = new StudentDTO();
                dto.setId(rs.getLong(1));
                dto.setName(rs.getString(2));
                dto.setDob(rs.getDate(3));
                dto.setMobileNo(rs.getString(4));
                dto.setEmail(rs.getString(5));
                dto.setCollegeId(rs.getLong(6));
                dto.setCreatedBy(rs.getString(7));
                dto.setModifiedBy(rs.getString(8));
                dto.setCreatedDatetime(rs.getTimestamp(9));
                dto.setModifiedDatetime(rs.getTimestamp(10));
            }
            rs.close();
        } catch (Exception e) {
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
     * Update a Student
     *
     * @param dto
     * @throws ApplicationException,DuplicateRecordException
     */
 
    public void update(StudentDTO dto) throws ApplicationException,
            DuplicateRecordException {
        log.info("Model update Started");
        Connection conn = null;
        
        StudentDTO dtoExist = findByEmailId(dto.getEmail());

        // Check if updated Roll no already exist
        if (dtoExist != null && dtoExist.getId() != dto.getId()) {
            throw new DuplicateRecordException("Email Id is already exist");
        }
        
        // get College Name
                CollegeModel_Interface cModel = ModelFactory.getInstance().getCollegeModel();
                CollegeDTO collegeDTO = cModel.findByPK(dto.getCollegeId());
        
        try {

            conn = JDBCDataSource.getConnection();

            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("UPDATE STUDENT SET COLLEGE_ID=?,NAME=?,DATE_OF_BIRTH=?,MOBILE_NO=?,EMAIL=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
            pstmt.setString(1, dto.getName());
            pstmt.setDate(2,  new java.sql.Date(dto.getDob().getTime()));
            pstmt.setString(3, dto.getMobileNo());
            pstmt.setString(4, dto.getEmail());
            pstmt.setLong(5, dto.getCollegeId());
            pstmt.setString(6, dto.getCreatedBy());
            pstmt.setString(7, dto.getModifiedBy());
            pstmt.setTimestamp(8,(dto.getCreatedDatetime()));
            pstmt.setTimestamp(9,(dto.getModifiedDatetime()));
            pstmt.setLong(10, dto.getId());
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
            throw new ApplicationException("Exception in updating Student ");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.info("Model update End");
    }

    /**
     * Search Student
     *
     * @param dto
     *            : Search Parameters
     * @throws ApplicationException
     */
    
    public List search(StudentDTO dto) throws ApplicationException {
        return search(dto, 0, 0);
    }

    /**
     * Search Student with pagination
     *
     * @return list : List of Students
     * @param dto
     *            : Search Parameters
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     *
     * @throws ApplicationException
     */
    
    public List search(StudentDTO dto, int pageNo, int pageSize)
            throws ApplicationException {
        log.info("Model search Started");
        StringBuffer sql = new StringBuffer("SELECT * FROM STUDENT WHERE 1=1");

        if (dto != null) {
            if (dto.getId() > 0) {
                sql.append(" AND id = " + dto.getId());
            }
            if (dto.getName() != null && dto.getName().length() > 0) {
                sql.append(" AND NAME like '" + dto.getName() + "%'");
            }
            if (dto.getDob() != null && dto.getDob().getDate() > 0) {
                sql.append(" AND DATE_OF_BIRTH = " + dto.getDob());
            }
            if (dto.getMobileNo() != null && dto.getMobileNo().length() > 0) {
                sql.append(" AND MOBILE_NO like '" + dto.getMobileNo() + "%'");
            }
            if (dto.getEmail() != null && dto.getEmail().length() > 0) {
                sql.append(" AND EMAIL like '" + dto.getEmail() + "%'");
            }
            if (dto.getCollegeId()> 0) {
                sql.append(" AND COLLEGE_ID = " + dto.getCollegeId());
            }

        }

        // if page size is greater than zero then apply pagination
        if (pageSize > 0) {
            // Calculate start record index
            pageNo = (pageNo - 1) * pageSize;

            sql.append(" Limit " + pageNo + ", " + pageSize);
            // sql.append(" limit " + pageNo + "," + pageSize);
        }

        ArrayList list = new ArrayList();
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                dto = new StudentDTO();
                dto.setId(rs.getLong(1));
                dto.setName(rs.getString(2));
                dto.setDob(rs.getDate(3));
                dto.setMobileNo(rs.getString(4));
                dto.setEmail(rs.getString(5));
                dto.setCollegeId(rs.getLong(6));
                dto.setCreatedBy(rs.getString(7));
                dto.setModifiedBy(rs.getString(8));
                dto.setCreatedDatetime(rs.getTimestamp(9));
                dto.setModifiedDatetime(rs.getTimestamp(10));
                list.add(dto);
            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in search Student");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.info("Model search End");
        return list;
    }

    /**
     * Get List of Student
     *
     * @return list : List of Student
     * @throws ApplicationException
     */
    
    public List list() throws ApplicationException {
        return list(0, 0);
    }

    /**
     * Get List of Student with pagination
     *
     * @return list : List of Student
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws ApplicationException
     */
    
    public List list(int pageNo, int pageSize) throws ApplicationException {
        log.info("Model list Started");
        ArrayList list = new ArrayList();
        StringBuffer sql = new StringBuffer("select * from STUDENT");
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
                StudentDTO dto = new StudentDTO();
                dto.setId(rs.getLong(1));
                dto.setName(rs.getString(2));
                dto.setDob(rs.getDate(3));
                dto.setMobileNo(rs.getString(4));
                dto.setEmail(rs.getString(5));
                dto.setCollegeId(rs.getLong(6));
                dto.setCreatedBy(rs.getString(7));
                dto.setModifiedBy(rs.getString(8));
                dto.setCreatedDatetime(rs.getTimestamp(9));
                dto.setModifiedDatetime(rs.getTimestamp(10));
                list.add(dto);
            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting list of Student");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.info("Model list End");
        return list;

    }
}