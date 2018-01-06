package uo.ri.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import uo.ri.util.exception.BusinessException;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "TBONOS")
public class Bono extends MedioPago {

	private double disponible = 0.0;
	private String descripcion;
	@Column(unique = true)
	private String codigo;

	Bono() {
	}

	public Bono(String codigo) {
		this.codigo = codigo;
		this.descripcion = "";
	}

	public Bono(String codigo, double disponible) {
		this(codigo);
		this.disponible = disponible;
	}

	public Bono(String codigo, String descripcion, double disponible) {
		this(codigo, disponible);
		this.descripcion = descripcion;
	}

	public Bono(String codigo, String descripcion, double disponible, Cliente cliente) {
		this(codigo, descripcion, disponible);
		Association.Pagar.link(this, cliente);
	}

	public double getDisponible() {
		return disponible;
	}

	public void setDisponible(double disponible) {
		this.disponible = disponible;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	@Override
	public String toString() {
		return "Bono [disponible=" + disponible + ", descripcion=" + descripcion + ", codigo=" + codigo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bono other = (Bono) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	public void pagar(double i) throws BusinessException {
		if (this.disponible >= i) {
			this.acumulado += i;
			this.disponible -= i;
		} else {
			throw new BusinessException(
					"No se puede pagar con un bono que tenga un saldo inferior al inporte que se quiere pagar");
		}

	}

}
