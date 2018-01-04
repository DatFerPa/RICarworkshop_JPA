package uo.ri.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "TMETALICOS")
public class Metalico extends MedioPago {
	
	Metalico() {
	}

	public Metalico(Cliente cliente) {
		Association.Pagar.link(this, cliente);
	}

	public void pagar(double i) {
		this.acumulado += i;
	}

}
