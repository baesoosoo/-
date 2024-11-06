package egovframework.let.cop.alarm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.let.cop.alarm.mapper.AlarmMapper;
import egovframework.let.cop.alarm.service.AlarmService;
import egovframework.let.cop.alarm.vo.AlarmVO;

/**
* @packageName    : egovframework.let.cop.alarm.service.impl
* @fileName        : AlarmServiceImpl.java
* @author        : yhs
* @date            : 2024.08.25
* @description            :
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2024.08.25        yhs       최초 생성
*/

@Service
public class AlarmServiceImpl implements AlarmService {

	@Autowired
	AlarmMapper alarmMapper;
	
	/**
	 * 태세발령현황 조회
	 * @return
	 * @throws Exception
	 */
	/*
	 * @Override public AlarmVO selectAlarm() throws Exception { return
	 * alarmMapper.selectAlarm(); }
	 */
	
	/**
	 * 태세발령현황 수정
	 * @param alarmVO
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updateAlarm(AlarmVO alarmVO) throws Exception {
		return alarmMapper.updateAlarm(alarmVO);
	}
	
}
