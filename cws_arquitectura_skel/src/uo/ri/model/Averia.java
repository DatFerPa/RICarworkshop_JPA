package uo.ri.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import uo.ri.model.types.AveriaStatus;
import uo.ri.model.types.FacturaStatus;
import uo.ri.util.exception.BusinessException;
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames="FECHA, VEHICULO_ID")})

public class Averia {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String descripcion;
	private Date fecha;
	private double importe = 0.0;
	private AveriaStatus status = AveriaStatus.ABIERTA;
	private boolean usada_bono;
	
	@ManyToOne private Vehiculo vehiculo;
	@ManyToOne private Mecanico mecanico;
	@OneToMany(mappedBy="averia") private Set<Intervencion> intervenciones = new HashSet<>();
	@ManyToOne private Factura factura;
	
	Averia(){}
	
	public Averia(Vehiculo vehiculo) {
		super();
		this.fecha = new Date();
		Association.Averiar.link(vehiculo, this);
		this.usada_bono = false;
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

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	void _setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Date getFecha() {
		return (Date) fecha.clone(); // hay que hacer esto porque son mutables
	}

	public double getImporte() {
		calcularImporte();
		return importe;
	}

	public AveriaStatus getStatus() {
		return status;
	}

	public Mecanico getMecanico() {
		return mecanico;
	}

	void _setMecanico(Mecanico mecanico) {
		this.mecanico = mecanico;
	}

	Set<Intervencion> _getIntervenciones() {
		return intervenciones;
	}

	public Set<Intervencion> getIntervenciones() {
		return new HashSet<>(intervenciones);
	}

	public Factura getFactura() {
		return factura;
	}

	void _setFactura(Factura factura) {
		this.factura = factura;
	}

	public boolean isUsada_bono() {
		return usada_bono;
	}
	
	public Long getId() {
		return id;
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

	/**
	 * Asigna la averia al mecanico
	 * 
	 * @param mecanico
	 */
	public void assignTo(Mecanico mecanico) {
		// Solo se puede asignar una averia que está ABIERTA
		// linkado de averia y mecanico
		// la averia pasa a ASIGNADA
		if (status.equals(AveriaStatus.ABIERTA)) {
			Association.Asignar.link(mecanico, this);
			status = AveriaStatus.ASIGNADA;
		}
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
		if (status.equals(AveriaStatus.ASIGNADA)) {
			// calcular importe
			calcularImporte();
			Association.Asignar.unlink(mecanico, this);
			status = AveriaStatus.TERMINADA;
		}

	}

	private void calcularImporte() {
		importe = 0;
		for (Intervencion i : intervenciones) {
			importe += i.getImporte();
		}
	}

	/**
	 * Una averia en estado TERMINADA se puede asignar a otro mecánico (el primero
	 * no ha podido terminar la reparación), pero debe ser pasada a ABIERTA primero
	 */
	public void reopen() {
		// Solo se puede reabrir una averia que está TERMINADA
		// la averia pasa a ABIERTA
		if (status.equals(AveriaStatus.TERMINADA)) {
			status = AveriaStatus.ABIERTA;
		}
	}

	/**
	 * Una avería ya facturada se elimina de la factura
	 */
	public void markBackToFinished() {
		// verificar que la averia está FACTURADA
		// cambiar status a TERMINADA
		if (status.equals(AveriaStatus.FACTURADA)) {
			status = AveriaStatus.TERMINADA;
		}
	}

	@Override
	public String toString() {
		return "Averia [descripcion=" + descripcion + ", fecha=" + fecha + ", importe=" + importe + ", status=" + status
				+ ", vehiculo=" + vehiculo + ", mecanico=" + mecanico + ", intervenciones=" + intervenciones
				+ ", factura=" + factura + ", usada_bono=" + usada_bono + "]";
	}

	/**
	 * Cambia el estado de la avería a facturada
	 * @throws BusinessException 
	 */
	public void markAsInvoiced() throws BusinessException {
		if(factura==null) {
			throw new BusinessException("No factura asignada para esta averia");
		}
		status = AveriaStatus.FACTURADA;
	}

	public boolean esElegibleParaBono3() {
		return (!usada_bono && status.equals(AveriaStatus.FACTURADA)
				&& factura.getStatus().equals(FacturaStatus.ABONADA)) ? true : false;
	}

	public void markAsBono3Used() {
		this.usada_bono = true;

	}

	public void desassign() {
		Association.Asignar.unlink(mecanico, this );
		
	}
	
}
