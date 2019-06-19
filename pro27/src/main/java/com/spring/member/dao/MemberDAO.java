package com.spring.member.dao;

import com.spring.member.vo.MemberVO;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface MemberDAO {
    List selectAllMemberList() throws DataAccessException;
    int insertMember(MemberVO memberVO) throws DataAccessException ;
    int deleteMember(String id) throws DataAccessException;
    MemberVO loginById(MemberVO memberVO) throws DataAccessException;
}
