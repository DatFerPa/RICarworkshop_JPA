package uo.ri;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	uo.ri.associations.AllTests.class,
	uo.ri.amp.AllTests.class,
	uo.ri.domain.AllTests.class,
})
public class AllTests { }
