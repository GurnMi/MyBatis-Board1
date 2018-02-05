package member.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.Member;
import member.service.MemberService;
import mvc.controller.CommandHandler;

public class ListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		MemberService service = MemberService.getInstance();
		List<Member> list = service.listAll();
		/*if(list.size()>0){
			for(Member member :list){
				System.out.println(member.toString());
			}
		}*/
		req.setAttribute("list", list);
		
		return "index.jsp";
	}

}
