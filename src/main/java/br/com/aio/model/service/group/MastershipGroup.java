package br.com.aio.model.service.group;

import org.springframework.stereotype.Service;

import br.com.aio.model.entity.enums.GroupDefault;

@Service
public class MastershipGroup extends AbstractGroupDefault{
	@Override
	GroupDefault getGroup() {
		return GroupDefault.MASTERSHIP;
	}
}
