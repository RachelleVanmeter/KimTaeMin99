package net.koreate.controller;

import java.util.zip.DataFormatException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionAdvice {
	/**
	 * 100 : 데이터의 일부를 서버가 받은 상태이다.
	 * 
	 * 200 : 정상적인 응답을 받은 상태로
	 * 204 : 정상처리되었으나 서버에서 보내줄 데이터가 없을 경우이다.
	 * 
	 * 301 : 요청된 페이지가 새로운 URL로 변경됨 ( Redirect 했을 시에 발생 )
	 * 304 : 이미 기존의 데이터와 변경된 것이 없음
	 * 
	 * 400 : 전송된 Request 문제 ( Bad Request exception )
	 * 403 : 서버에서 허락되지 않은 경우
	 * 404 : URL에 해당 소스를 찾을 수 없을 경우 ( File not found exception )
	 * 406 : 전송 방식이 허락되지 않을 경우
	 * 
	 * 500 : 서버단의 문제 ( 권한 문제 )
	 * 502 : 게이트웨이나 프락시 서버 의 과부하
	 * 503 : 일식적인 과부하 및 서비스 중단
	 * 504 : 지정된 처리시간이 지나서 처리되지 못함.
	 */
	
	@ExceptionHandler(DataFormatException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	private ModelAndView Error400(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("error/400");
		return mav;
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	private ModelAndView Error404(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("error/404");
		return mav;
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	private ModelAndView Error500(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("error/500");
		return mav;
	}
	
}