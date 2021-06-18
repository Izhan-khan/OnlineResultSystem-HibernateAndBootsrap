package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import dto.SubjectDTO;
import exception.ApplicationException;
import exception.DatabaseException;
import exception.DuplicateRecordException;
import utility.JDBCDataSource;

/**
 * JDBC Implementation of SubjectModel
 *
 * @author SUNRAYS TechnolCogies
 * @version 1.0
 * 
 */
public class SubjectModel_JDBC_Implement implements SubjectModel_Interface {

	private static Logger log = Logger.getLogger(SubjectModel_JDBC_Implement.class);

	/**
	 * Find next PK of Subject
	 *
	 * @throws DatabaseException
	 */
	public Integer nextPK() throws DatabaseException {
		log.info("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM SUBJECT");
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
	 * Add a Subject
	 *
	 * @param dto
	 * @throws ApplicationException,DuplicateRecordException
	 *
	 */
	public long add(SubjectDTO dto) throws ApplicationException, DuplicateRecordException {
		log.info("Model add Started");
		Connection conn = null;
		int pk = 0;

		SubjectDTO duplicateSubjectName = findByName(dto.getName());

		if (duplicateSubjectName != null) {
			throw new DuplicateRecordException("Subject Name already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO SUBJECT VALUES(?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getDescription());
			pstmt.setLong(4, dto.getCourseId());
			pstmt.setString(5, dto.getCreatedBy());
			pstmt.setString(6, dto.getModifiedBy());
			pstmt.setTimestamp(7,(dto.getCreatedDatetime()));
			pstmt.setTimestamp(8,(dto.getModifiedDatetime()));
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Subject");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("Model add End");
		return pk;
	}

	/**
	 * Delete a Subject
	 *
	 * @param dto
	 * @throws ApplicationException
	 */
	public void delete(SubjectDTO dto) throws ApplicationException {
		log.info("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM SUBJECT WHERE ID=?");
			pstmt.setLong(1, dto.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete college");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("Model delete Started");
	}

	/**
	 * Find User by Subject
	 *
	 * @param login : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */

	public SubjectDTO findByName(String name) throws ApplicationException {
		log.info("Model findByName Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM SUBJECT WHERE NAME=?");
		SubjectDTO dto = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new SubjectDTO();
				dto.setId(rs.getLong(1));
				dto.setName(rs.getString(2));
				dto.setDescription(rs.getString(3));
				dto.setCourseId(rs.getLong(4));
				dto.setCreatedBy(rs.getString(5));
				dto.setModifiedBy(rs.getString(6));
				dto.setCreatedDatetime(rs.getTimestamp(7));
				dto.setModifiedDatetime(rs.getTimestamp(8));

			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Subject by Name");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("Model findByName End");
		return dto;
	}

	/**
	 * Find User by Subject
	 *
	 * @param pk : get parameter
	 * @return dto
	 * @throws ApplicationException
	 */

	public SubjectDTO findByPK(long pk) throws ApplicationException {
		log.info("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM SUBJECT WHERE ID=?");
		SubjectDTO dto = null;
		Connection conn = null;
		try {

			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new SubjectDTO();
				dto.setId(rs.getLong(1));
				dto.setName(rs.getString(2));
				dto.setDescription(rs.getString(3));
				dto.setCourseId(rs.getLong(4));
				dto.setCreatedBy(rs.getString(5));
				dto.setModifiedBy(rs.getString(6));
				dto.setCreatedDatetime(rs.getTimestamp(7));
				dto.setModifiedDatetime(rs.getTimestamp(8));

			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Subject by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("Model findByPK End");
		return dto;
	}

	/**
	 * Update a Subject
	 *
	 * @param dto
	 * @throws ApplicationException,DuplicateRecordException
	 */

	public void update(SubjectDTO dto) throws ApplicationException, DuplicateRecordException {
		log.info("Model update Started");
		Connection conn = null;

		SubjectDTO dtoExist = findByName(dto.getName());

		// Check if updated Subject already exist
		if (dtoExist != null && dtoExist.getId() != dto.getId()) {

			throw new DuplicateRecordException("Subject is already exist");
		}

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE SUBJECT SET NAME=?,ADDRESS=?,STATE=?,CITY=?,PHONE_NO=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");

			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getDescription());
			pstmt.setLong(3, dto.getCourseId());
			pstmt.setString(4, dto.getCreatedBy());
			pstmt.setString(5, dto.getModifiedBy());
			pstmt.setTimestamp(6,(dto.getCreatedDatetime()));
			pstmt.setTimestamp(7,(dto.getModifiedDatetime()));
			pstmt.setLong(8, dto.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Subject ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("Model update End");
	}

	/**
	 * Search Subject with pagination
	 *
	 * @return list : List of Users
	 * @param dto      : Search Parameters
	 * @param pageNo   : Current Page No.
	 * @param pageSize : Size of Page
	 *
	 * @throws ApplicationException
	 */

	public List search(SubjectDTO dto, int pageNo, int pageSize) throws ApplicationException {
		log.info("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM SUBJECT WHERE 1=1");

		if (dto != null) {
			if (dto.getId() > 0) {
				sql.append(" AND id = " + dto.getId());
			}
			if (dto.getName() != null && dto.getName().length() > 0) {
				sql.append(" AND NAME like '" + dto.getName() + "%'");
			}
			if (dto.getCourseId() > 0) {
				sql.append(" AND CourseId = " + dto.getCourseId());
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
				dto = new SubjectDTO();
				dto.setId(rs.getLong(1));
				dto.setName(rs.getString(2));
				dto.setDescription(rs.getString(3));
				dto.setCourseId(rs.getLong(4));
				dto.setCreatedBy(rs.getString(5));
				dto.setModifiedBy(rs.getString(6));
				dto.setCreatedDatetime(rs.getTimestamp(7));
				dto.setModifiedDatetime(rs.getTimestamp(8));
				list.add(dto);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search college");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.info("Model search End");
		return list;
	}

	/**
	 * Search Subject
	 *
	 * @param dto : Search Parameters
	 * @throws ApplicationException
	 */

	public List search(SubjectDTO dto) throws ApplicationException {
		return search(dto, 0, 0);
	}

	/**
	 * Get List of Subject
	 *
	 * @return list : List of Subject
	 * @throws ApplicationException
	 */

	public List list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * Get List of Subject with pagination
	 *
	 * @return list : List of Subject
	 * @param pageNo   : Current Page No.
	 * @param pageSize : Size of Page
	 * @throws ApplicationException
	 */

	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.info("Model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from SUBJECT");
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
				SubjectDTO dto = new SubjectDTO();
				dto.setId(rs.getLong(1));
				dto.setName(rs.getString(2));
				dto.setDescription(rs.getString(3));
				dto.setCourseId(rs.getLong(4));
				dto.setCreatedBy(rs.getString(5));
				dto.setModifiedBy(rs.getString(6));
				dto.setCreatedDatetime(rs.getTimestamp(7));
				dto.setModifiedDatetime(rs.getTimestamp(8));
				list.add(dto);
			}
			rs.close();
		} catch (Exception e) {

			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of users");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.info("Model list End");
		return list;

	}
}