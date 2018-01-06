package uo.ri.ui.admin.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.AdminService;
import uo.ri.conf.Factory;

public class VouchersByBreackdownsAction implements Action{

	@Override
	public void execute() throws Exception {
		AdminService as = Factory.service.forAdmin();
		int numeroBonos = as.generateVouchers();
		Console.println("Se han generado "+numeroBonos+" bonos nuevos");
		
	}

}
