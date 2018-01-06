package uo.ri;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	uo.ri.associations.AllTests.class,
	uo.ri.domain.AllTests.class,
	uo.ri.persistence.PersistenceTest.class,
	uo.ri.business.impl.DtoAssemblerTests.class,
	uo.ri.amp.domain.AllTests.class,
	uo.ri.amp.TestsForUoMod_1.class
})
public class AllTests { }
