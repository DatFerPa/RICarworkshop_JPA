package uo.ri.ui.admin;

import alb.util.menu.BaseMenu;
import uo.ri.ui.admin.action.VouchersByBreackdownsAction;

public class VouchersMenu extends BaseMenu{
	
	public VouchersMenu() {
		menuOptions = new Object[][] {
			{"Administrador > Gestión de bonos",null},
			{"Generar bonos por tres averias",VouchersByBreackdownsAction.class},
		};
	}

}
