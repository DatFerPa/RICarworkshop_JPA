package uo.ri.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import uo.ri.model.types.AveriaStatus;

public class Averia {

	private String descripcion;
	private Date fecha;
	private double importe = 0.0;
	private AveriaStatus status = AveriaStatus.ABIERTA;
	private Vehiculo vehiculo;
	private Set<Intervencion> intervenciones = new HashSet<>();

	public Averia(Vehiculo vehiculo) {
		super();
		this.vehiculo = vehiculo;
		Association.Averiar.link(vehiculo, this);
		fecha = new Date();
	}

	public Averia(Vehiculo vehiculo, String descripcion) {
		this(vehiculo);
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return fecha;
	}

	public double getImporte() {
		return importe;
	}

	public AveriaStatus getStatus() {
		return status;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	void _setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	
	

	public Set<Intervencion> getIntervenciones() {
		return new HashSet<>(intervenciones);
	}
	
	 Set<Intervencion> _getIntervenciones() {
		return intervenciones;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((vehiculo == null) ? 0 : vehiculo.hashCode());
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
		Averia other = (Averia) obj;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (vehiculo == null) {
			if (other.vehiculo != null)
				return false;
		} else if (!vehiculo.equals(other.vehiculo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Averia [descripcion=" + descripcion + ", fecha=" + fecha + ", importe=" + importe + ", status=" + status
				+ ", vehiculo=" + vehiculo + "]";
	}

	/**
	 * Asigna la averia al mecanico
	 * 
	 * @param mecanico
	 */
	public void assignTo(Mecanico mecanico) {
		// Solo se puede asignar una averia que está ABIERTA
		// linkado de averia y mecanico
		// la averia pasa a ASIGNADA
	}

	/**
	 * El mecánico da por finalizada esta avería, entonces se calcula el importe
	 * 
	 */
	public void markAsFinished() {
		// Se verifica que está en estado ASIGNADA
		// se calcula el importe
		// se desvincula mecanico y averia
		// el status cambia a TERMINADA
	}

	/**
	 * Una averia en estado TERMINADA se puede asignar a otro mecánico (el primero
	 * no ha podido terminar la reparación), pero debe ser pasada a ABIERTA primero
	 */
	public void reopen() {
		// Solo se puede reabrir una averia que está TERMINADA
		// la averia pasa a ABIERTA
	}

	/**
	 * Una avería ya facturada se elimina de la factura
	 */
	public void markBackToFinished() {
		// verificar que la averia está FACTURADA
		// cambiar status a TERMINADA
	}
}