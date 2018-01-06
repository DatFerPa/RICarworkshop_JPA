package uo.ri.business.impl.foreman;

import uo.ri.business.impl.Command;
import uo.ri.util.exception.BusinessException;

public class DeleteCliente  implements Command<Void>{
	
	private long id;
	
	public DeleteCliente(long id) {
		this.id = id;
	}

	@Override
	public Void execute() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
