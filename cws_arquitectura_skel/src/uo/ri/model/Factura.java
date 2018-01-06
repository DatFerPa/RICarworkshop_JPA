package uo.ri.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import alb.util.date.DateUtil;
import alb.util.math.Round;
import uo.ri.model.types.AveriaStatus;
import uo.ri.model.types.FacturaStatus;
import uo.ri.util.exception.BusinessException;

@Entity
@Table(name = "TFACTURAS")
public class Factura {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private Long numero;
	@Temporal(TemporalType.DATE)
	private Date fecha;
	private Double importe = 0.0;
	private Double iva = 0.0;

	@Enumerated(EnumType.STRING)
	private FacturaStatus status = FacturaStatus.SIN_ABONAR;
	private boolean usada_bono = false;

	@OneToMany(mappedBy = "factura")
	private Set<Averia> averias = new HashSet<>();
	@OneToMany(mappedBy = "factura")
	private Set<Cargo> cargos = new HashSet<>();

	Factura() {
	}

	public Factura(Long numero) {
		super();
		this.numero = numero;
		this.fecha = DateUtil.today();
	}

	public Factura(long numero, Date fecha) {
		this(numero);
		this.fecha = fecha;
	}

	public Factura(long numero, List<Averia> averias) throws BusinessException {
		this(numero);
		this.fecha = DateUtil.today();

		for (Averia a : averias) {
			if (!a.getStatus().equals(AveriaStatus.TERMINADA))
				throw new BusinessException("Alguna de las averías no está terminada");
			Association.Facturar.link(a, this);
			a.markAsInvoiced();
		}
	}

	public Factura(long numero, Date fecha, List<Averia> averias) throws BusinessException {
		this(numero, fecha);

		for (Averia a : averias) {
			if (!a.getStatus().equals(AveriaStatus.TERMINADA))
				throw new BusinessException("Alguna de las averías no está terminada");
			Association.Facturar.link(a, this);
			a.markAsInvoiced();
		}

	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getIva() {
		return iva;
	}

	public Long getNumero() {
		return numero;
	}

	public double getImporte() {
		calcularImporte();
		return importe;
	}

	public boolean isUsada_bono() {
		return usada_bono;
	}

	public void setUsada_bono(boolean usada_bono) {
		this.usada_bono = usada_bono;
	}

	public FacturaStatus getStatus() {
		return status;
	}

	Set<Averia> _getAverias() {
		return averias;
	}

	public Set<Averia> getAverias() {
		return new HashSet<>(averias);
	}

	Set<Cargo> _getCargos() {
		return cargos;
	}

	public Set<Cargo> getCargos() {
		return new HashSet<>(cargos);
	}

	public Long getId() {
		return id;
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
		Factura other = (Factura) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

	/**
	 * Añade la averia a la factura
	 * 
	 * @param averia
	 * @throws BusinessException
	 */
	public void addAveria(Averia averia) throws BusinessException {

		if (status.equals(FacturaStatus.SIN_ABONAR) && averia.getStatus().equals(AveriaStatus.TERMINADA)) {
			Association.Facturar.link(averia, this);
			averia.markAsInvoiced();
			calcularImporte();
		}
	}

	/**
	 * Calcula el importe de la avería y su IVA, teniendo en cuenta la fecha de
	 * factura
	 */
	void calcularImporte() {

		iva = (fecha.getTime() > DateUtil.fromDdMmYyyy(1, 7, 2012).getTime()) ? .21 : .18;
		importe = (double) 0;
		for (Averia a : averias) {
			importe += a.getImporte();

		}
		importe *= 1 + iva;
		importe = Round.twoCents(importe);
	}

	/**
	 * Elimina una averia de la factura, solo si está SIN_ABONAR y recalcula el
	 * importe
	 * 
	 * @param averia
	 */
	public void removeAveria(Averia averia) {

		if (status.equals(FacturaStatus.SIN_ABONAR)) {
			Association.Facturar.unlink(averia, this);
			averia.markBackToFinished();
			calcularImporte();
		}
	}

	@Override
	public String toString() {
		return "Factura [numero=" + numero + ", fecha=" + fecha + ", importe=" + importe + ", iva=" + iva + ", status="
				+ status + "]";
	}

	public void settle() throws BusinessException {
		if (averias.isEmpty()) {
			throw new BusinessException("No se puede liquidar una factura sin averías");
		}
		
		if(importe==0 && cargos.isEmpty()) {
			this.status = FacturaStatus.ABONADA;
			return;
		}
		
		if (!comprobacionEnMargen()) {
			throw new BusinessException("Pago de la factura no se encuentra dentro del +-0.01€ de margen");
		}

		this.status = FacturaStatus.ABONADA;

	}

	private boolean comprobacionEnMargen() {
		double dineroCargado = 0;
		for (Cargo c : cargos) {
			dineroCargado += c.getImporte();
		}
		return (dineroCargado <= importe + 0.01 && dineroCargado >= importe - 0.01) ? true : false;

	}

	public boolean isBono500Used() {
		return usada_bono;
	}

	public boolean puedeGenerarBono500() {
		return (status.equals(FacturaStatus.ABONADA) && usada_bono == false && importe > 500) ? true : false;
	}

	public boolean isSettled() {
		return (status.equals(FacturaStatus.ABONADA)) ? true : false;
	}

	public void markAsBono500Used() throws BusinessException {
		if (isSettled() && importe >= 500) {
			usada_bono = true;
		} else {
			throw new BusinessException("No se puede marcar como usada una factura no pagada");
		}
	}

}
