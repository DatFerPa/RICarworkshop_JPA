package uo.ri.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import alb.util.date.DateUtil;
import uo.ri.util.exception.BusinessException;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "TTARJETASCREDITO")
public class TarjetaCredito extends MedioPago {

	@Column(unique = true)
	private String numero;
	private String tipo;
	private Date validez;

	TarjetaCredito() {
	}

	public TarjetaCredito(String numero) {
		super();
		this.numero = numero;
		this.validez = DateUtil.tomorrow();
		this.tipo = "UNKNOWN";
	}

	public TarjetaCredito(String numero, String tipo, Date validez) {
		this(numero);
		this.tipo = tipo;
		this.validez = validez;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getValidez() {
		return validez;
	}

	public void setValidez(Date validez) {
		this.validez = validez;
	}

	public String getNumero() {
		return numero;
	}

	@Override
	public String toString() {
		return "TarjetaCredito [numero=" + numero + ", tipo=" + tipo + ", validez=" + validez + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		TarjetaCredito other = (TarjetaCredito) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

	public void pagar(double i) throws BusinessException {
		if (isValidNow()) {
			this.acumulado += i;
		} else {
			throw new BusinessException("No se puede pagar con una tarjeta de crédito que tenga una fecha atrasada");
		}

	}

	public boolean isValidNow() {
		return (validez.after(DateUtil.today())) ? true : false;
	}

}
