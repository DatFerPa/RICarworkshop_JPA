package uo.ri.business.impl.foreman;

import alb.util.assertion.Argument;
import uo.ri.business.dto.ClientDto;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.ClienteRepository;
import uo.ri.business.repository.MedioPagoRepository;
import uo.ri.business.repository.RecomendacionRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Averia;
import uo.ri.model.Cliente;
import uo.ri.model.Metalico;
import uo.ri.model.Recomendacion;
import uo.ri.model.Vehiculo;
import uo.ri.model.types.AveriaStatus;
import uo.ri.util.exception.BusinessException;
import uo.ri.util.exception.Check;

public class AddCliente implements Command<Void> {

	private ClientDto clientDto;
	private Long id_cliente_recomendador;

	private ClienteRepository cr = Factory.repository.forCliente();
	private MedioPagoRepository mp = Factory.repository.forMedioPago();
	private RecomendacionRepository rr = Factory.repository.forRecomendacion();

	public AddCliente(ClientDto client, Long id_cliente_recomendador) {
		this.clientDto = client;
		this.id_cliente_recomendador = id_cliente_recomendador;
	}

	@Override
	public Void execute() throws BusinessException {
		Argument.isNotNull(clientDto);

		assertNotRepeatDni(clientDto.dni);

		if (id_cliente_recomendador != null) {
			// assertRecomendadorExiste(id_cliente_recomendador);
			assertassertRecomendadorExisteYConUnaFacturaPagada(id_cliente_recomendador);
		}

		Cliente c = DtoAssembler.toEntity(clientDto);
		Metalico m = new Metalico(c);

		
		if (id_cliente_recomendador != null) {
			Cliente crecomendador = cr.findById(id_cliente_recomendador);
			Recomendacion r = new Recomendacion(crecomendador, c);
			rr.add(r);
		}
		mp.add(m);
		cr.add(c);

		return null;
	}

	private void assertassertRecomendadorExisteYConUnaFacturaPagada(Long id) throws BusinessException {
		Cliente c = cr.findById(id);
		Check.isNotNull(c, "No existe el cliente recomendador");
		for (Vehiculo v : c.getVehiculos()) {
			for (Averia a : v.getAverias()) {
				if (a.getStatus().equals(AveriaStatus.FACTURADA)) {
					return;
				}
			}
		}
		throw new BusinessException("Cliente no puede recomendar, ya que no tiene ninguna factura pagada");

	}

	private void assertNotRepeatDni(String dni) throws BusinessException {
		Cliente c = cr.findByDni(dni);
		Check.isNull(c, "Ya existe un cliente con ese dni");
	}

	private void assertRecomendadorExiste(Long id) throws BusinessException {
		Cliente c = cr.findById(id);
		Check.isNotNull(c, "No existe el cliente recomendador");
	}

}
