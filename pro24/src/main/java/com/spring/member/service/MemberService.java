package com.spring.member.service;

import com.spring.member.vo.MemberVO;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface MemberService {
    List listMembers() throws DataAccessException;
    int addMember(MemberVO membeVO) throws DataAccessException;
    int removeMember(String id) throws DataAccessException;
}
