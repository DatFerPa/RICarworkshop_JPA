package uo.ri.ui.admin.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.AdminService;
import uo.ri.business.dto.MechanicDto;
import uo.ri.conf.Factory;
import uo.ri.util.exception.BusinessException;

public class UpdateMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {
		
		// Pedir datos
		Long id = Console.readLong("Id del mecánico"); 
		String nombre = Console.readString("Nombre"); 
		String apellidos = Console.readString("Apellidos");
		
		// Procesar
		AdminService as = Factory.service.forAdmin();

		MechanicDto m = as.findMechanicById(id);
		if ( m == null) {
			throw new BusinessException("No existe el mecánico");
		}
		m.name = nombre;
		m.surname = apellidos;  // el dni es la identidad, no se puede cambiar
		
		as.updateMechanic( m );
		
		// Mostrar resultado
		Console.println("Mecánico actualizado");
	}

}
