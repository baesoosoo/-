package egovframework.let.cop.alarm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import egovframework.let.cop.alarm.service.AlarmService;
import egovframework.let.cop.alarm.vo.AlarmVO;

/**
* @packageName    : egovframework.let.cop.alarm.controller
* @fileName        : AlarmController.java
* @author        : yhs
* @date            : 2024.08.25
* @description            :
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2024.08.25        yhs       최초 생성
*/

@Controller
public class AlarmController {
	
	@Autowired
	AlarmService alarmService;
	
	/**
	 * 태세발령현황 조회
	 * @return
	 * @throws Exception
	 */
	/*
	 * @RequestMapping("/selectAlarm.json") public ModelAndView
	 * selectAlarm(HttpServletRequest reqeust, HttpServletResponse response) throws
	 * Exception { ModelAndView mav = new ModelAndView("jsonView");
	 * 
	 * AlarmVO alarmInfo = alarmService.selectAlarm();
	 * 
	 * mav.addObject("alarmInfo", alarmInfo);
	 * 
	 * return mav; }
	 * 
	 * @RequestMapping("/alarmSetttingView.do") public String
	 * alarmSetttingView(Model model) throws Exception {
	 * 
	 * AlarmVO alarmInfo = alarmService.selectAlarm();
	 * 
	 * model.addAttribute("alarmInfo", alarmInfo);
	 * 
	 * return "cop/alarmSetting"; }
	 */
	/**
	 * 태세발령현황 수정
	 * @param alarmVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateAlarm.json")
	public ModelAndView updateAlarm(HttpServletRequest reqeust, HttpServletResponse response, AlarmVO alarmVO) throws Exception {
		ModelAndView mav = new ModelAndView("jsonView");
		
		int resultCount = 0;
		String resultCode = "N";
		
		resultCount = alarmService.updateAlarm(alarmVO);
		
		if(resultCount > 0) {
			resultCode = "Y";
		}
		
		mav.addObject("resultCode", resultCode);
		
		return mav;
	}
}
