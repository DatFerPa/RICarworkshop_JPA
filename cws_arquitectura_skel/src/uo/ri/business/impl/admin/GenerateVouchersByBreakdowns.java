package uo.ri.business.impl.admin;



import java.util.List;

import alb.util.random.Random;
import uo.ri.business.impl.Command;
import uo.ri.business.repository.ClienteRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Cliente;
import uo.ri.util.exception.BusinessException;

public class GenerateVouchersByBreakdowns implements Command<Integer>{
	
	private ClienteRepository cr = Factory.repository.forCliente();

	@Override
	public Integer execute() throws BusinessException {
		int numBonosGenerados = 0;
		
		List<Cliente> clientes = cr.findAll();
		
		
		return numBonosGenerados;
	}
	
	private String generateNewCode() {
		return "V-" + Random.string(5) + "-" + Random.integer(1000, 999999);
	}

}
