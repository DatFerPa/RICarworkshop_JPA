package uo.ri.ui.foreman.action.cliente;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.ForemanService;
import uo.ri.business.dto.ClientDto;
import uo.ri.conf.Factory;
import uo.ri.ui.util.Printer;

public class ListClientesRecomendadosPorAction implements Action {

	@Override
	public void execute() throws Exception {
		ForemanService fs = Factory.service.forForeman();
		Long id = Console.readLong("ID:");
		List<ClientDto> clientes = fs.findClientesRecomendadosPor(id);
		Console.println("\nListado de clientes recomendados por el usuario ID:" + id + " \n");
		for (ClientDto c : clientes) {
			Printer.printCLiente(c);
		}

	}

}
