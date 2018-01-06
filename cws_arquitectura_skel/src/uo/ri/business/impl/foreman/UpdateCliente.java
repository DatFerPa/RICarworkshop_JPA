package uo.ri.business.impl.foreman;

import alb.util.assertion.Argument;
import uo.ri.business.dto.ClientDto;
import uo.ri.business.impl.Command;
import uo.ri.business.repository.ClienteRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Cliente;
import uo.ri.model.types.Address;
import uo.ri.util.exception.BusinessException;
import uo.ri.util.exception.Check;

public class UpdateCliente implements Command<Void> {

	ClientDto dto_cliente;

	ClienteRepository cp = Factory.repository.forCliente();

	public UpdateCliente(ClientDto dto_cliente) {
		this.dto_cliente = dto_cliente;
	}

	@Override
	public Void execute() throws BusinessException {
		Argument.isNotNull(dto_cliente);
		Cliente c = cp.findById(dto_cliente.id);
		Check.isNotNull(c, "El cliente no existe");
		c.setApellidos(dto_cliente.surname);
		c.setNombre(dto_cliente.name);
		c.setEmail(dto_cliente.email);
		c.setPhone(dto_cliente.phone);
		c.setAddress(new Address(dto_cliente.addressStreet, dto_cliente.addressCity, dto_cliente.addressZipcode));

		return null;
	}

}
