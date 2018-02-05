package member.service;




import java.util.List;

import org.apache.ibatis.session.SqlSession;

import member.model.Member;
import member.model.MemberDao;
import mvc.util.ConnectionProvider;
import mvc.util.MySalSessionFactory;
import mvc.util.jdbcUtil;

public class MemberService {
	private static final MemberService instance = new MemberService();
	
	public static MemberService getInstance() {
		return instance;
	}
	
	public MemberService() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// -2 : duplicateId
	public int insertMember(Member member){
		SqlSession session = null;
		try {
			session = MySalSessionFactory.openSession();
			MemberDao dao = session.getMapper(MemberDao.class);
			
			Member existMember= dao.selectById(member.getId());
			
			if(existMember != null){
				System.out.println("-2");
				return -2;
			}
			
			dao.insert(member);
			session.commit();
			
			return 1;			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			MySalSessionFactory.closeSession(session);
		}
		System.out.println("-1");
		return -1;

	}
	
	public int UpdateService(String id, String password, String newpw){
		SqlSession session = null;
		try {
			session = MySalSessionFactory.openSession();
			MemberDao dao = session.getMapper(MemberDao.class);
			
			Member member = dao.selectById(id);
			
			if(member.getPassword().equals(password) ==false){
				return -3;//비밀번호 불일치
			}
			member.changePassword(newpw);
			dao.update(member);
			
			session.commit();
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			MySalSessionFactory.closeSession(session);
		}
		return -1;
	}
	
	
	
	public List<Member> listAll(){
		SqlSession session = null;
		List<Member> list = null;
		try {
			
			session = MySalSessionFactory.openSession();
			MemberDao dao = session.getMapper(MemberDao.class);
			
			
			list= dao.listAll();
			/*if(list.size()>0){
				for(Member member :list){
					System.out.println(member.toString());
				}
			}*/
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			MySalSessionFactory.closeSession(session);
		}
		return list;
		
	}
	
	
}
