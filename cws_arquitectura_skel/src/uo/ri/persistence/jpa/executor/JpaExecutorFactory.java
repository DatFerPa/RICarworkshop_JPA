package uo.ri.persistence.jpa.executor;

import uo.ri.business.impl.CommandExecutorFactory;
import uo.ri.business.impl.CommandExecutor;

public class JpaExecutorFactory implements CommandExecutorFactory {

	@Override
	public CommandExecutor forExecutor() {
		return new JpaCommandExecutor();
	}

}
