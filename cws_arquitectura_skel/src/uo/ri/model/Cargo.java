package uo.ri.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import uo.ri.model.types.FacturaStatus;
import uo.ri.util.exception.BusinessException;
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames="FACTURA_ID, MEDIOPAGO_ID")})
public class Cargo {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
	
	private Factura factura;
	private MedioPago medioPago;
	private double importe = 0.0;

	public Cargo(Factura factura, MedioPago medioPago) {
		super();
		Association.Cargar.link(factura, this, medioPago);
	}
	
	Cargo(){}

	public Cargo(Factura factura, MedioPago medioPago, double importe) throws BusinessException {
		// incrementar el importe en el acumulado del medio de pago
		// guardar el importe
		// enlazar (link) factura, este cargo y medioDePago
		this(factura, medioPago);
		medioPago.acumulado += importe;
		this.importe = importe;
	}
	
	

	public Long getId() {
		return id;
	}

	public Factura getFactura() {
		return factura;
	}

	void _setFactura(Factura factura) {
		this.factura = factura;
	}

	public MedioPago getMedioPago() {
		return medioPago;
	}

	void _setMedioPago(MedioPago medioPago) {
		this.medioPago = medioPago;
	}

	public double getImporte() {
		return importe;
	}

	/**
	 * Anula (retrocede) este cargo de la factura y el medio de pago Solo se puede
	 * hacer si la factura no está abonada Decrementar el acumulado del medio de
	 * pago Desenlazar el cargo de la factura y el medio de pago
	 * 
	 * @throws BusinessException
	 */
	public void rewind() throws BusinessException {
		if (factura.getStatus().equals(FacturaStatus.SIN_ABONAR)) {
			medioPago._setAcumulado(medioPago.getAcumulado() - importe);
			Association.Cargar.unlink(this);

		}

	}

}
