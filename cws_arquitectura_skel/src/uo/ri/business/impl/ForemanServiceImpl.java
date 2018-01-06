package uo.ri.business.impl;

import java.util.List;

import uo.ri.business.ForemanService;
import uo.ri.business.dto.ClientDto;
import uo.ri.business.impl.foreman.AddCliente;
import uo.ri.business.impl.foreman.DeleteCliente;
import uo.ri.business.impl.foreman.FindAllClients;
import uo.ri.business.impl.foreman.FindClienteById;
import uo.ri.business.impl.foreman.FindClientesRecomendadosPor;
import uo.ri.business.impl.foreman.UpdateCliente;
import uo.ri.conf.Factory;
import uo.ri.util.exception.BusinessException;

public class ForemanServiceImpl implements ForemanService {

	private CommandExecutor executor = Factory.executor.forExecutor();

	@Override
	public void addClient(ClientDto client, Long id_cliente_recomendador) throws BusinessException {
		executor.execute(new AddCliente(client, id_cliente_recomendador));

	}

	@Override
	public void deleteClient(long id) throws BusinessException {
		executor.execute(new DeleteCliente(id));

	}

	@Override
	public void updateClient(ClientDto dto) throws BusinessException {
		executor.execute(new UpdateCliente(dto));
	}

	@Override
	public ClientDto findClientById(Long id) throws BusinessException {
		return executor.execute(new FindClienteById(id));
	}

	@Override
	public List<ClientDto> findAllClients() throws BusinessException {
		return executor.execute(new FindAllClients());
	}

	public List<ClientDto> findClientesRecomendadosPor(Long id) throws BusinessException {
		return executor.execute(new FindClientesRecomendadosPor(id));
	}	

}
