package uo.ri.ui.foreman.action.cliente;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.ForemanService;
import uo.ri.business.dto.ClientDto;
import uo.ri.conf.Factory;

public class AddClienteAction implements Action {

	@Override
	public void execute() throws Exception {
		ClientDto c = new ClientDto();
		c.dni = Console.readString("Dni");
		c.name = Console.readString("nombre");
		c.surname = Console.readString("apellidos");
		c.phone = Console.readString("Telefono");
		c.email = Console.readString("Email");
		c.addressStreet = Console.readString("Calle");
		c.addressCity = Console.readString("Ciudad");
		c.addressZipcode = Console.readString("Zipcode");
		Long id_recomendado = null;
		String texto = "";
		do {
			texto = Console.readString("¿Viene recomendado? SI/NO");
		} while (!texto.equals("SI") && !texto.equals("NO"));
		if (texto.equals("SI")) {
			id_recomendado = Console.readLong("Indetificador del usuario del que venga recomendado");
		}

		ForemanService fs = Factory.service.forForeman();
		fs.addClient(c, id_recomendado);

		Console.println("Nuevo cliente añadido");

	}

}
