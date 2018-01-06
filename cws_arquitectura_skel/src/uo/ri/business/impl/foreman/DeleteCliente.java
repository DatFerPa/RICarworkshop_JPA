package uo.ri.business.impl.foreman;

import uo.ri.business.impl.Command;
import uo.ri.business.repository.ClienteRepository;
import uo.ri.business.repository.MedioPagoRepository;
import uo.ri.business.repository.RecomendacionRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Association;
import uo.ri.model.Cliente;
import uo.ri.model.MedioPago;
import uo.ri.model.Recomendacion;
import uo.ri.util.exception.BusinessException;
import uo.ri.util.exception.Check;

public class DeleteCliente implements Command<Void> {

	private long id;

	private ClienteRepository cr = Factory.repository.forCliente();
	private MedioPagoRepository mp = Factory.repository.forMedioPago();
	private RecomendacionRepository rr = Factory.repository.forRecomendacion();

	public DeleteCliente(long id) {
		this.id = id;
	}

	@Override
	public Void execute() throws BusinessException {
		Cliente c = cr.findById(id);
		Check.isNotNull(c, "El cliente no existe");
		if (c.getVehiculos().size() != 0) {
			throw new BusinessException("El cliente no puede ser eliminado al tener vehículos registrados");
		}
		for (Recomendacion rRealizada : c.getRecomendacionesHechas()) {
			Association.Recomendar.unlink(c, rRealizada, rRealizada.getRecomendado());
			rr.remove(rRealizada);
		}
		if (c.getRecomendacionRecibida() != null) {
			Recomendacion r = c.getRecomendacionRecibida();
			Association.Recomendar.unlink(c.getRecomendacionRecibida().getRecomendador(), r,
					c);
			rr.remove(r);
		}
		for (MedioPago m : c.getMediosPago()) {
			//Association.Pagar.unlink(c, m);
			mp.remove(m);
		}
		cr.remove(c);

		return null;
	}
}
