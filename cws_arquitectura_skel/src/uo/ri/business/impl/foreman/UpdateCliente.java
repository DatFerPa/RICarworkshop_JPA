package uo.ri.business.impl.foreman;

import uo.ri.business.dto.ClientDto;
import uo.ri.business.impl.Command;
import uo.ri.util.exception.BusinessException;

public class UpdateCliente  implements Command<Void>{
	
	ClientDto dto_cliente;
	
	public UpdateCliente(ClientDto dto_cliente) {
		this.dto_cliente = dto_cliente;
	}

	@Override
	public Void execute() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
