package org.kostagram.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class PostDAO {
	private static PostDAO instance = new PostDAO();
    private DataSource dataSource;
    private PostDAO() {
        this.dataSource = DataSourceManager.getInstance().getDataSource();
    }
    public static PostDAO getInstance() {
        return instance;
    }
    public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection con) throws SQLException {
        if (rs != null)
            rs.close();
        closeAll(pstmt, con);
    }
    public void closeAll(PreparedStatement pstmt, Connection con) throws SQLException {
        if (pstmt != null)
            pstmt.close();
        if (con != null)
            con.close();
    }
     
    public ArrayList<PostVO> mainPostList() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PostVO vo = null;
		MemberVO mvo = null;
		ArrayList<PostVO> list = new ArrayList<PostVO>();
		try {
			con = dataSource.getConnection();
			String postSql = "select p.post_id,p.img,p.org_img,p.post_content,p.post_date,m.member_id,m.org_profile_img from post p,member m where p.member_id=m.member_id order by p.post_id desc";
			pstmt = con.prepareStatement(postSql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				vo = new PostVO(rs.getInt(1), null, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				mvo = new MemberVO(rs.getString(6),null,null,null,null,rs.getString(7),null,null);
				vo.setMemberVO(mvo);
				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, con);
		}
		
		return list;
	}

    public PostVO getPostDetail(String postId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PostVO vo = null;
		
		try {
			con = dataSource.getConnection();
			String postSql = "SELECT * FROM post WHERE post_id = ?";
			
			pstmt = con.prepareStatement(postSql);
			pstmt.setString(1, postId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new PostVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, con);
		}
		
		return vo;
	}
	//포스트 업로드 메서드
	public void uploadPost(String fileName, String fileRealName, String postContent, String memberId) throws SQLException {
	    Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = dataSource.getConnection();
            StringBuilder postUploadSql = new StringBuilder();
            postUploadSql.append("insert into post(post_id, member_id, img, org_img, post_content, post_date) ");
            postUploadSql.append("values(post_seq.nextval, ?, ?, ?, ?, sysdate)");
            pstmt = con.prepareStatement(postUploadSql.toString());
            pstmt.setString(1, memberId);
            pstmt.setString(2, fileName);
            pstmt.setString(3, fileRealName);
            pstmt.setString(4, postContent);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(pstmt, con);
        }
	}
	// 포스트 아이디 조회
	public PostVO getPostId(String memberId) throws SQLException {
	    Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PostVO vo = null;
        try {
            con = dataSource.getConnection();
            String getPostIdSql = "SELECT * FROM post WHERE member_id = ? and post_id = (select max(post_id) from post where member_id = ?)";
            
            pstmt = con.prepareStatement(getPostIdSql);
            pstmt.setString(1, memberId);
            pstmt.setString(2, memberId);
            rs = pstmt.executeQuery();
            
            if(rs.next()) {
                vo = new PostVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), null,null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, pstmt, con);
        }
        return vo;
	}
}

