package uo.ri.business.impl.foreman;

import uo.ri.business.dto.ClientDto;
import uo.ri.business.impl.Command;
import uo.ri.util.exception.BusinessException;

public class FindClienteById implements Command<ClientDto>{

	private Long id;
	
	public FindClienteById(Long id) {
		this.id = id;
	}
	
	@Override
	public ClientDto execute() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
