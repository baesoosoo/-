package egovframework.com.join.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.com.join.mapper.JoinMapper;
import egovframework.com.join.model.MemberDTO;
import egovframework.com.join.service.JoinService;

@Service
public class JoinServiceImpl implements JoinService{
	
	@Autowired
	private JoinMapper joinMapper;
	

	@Override
	public int insertMem(MemberDTO dto) throws Exception {
		
		return joinMapper.insertMem(dto);
	}
	
	

}
