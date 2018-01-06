package uo.ri.business.impl.foreman;

import uo.ri.business.dto.ClientDto;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.ClienteRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Cliente;
import uo.ri.util.exception.BusinessException;

public class FindClienteById implements Command<ClientDto>{

	private Long id;
	private ClienteRepository cr = Factory.repository.forCliente();
	
	public FindClienteById(Long id) {
		this.id = id;
	}
	
	@Override
	public ClientDto execute() throws BusinessException {
		Cliente c = cr.findById(id);
		if(c== null) {
			return null;
		}
		
		return DtoAssembler.toDto(c);
	}

}
