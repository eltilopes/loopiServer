package br.com.aio.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Objects;
import java.util.Properties;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.aio.model.entity.vo.MobileVersionVo;
import br.com.aio.model.repository.dao.MobileDao;

@RestController
@RequestMapping("/mobile")
public class MobileController {
	
	@Inject
	private MobileDao dao;
	@Resource(name = "messages")
	private Properties messages;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/version", method = GET)
	public ResponseEntity isValidVersion(@RequestParam Integer versionCode, @RequestParam String versionName){
		MobileVersionVo version = dao.getVersionActive(versionCode);
//		if(Objects.isNull(version)){
//			return new ResponseEntity(messages.getProperty("mobile.version.nocontent"), HttpStatus.NO_CONTENT);
//		}
		return new ResponseEntity<Boolean>(Objects.isNull(version), HttpStatus.OK);
	}
}
