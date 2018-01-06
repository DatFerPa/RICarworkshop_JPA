package uo.ri.business.impl.foreman;

import java.util.List;

import uo.ri.business.dto.ClientDto;
import uo.ri.business.impl.Command;
import uo.ri.util.exception.BusinessException;

public class FindClientesRecomendadosPor implements Command<List<ClientDto>>{
	
	private Long id;
	
	public FindClientesRecomendadosPor(Long id) {
		this.id = id;
	}
	
	@Override
	public List<ClientDto> execute() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
