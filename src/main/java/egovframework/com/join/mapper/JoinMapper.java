package egovframework.com.join.mapper;


import org.egovframe.rte.psl.dataaccess.mapper.Mapper;
import egovframework.com.join.model.MemberDTO;


@Mapper
public interface JoinMapper {
	
	int insertMem(MemberDTO dto);
}
