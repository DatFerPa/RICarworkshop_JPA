package uo.ri.business.impl.admin;

import java.util.List;

import alb.util.random.Random;
import uo.ri.business.impl.Command;
import uo.ri.business.repository.ClienteRepository;
import uo.ri.business.repository.MedioPagoRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Averia;
import uo.ri.model.Bono;
import uo.ri.model.Cliente;
import uo.ri.util.exception.BusinessException;

public class GenerateVouchersByBreakdowns implements Command<Integer> {

	private ClienteRepository cr = Factory.repository.forCliente();
	private MedioPagoRepository mr = Factory.repository.forMedioPago();

	private int numBonosGenerados = 0;

	@Override
	public Integer execute() throws BusinessException {

		List<Cliente> clientes = cr.findAll();

		for (Cliente cliente : clientes) {
			List<Averia> averiasparaBono = cliente.getAveriasBono3NoUsadas();
			;
			int generar = averiasparaBono.size() / 3;
			int finalizar = generar * 3;
			for (int i = 0; i < generar; i++) {
				Bono bono = new Bono(generateNewCode(), "Por tres averías", 20.0, cliente);
				mr.add(bono);
				++numBonosGenerados;
			}
			for (int j = 0; j < finalizar; j++) {
				averiasparaBono.get(j).markAsBono3Used();
			}

		}

		return numBonosGenerados;
	}

	private String generateNewCode() {
		return "V-" + Random.string(5) + "-" + Random.integer(1000, 999999);
	}

}
