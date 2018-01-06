package uo.ri.ui.foreman;

import alb.util.menu.BaseMenu;
import uo.ri.ui.foreman.action.cliente.AddClienteAction;
import uo.ri.ui.foreman.action.cliente.DeleteClienteAction;
import uo.ri.ui.foreman.action.cliente.FindClienteByIdAction;
import uo.ri.ui.foreman.action.cliente.ListClientesAction;
import uo.ri.ui.foreman.action.cliente.UpdateClienteAction;

public class ClientesMenu extends BaseMenu {

	public ClientesMenu() {
		menuOptions = new Object[][] { 
			{ "Jefe de Taller > Gestión de Clientes", null },

			{ "Añadir cliente", AddClienteAction.class }, 
			{ "Modificar datos de cliente", UpdateClienteAction.class }, 
			{ "Eliminar cliente", DeleteClienteAction.class }, 
			{ "Mostrar Cliente", FindClienteByIdAction.class },
			{ "Listar todos los clientes", ListClientesAction.class }, 
		};
	}

}
