package uo.ri.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import uo.ri.model.types.AveriaStatus;
import uo.ri.model.types.FacturaStatus;
import uo.ri.util.exception.BusinessException;

@Entity
@Table(name = "TAVERIAS", uniqueConstraints = { @UniqueConstraint(columnNames = "FECHA, VEHICULO_ID") })

public class Averia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String descripcion;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	private Double importe = 0.0;
	@Enumerated(EnumType.STRING)
	private AveriaStatus status = AveriaStatus.ABIERTA;
	private boolean usada_bono;

	@ManyToOne
	private Vehiculo vehiculo;
	@ManyToOne
	private Mecanico mecanico;
	@OneToMany(mappedBy = "averia")
	private Set<Intervencion> intervenciones = new HashSet<>();
	@ManyToOne
	private Factura factura;

	Averia() {
	}

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
		return (Date) fecha.clone();
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

		if (status.equals(AveriaStatus.ASIGNADA)) {

			calcularImporte();
			Association.Asignar.unlink(mecanico, this);
			status = AveriaStatus.TERMINADA;
		}

	}

	private void calcularImporte() {
		importe = (double) 0;
		for (Intervencion i : intervenciones) {
			importe += i.getImporte();
		}
	}

	/**
	 * Una averia en estado TERMINADA se puede asignar a otro mecánico (el primero
	 * no ha podido terminar la reparación), pero debe ser pasada a ABIERTA primero
	 */
	public void reopen() {

		if (status.equals(AveriaStatus.TERMINADA)) {
			status = AveriaStatus.ABIERTA;
		}
	}

	/**
	 * Una avería ya facturada se elimina de la factura
	 */
	public void markBackToFinished() {

		if (status.equals(AveriaStatus.FACTURADA)) {
			status = AveriaStatus.TERMINADA;
		}
	}

	@Override
	public String toString() {
		return "Averia [id=" + id + ", descripcion=" + descripcion + ", fecha=" + fecha + ", importe=" + importe
				+ ", status=" + status + ", usada_bono=" + "usada_bono]";
	}

	/**
	 * Cambia el estado de la avería a facturada
	 * 
	 * @throws BusinessException
	 */
	public void markAsInvoiced() throws BusinessException {
		if (factura == null) {
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
		Association.Asignar.unlink(mecanico, this);

	}

	public boolean isUsadaBono3() {
		return isUsada_bono();
	}

	public boolean isInvoiced() {
		return (status.equals(AveriaStatus.FACTURADA)) ? true : false;
	}

}
