package uo.ri.model;

import java.util.HashSet;
import java.util.Set;

import uo.ri.model.exception.BusinessException;

public abstract class MedioPago {

	protected double acumulado = 0.0;
	
	private Cliente cliente;
	private Set<Cargo> cargos = new HashSet<>();

	public Cliente getCliente() {
		return cliente;
	}

	void _setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public double getAcumulado() {
		return acumulado;
	}
	
	void _setAcumulado(double acumulado) {
		this.acumulado = acumulado;
	}

	Set<Cargo> _getCargos() {
		return cargos;
	}

	public Set<Cargo> getCargos() {
		return new HashSet<>( cargos );
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
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
		MedioPago other = (MedioPago) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		return true;
	}
	
	public abstract void pagar(double i) throws BusinessException ;
		
	
}
