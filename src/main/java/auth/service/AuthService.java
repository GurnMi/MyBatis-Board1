package auth.service;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import member.model.Member;
import member.model.MemberDao;
import mvc.util.MySalSessionFactory;

public class AuthService {
	private static final AuthService instance = new AuthService();

	public static AuthService getInstance() {
		return instance;
	}

	public AuthService() {
		// TODO Auto-generated constructor stub
	}
	
	//-2 : notJoin 회원없음
	//-3 : 비밀번호 불일치
	//1 : 정상로그인
	public HashMap<String, Object> checkLoginMember(String id, String password){
		SqlSession session = null;
		HashMap<String, Object> map = new HashMap<>();
		
		try {
			session = MySalSessionFactory.openSession();
			MemberDao dao = session.getMapper(MemberDao.class);
			
			Member member = dao.selectById(id);
			if(member == null){
				map.put("error", -2);
				return map;	//회원없음
			}
			
			if(member.getPassword().equals(password) ==false){
				map.put("error", -3);
				return map;//비밀번호 불일치
			}
			map.put("error", 0);
			map.put("member", member);
			return map;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			MySalSessionFactory.closeSession(session);
		}
		return map;
		
	}
		
		
	
	
	
}
