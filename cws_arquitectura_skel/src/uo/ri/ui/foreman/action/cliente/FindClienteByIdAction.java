package uo.ri.ui.foreman.action.cliente;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.ForemanService;
import uo.ri.business.dto.ClientDto;
import uo.ri.conf.Factory;
import uo.ri.ui.util.Printer;
import uo.ri.util.exception.BusinessException;

public class FindClienteByIdAction implements Action{

	@Override
	public void execute() throws Exception {
		ForemanService fs = Factory.service.forForeman();
		Long id = Console.readLong("ID:");
		ClientDto cliente = fs.findClientById(id);
		if(cliente == null) {
			throw new BusinessException("El cliente que se busca no existe");
		}
		Printer.printCLiente(cliente);
	}

}
