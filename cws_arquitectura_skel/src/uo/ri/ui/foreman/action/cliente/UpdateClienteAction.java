package uo.ri.ui.foreman.action.cliente;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.ForemanService;
import uo.ri.business.dto.ClientDto;
import uo.ri.conf.Factory;

public class UpdateClienteAction implements Action{

	@Override
	public void execute() throws Exception {
		Long id = Console.readLong("ID");
		String nombre = Console.readString("nombre");
		String apellido = Console.readString("apellido");
		String email = Console.readString("email");
		String phone = Console.readString("telefono");
		String street = Console.readString("street");
		String city = Console.readString("ciudad");
		String zipcode = Console.readString("zipcode");
		ClientDto client = new ClientDto();
		client.id = id;
		client.name = nombre;
		client.surname = apellido;
		client.email = email;
		client.phone = phone;
		client.addressStreet = street;
		client.addressCity = city;
		client.addressZipcode = zipcode;
		
		ForemanService fs = Factory.service.forForeman();
		fs.updateClient(client);
		
		Console.println("Cliente actualizado");
	}

}
