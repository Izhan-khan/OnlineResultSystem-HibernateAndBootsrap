package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

import org.apache.log4j.Logger;

import dto.FacultyDTO;
import exception.ApplicationException;
import exception.DatabaseException;
import exception.DuplicateRecordException;
import utility.JDBCDataSource;

/**
 * JDBC Implementation of FacultyModel
 *
 * @author SUNRAYS Technologies
 * @version 1.0
 * 
 */
public class FacultyModel_JDBC_Implement implements FacultyModel_Interface {

    private static Logger log = Logger.getLogger(FacultyModel_JDBC_Implement.class);

    /**
     * Find next PK of Faculty
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
                    .prepareStatement("SELECT MAX(ID) FROM FACULTY");
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
     * Add a Faculty
     *
     * @param dto
     * @throws ApplicationException
     *
     */
    public long add(FacultyDTO dto) throws ApplicationException,
            DuplicateRecordException {
        log.info("Model add Started");
        Connection conn = null;
        int pk = 0;

        FacultyDTO duplicateFacultyName = findByEmail(dto.getEmail());

        if (duplicateFacultyName != null) {
            throw new DuplicateRecordException("Faculty already exists");
        }

        try {
            conn = JDBCDataSource.getConnection();
            pk = nextPK();
            // Get auto-generated next primary key
            System.out.println(pk + " in ModelJDBC");
            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("INSERT INTO FACULTY VALUES(?,?,?,?,?,?,?,?,?,?)");
            pstmt.setInt(1, pk);
            pstmt.setString(2, dto.getName());
            pstmt.setString(3, dto.getQualification());
            pstmt.setString(4, dto.getEmail());
            pstmt.setString(5, dto.getGender());
            pstmt.setString(6, dto.getMobileNo());
            pstmt.setLong(7, dto.getCollegeId());
            pstmt.setLong(8, dto.getCourseId());
            pstmt.setLong(9, dto.getSubjectId());
            pstmt.setDate(10,  new java.sql.Date(dto.getDob().getTime()));
            pstmt.setString(11, dto.getCreatedBy());
            pstmt.setString(12, dto.getModifiedBy());
            pstmt.setTimestamp(13,(dto.getCreatedDatetime()));
            pstmt.setTimestamp(14,(dto.getModifiedDatetime()));
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
            throw new ApplicationException(
                    "Exception : Exception in add Faculty");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.info("Model add End");
        return pk;
    }

    /**
     * Delete a Faculty
     *
     * @param dto
     * @throws ApplicationException
     */
    public void delete(FacultyDTO dto) throws ApplicationException {
        log.info("Model delete Started");
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("DELETE FROM FACULTY WHERE ID=?");
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
                    "Exception : Exception in delete faculty");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.info("Model delete Started");
    }

    /**
     * Find User by Faculty
     *
     * @param login
     *            : get parameter
     * @return dto
     * @throws ApplicationException
     */
    
    public FacultyDTO findByEmail(String email) throws ApplicationException {
        log.info("Model findByName Started");
        StringBuffer sql = new StringBuffer(
                "SELECT * FROM FACULTY WHERE EMAIL =?");
        FacultyDTO dto = null;
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                dto = new FacultyDTO();
                dto.setId(rs.getLong(1));
                dto.setName(rs.getString(2));
                dto.setQualification(rs.getString(3));
                dto.setMobileNo(rs.getString(4));
                dto.setGender(rs.getString(5));
                dto.setDob(rs.getDate(6));
                dto.setEmail(rs.getString(7));
                dto.setCollegeId(rs.getLong(8));
                dto.setCourseId(rs.getLong(9));
                dto.setSubjectId(rs.getLong(10));
                dto.setCreatedBy(rs.getString(11));
                dto.setModifiedBy(rs.getString(12));
                dto.setCreatedDatetime(rs.getTimestamp(13));
                dto.setModifiedDatetime(rs.getTimestamp(14));

            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting Faculty by Name");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.info("Model findByName End");
        return dto;
    }

    /**
     * Find User by Faculty
     *
     * @param pk
     *            : get parameter
     * @return dto
     * @throws ApplicationException
     */
    
    public FacultyDTO findByPK(long pk) throws ApplicationException {
        log.info("Model findByPK Started");
        StringBuffer sql = new StringBuffer(
                "SELECT * FROM FACULTY WHERE ID=?");
        FacultyDTO dto = null;
        Connection conn = null;
        try {

            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setLong(1, pk);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                dto = new FacultyDTO();
                dto.setId(rs.getLong(1));
                dto.setName(rs.getString(2));
                dto.setQualification(rs.getString(3));
                dto.setMobileNo(rs.getString(4));
                dto.setGender(rs.getString(5));
                dto.setDob(rs.getDate(6));
                dto.setEmail(rs.getString(7));
                dto.setCollegeId(rs.getLong(8));
                dto.setCourseId(rs.getLong(9));
                dto.setSubjectId(rs.getLong(10));
                dto.setCreatedBy(rs.getString(11));
                dto.setModifiedBy(rs.getString(12));
                dto.setCreatedDatetime(rs.getTimestamp(13));
                dto.setModifiedDatetime(rs.getTimestamp(14));

            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting Faculty by pk");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.info("Model findByPK End");
        return dto;
    }

    /**
     * Update a Faculty
     *
     * @param dto
     * @throws ApplicationException
     */
    
    public void update(FacultyDTO dto) throws ApplicationException,
            DuplicateRecordException {
        log.info("Model update Started");
        Connection conn = null;

        FacultyDTO dtoExist = findByEmail(dto.getEmail());

        // Check if updated Faculty already exist
        if (dtoExist != null && dtoExist.getId() != dto.getId()) {

            throw new DuplicateRecordException("Faculty is already exist");
        }

        try {

            conn = JDBCDataSource.getConnection();

            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("UPDATE FACULTY SET NAME=?,ADDRESS=?,STATE=?,CITY=?,PHONE_NO=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
            pstmt.setString(1, dto.getName());
            pstmt.setString(2, dto.getQualification());
            pstmt.setString(3, dto.getEmail());
            pstmt.setString(4, dto.getGender());
            pstmt.setString(5, dto.getMobileNo());
            pstmt.setLong(6, dto.getCollegeId());
            pstmt.setLong(7, dto.getCourseId());
            pstmt.setLong(8, dto.getSubjectId());
            pstmt.setDate(9,  new java.sql.Date(dto.getDob().getTime()));
            pstmt.setString(10, dto.getCreatedBy());
            pstmt.setString(11, dto.getModifiedBy());
            pstmt.setTimestamp(12,(dto.getCreatedDatetime()));
            pstmt.setTimestamp(13,(dto.getModifiedDatetime()));
           pstmt.setLong(14, dto.getId());
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
            throw new ApplicationException("Exception in updating Faculty ");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.info("Model update End");
    }

    /**
     * Search Faculty with pagination
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
    
    public List search(FacultyDTO dto, int pageNo, int pageSize)
            throws ApplicationException {
        log.info("Model search Started");
        StringBuffer sql = new StringBuffer(
                "SELECT * FROM FACULTY WHERE 1=1");

        if (dto != null) {
            if (dto.getId() > 0) {
                sql.append(" AND id = " + dto.getId());
            }
            if (dto.getName() != null && dto.getName().length() > 0) {
                sql.append(" AND NAME like '" + dto.getName() + "%'");
            }
            if (dto.getCollegeId() > 0) {
                sql.append(" AND CollegeId = " + dto.getCollegeId());
            }
            if (dto.getCourseId() > 0) {
                sql.append(" AND Courseid = " + dto.getCourseId());
            }
            if (dto.getSubjectId() > 0) {
                sql.append(" AND Subjectid = " + dto.getSubjectId());
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
                dto = new FacultyDTO();
                dto.setId(rs.getLong(1));
                dto.setName(rs.getString(2));
                dto.setQualification(rs.getString(3));
                dto.setMobileNo(rs.getString(5));
                dto.setGender(rs.getString(6));
                dto.setDob(rs.getDate(7));
                dto.setEmail(rs.getString(4));
                dto.setCollegeId(rs.getLong(8));
                dto.setCourseId(rs.getLong(10));
                dto.setSubjectId(rs.getLong(9));
                dto.setCreatedBy(rs.getString(11));
                dto.setModifiedBy(rs.getString(12));
                dto.setCreatedDatetime(rs.getTimestamp(13));
                dto.setModifiedDatetime(rs.getTimestamp(14));
                list.add(dto);
            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in search faculty");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.info("Model search End");
        return list;
    }

    /**
     * Search Faculty
     *
     * @param dto
     *            : Search Parameters
     * @throws ApplicationException
     */
    
    public List search(FacultyDTO dto) throws ApplicationException {
        return search(dto, 0, 0);
    }

    /**
     * Get List of Faculty
     *
     * @return list : List of Faculty
     * @throws ApplicationException
     */
    
    public List list() throws ApplicationException {
        return list(0, 0);
    }

    /**
     * Get List of Faculty with pagination
     *
     * @return list : List of Faculty
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws ApplicationException
     */
    
    public List list(int pageNo, int pageSize) throws ApplicationException {
        log.info("Model list Started");
        ArrayList list = new ArrayList();
        StringBuffer sql = new StringBuffer("select * from FACULTY");
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
                FacultyDTO dto = new FacultyDTO();
                dto.setId(rs.getLong(1));
                dto.setName(rs.getString(2));
                dto.setQualification(rs.getString(3));
                dto.setMobileNo(rs.getString(4));
                dto.setGender(rs.getString(5));
                dto.setDob(rs.getDate(6));
                dto.setEmail(rs.getString(7));
                dto.setCollegeId(rs.getLong(8));
                dto.setCourseId(rs.getLong(9));
                dto.setSubjectId(rs.getLong(10));
                dto.setCreatedBy(rs.getString(11));
                dto.setModifiedBy(rs.getString(12));
                dto.setCreatedDatetime(rs.getTimestamp(13));
                dto.setModifiedDatetime(rs.getTimestamp(14));
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
}