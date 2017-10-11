package br.com.aio.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.aio.model.entity.NotificationGroup;
import br.com.aio.model.entity.vo.NotificationGroupVo;
import br.com.aio.model.service.NotificationGroupService;

@RestController
@RequestMapping("/grupo")
public class NotificationGroupController {

	@Inject
	public NotificationGroupService grupoService;
	
	@RequestMapping(value = "/novo", method = POST)
	public ResponseEntity<String> saveGroup (@Valid @RequestBody NotificationGroup group){
		grupoService.save(group);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/listar")
	public ResponseEntity<List<NotificationGroupVo>> getGroups(){
		List<NotificationGroupVo> groups = grupoService.getGroups();
		if(Objects.isNull(groups) || groups.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<NotificationGroupVo>>(groups, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/remover", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeGroup(@RequestParam(required  = true) Long idGroup){
		grupoService.remove(idGroup);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@RequestMapping(value = "/editar", method = RequestMethod.PUT)
	public ResponseEntity<String> editGroup(@Valid @RequestBody NotificationGroup group){
		grupoService.update(group);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/buscar") 
	public ResponseEntity getUserById(@RequestParam(name="id", required = false) Long idGroup, @RequestParam(name="name", required = false) String nmGroup){
		if(Objects.nonNull(nmGroup)){
			List<NotificationGroup> groups = grupoService.getGroupByName(nmGroup);
			return new ResponseEntity<List<NotificationGroup>>(groups, HttpStatus.OK);
		}
		NotificationGroup group = grupoService.getGroupById(idGroup);
		if(Objects.isNull(group)){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<NotificationGroup>(group, HttpStatus.OK);
	}
}
