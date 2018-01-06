package uo.ri.business;

import java.util.List;

import uo.ri.business.dto.ClientDto;
import uo.ri.util.exception.BusinessException;

public interface ForemanService {

	void addClient(ClientDto client, Long Id_cliente_recomendador) throws BusinessException;

	void deleteClient(long id) throws BusinessException;

	void updateClient(ClientDto dto) throws BusinessException;

	ClientDto findClientById(Long id) throws BusinessException;

	List<ClientDto> findAllClients() throws BusinessException;
	

}
