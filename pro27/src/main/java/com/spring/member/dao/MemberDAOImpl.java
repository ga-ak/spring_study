package com.spring.member.dao;

import com.spring.member.vo.MemberVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List selectAllMemberList() throws DataAccessException {
        List<MemberVO> membersList = sqlSession.selectList("mapper.member.selectAllMemberList");
        return membersList;
    }

    @Override
    public int insertMember(MemberVO memberVO) throws DataAccessException {
        int result = sqlSession.insert("mapper.member.insertMember", memberVO);
        return result;
    }

    @Override
    public int deleteMember(String id) throws DataAccessException {
        int result = sqlSession.delete("mapper.member.deleteMember", id);
        return result;
    }

    @Override
    public MemberVO loginById(MemberVO memberVO) throws DataAccessException {
        MemberVO result = sqlSession.selectOne("mapper.member.loginById", memberVO);
        return result;
    }
}