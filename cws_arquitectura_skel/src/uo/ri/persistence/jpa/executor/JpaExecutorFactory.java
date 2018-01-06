package uo.ri.persistence.jpa.executor;

import uo.ri.business.impl.ComandExecutorFactory;
import uo.ri.business.impl.CommandExecutor;

public class JpaExecutorFactory implements ComandExecutorFactory {

	@Override
	public CommandExecutor forExecutor() {
		return new JpaCommandExecutor();
	}

}
