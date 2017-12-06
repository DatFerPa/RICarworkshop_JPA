package uo.ri.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Metalico extends MedioPago {
	
	public Metalico(Cliente cliente) {
		Association.Pagar.link(this,cliente);
	}
	
	Metalico(){}

}
