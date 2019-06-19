package com.spring.member.service;

import com.spring.member.dao.MemberDAO;
import com.spring.member.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("memberService")
@Transactional(propagation = Propagation.REQUIRED)
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDAO memberDAO;

    @Override
    public List listMembers() throws DataAccessException {
        List<MemberVO> memberList = null;
        memberList = memberDAO.selectAllMemberList();
        return memberList;
    }

    @Override
    public int addMember(MemberVO memberVO) throws DataAccessException {
        int result = memberDAO.insertMember(memberVO);
        return result;
    }

    @Override
    public int removeMember(String id) throws DataAccessException {
        int result = memberDAO.deleteMember(id);
        return result;
    }

    @Override
    public MemberVO login(MemberVO memberVO) throws Exception {
        MemberVO result = memberDAO.loginById(memberVO);
        return result;
    }
}
