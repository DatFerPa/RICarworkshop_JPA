package uo.ri.business.impl.foreman;

import uo.ri.business.dto.ClientDto;
import uo.ri.business.impl.Command;
import uo.ri.util.exception.BusinessException;

public class FindClienteByDni  implements Command<ClientDto>{
	
	private String dni;
	
	public FindClienteByDni(String dni) {
		this.dni = dni;
	}

	@Override
	public ClientDto execute() throws BusinessException {
		return null;
	}

}
