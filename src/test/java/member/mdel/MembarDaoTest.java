package member.mdel;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import member.model.Member;
import member.model.MemberDao;
import mvc.util.MySalSessionFactory;

public class MembarDaoTest {
	
	//@Test
	public void testInsert(){
		SqlSession session = null;
		
		try {
			session = MySalSessionFactory.openSession();
			
			MemberDao dao = session.getMapper(MemberDao.class);
			
			
			Member mem = new Member("test222","테스트","123",new Date());
			dao.insert(mem);
			
			session.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			MySalSessionFactory.closeSession(session);
		}
	}
	
	//@Test
	public void testSelecte(){
		SqlSession session = null;
		
		try {
			session = MySalSessionFactory.openSession();
			
			MemberDao dao = session.getMapper(MemberDao.class);
			
			List<Member> list= dao.listAll();
			if(list.size()>0){
				for(Member member :list){
					System.out.println(member.toString());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			MySalSessionFactory.closeSession(session);
		}
	}
	
	//@Test
	public void testSelecteById(){
		SqlSession session = null;
		
		try {
			session = MySalSessionFactory.openSession();
			
			MemberDao dao = session.getMapper(MemberDao.class);
			
			Member m = dao.selectById("test");

			System.out.println(m.toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			MySalSessionFactory.closeSession(session);
		}
	}
	
	@Test
	public void testUpdate(){
		SqlSession session = null;
		
		try {
			session = MySalSessionFactory.openSession();
			
			MemberDao dao = session.getMapper(MemberDao.class);
			
			
			Member mem = new Member("test222","테스트22","1234",null);
			dao.update(mem);
			
			session.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			MySalSessionFactory.closeSession(session);
		}
	}
	
	
}
