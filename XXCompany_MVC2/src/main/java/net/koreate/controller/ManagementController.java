package net.koreate.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.koreate.service.MemberService;
import net.koreate.vo.MemberVo;

@Controller
@RequestMapping("/management/*") // Spring Framework V2.5
public class ManagementController {
	
	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);
	
	@Inject
	MemberService service;
	
	/*
	@GetMapping(value = "/") // Spring Framework V4.3
	public void GetMethod() {
		logger.info("GetMethod Called!!!");
	}

	@PostMapping(value = "/") // Spring Framework V4.3
	public void PostMethod() {
		logger.info("PostMethod Called!!!");
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET) // Spring Framework V2.5
	public void GetMethod() {
		logger.info("GetMethod Called!!!");
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST) // Spring Framework V2.5
	public void PostMethod() {
		logger.info("PostMethod Called!!!");
	}
	*/
	
	@GetMapping(value = "/member") // Spring Framework V4.3
	public void HomeGetMethod(Model model) {
		logger.info("HomeGetMethod Called!!!");
		List<MemberVo> members = service.getMemberLists();
		model.addAttribute("members", members);
	}
	
	@PostMapping(value = "/authoritySave") // Spring Framework V4.3
	public ResponseEntity<String> authoritySavePostMethod(@RequestBody MemberVo vo) {
		logger.info("authoritySavePostMethod Called!!!");
		ResponseEntity<String> entity = null;
		try {
			service.authoritySave(vo);
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		}
		catch (Exception e) {
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}
	
	@PostMapping(value = "/info") // Spring Framework V4.3
	public ResponseEntity<MemberVo> infoPostMethod(@RequestParam("userid") int userid) {
		logger.info("infoPostMethod Called!!!");
		ResponseEntity<MemberVo> entity = null;
		try {
			MemberVo list = service.getUserInfoById(userid);
			entity = new ResponseEntity<>(list, HttpStatus.OK);
		}
		catch (Exception e) {
			entity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}
	
	@PostMapping(value = "/delete") // Spring Framework V4.3
	public ResponseEntity<String> deletePostMethod(@RequestParam("userid") int userid) {
		logger.info("deletePostMethod Called!!!");
		ResponseEntity<String> entity = null;
		try {
			service.memberDeleteById(userid);
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		}
		catch (Exception e) {
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return entity;
	}
}
