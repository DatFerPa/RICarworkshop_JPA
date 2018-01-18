package uo.ri.business.impl;

import java.util.List;

import uo.ri.business.ForemanService;
import uo.ri.business.dto.ClientDto;
import uo.ri.business.impl.foreman.AddCliente;
import uo.ri.business.impl.foreman.DeleteCliente;
import uo.ri.business.impl.foreman.FindAllClients;
import uo.ri.business.impl.foreman.FindClienteById;
import uo.ri.business.impl.foreman.FindRecomendedBy;
import uo.ri.business.impl.foreman.UpdateCliente;
import uo.ri.conf.Factory;
import uo.ri.util.exception.BusinessException;

public class ForemanServiceImpl implements ForemanService {

	private CommandExecutor executor = Factory.executor.forExecutor();

	/**
	 * 
	 */
	@Override
	public void addClient(ClientDto client, Long id_cliente_recomendador) throws BusinessException {
		executor.execute(new AddCliente(client, id_cliente_recomendador));

	}

	/**
	 * Metodo para eliminar a un cliente
	 * 
	 * @param el
	 *            identificador único de cada cliente
	 * 
	 */
	@Override
	public void deleteClient(long id) throws BusinessException {
		executor.execute(new DeleteCliente(id));

	}

	/**
	 * Método para actualizar a un cliente de la base de datos
	 * 
	 * @param Los
	 *            nuevos valores del cliente, pasado en un DTO
	 * 
	 */
	@Override
	public void updateClient(ClientDto dto) throws BusinessException {
		executor.execute(new UpdateCliente(dto));
	}

	/**
	 * Metodo para encontrar a un cliente en concreto
	 * 
	 * @param el
	 *            identificador único de cada cliente
	 * @return El DTO del cliente
	 */
	@Override
	public ClientDto findClientById(Long id) throws BusinessException {
		return executor.execute(new FindClienteById(id));
	}

	/**
	 * Metodo para encontrar a todos los clinetes que se encuentran en la base de
	 * datos
	 * 
	 * @return una lista de DTO de clinete
	 * 
	 */
	@Override
	public List<ClientDto> findAllClients() throws BusinessException {
		return executor.execute(new FindAllClients());
	}

	/**
	 * Método para encontrar a los clientes que han sido recomendados por otro
	 * 
	 * @param id
	 *            el identificador del que ha recomendado
	 * @return una lista de DTO de clinete
	 * 
	 */
	public List<ClientDto> findClientesRecomendadosPor(Long id) throws BusinessException {
		return executor.execute(new FindRecomendedBy(id));
	}

}
