package egovframework.let.cop.alarm.service;

import egovframework.let.cop.alarm.vo.AlarmVO;

/**
* @packageName    : egovframework.let.cop.alarm.service
* @fileName        : AlarmService.java
* @author        : yhs
* @date            : 2024.08.25
* @description            :
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2024.08.25        yhs       최초 생성
*/

public interface AlarmService {
	
	/**
	 * 태세발령현황 조회
	 * @return
	 * @throws Exception
	 */
	/* public AlarmVO selectAlarm() throws Exception; */
	
	/**
	 * 태세발령현황 수정
	 * @param alarmVO
	 * @return
	 * @throws Exception
	 */
	public int updateAlarm(AlarmVO alarmVO) throws Exception;
	
}
