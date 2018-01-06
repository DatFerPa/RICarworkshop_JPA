package uo.ri.business.impl.foreman;

import java.util.List;

import uo.ri.business.dto.ClientDto;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.ClienteRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Cliente;
import uo.ri.util.exception.BusinessException;

public class FindAllClients implements Command<List<ClientDto>> {

	@Override
	public List<ClientDto> execute() throws BusinessException {
		ClienteRepository r = Factory.repository.forCliente();
		List<Cliente> lc = r.findAll();
		return DtoAssembler.toClientDtoList(lc);
	}
	

}
