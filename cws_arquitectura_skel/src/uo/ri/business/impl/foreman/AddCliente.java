package uo.ri.business.impl.foreman;

import uo.ri.business.dto.ClientDto;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.ClienteRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Cliente;
import uo.ri.util.exception.BusinessException;

public class AddCliente implements Command<Void> {

	private ClientDto clientDto;
	private Long id_cliente_recomendador;
	
	private ClienteRepository r = Factory.repository.forCliente();

	public AddCliente(ClientDto client, Long id_cliente_recomendador) {
		this.clientDto = client;
		this.id_cliente_recomendador = id_cliente_recomendador;
	}

	@Override
	public Void execute() throws BusinessException {
		Cliente c = DtoAssembler.toEntity(clientDto);
		r.add(c);
		return null;
	}

}
