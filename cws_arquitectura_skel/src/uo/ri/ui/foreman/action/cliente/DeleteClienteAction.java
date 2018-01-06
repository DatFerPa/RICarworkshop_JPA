package uo.ri.ui.foreman.action.cliente;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.ForemanService;
import uo.ri.conf.Factory;

public class DeleteClienteAction implements Action{

	@Override
	public void execute() throws Exception {
		Long id = Console.readLong("ID");
		
		ForemanService fs = Factory.service.forForeman();
		fs.deleteClient(id);
		
		Console.println("Se ha eliminado el CLiente");
		
	}

}
