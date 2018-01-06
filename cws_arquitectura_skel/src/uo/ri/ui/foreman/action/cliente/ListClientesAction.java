package uo.ri.ui.foreman.action.cliente;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.ForemanService;
import uo.ri.business.dto.ClientDto;
import uo.ri.conf.Factory;
import uo.ri.ui.util.Printer;

public class ListClientesAction implements Action{

	@Override
	public void execute() throws Exception {
		ForemanService fs = Factory.service.forForeman();
		List<ClientDto> clientes = fs.findAllClients();
		Console.println("\nListado de clientes\n"); 
		for(ClientDto c:clientes) {
			Printer.printCLiente(c);
		}
		
	}

}
