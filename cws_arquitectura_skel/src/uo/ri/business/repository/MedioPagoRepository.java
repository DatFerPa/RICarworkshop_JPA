package uo.ri.business.repository;

import java.util.List;

import uo.ri.model.Bono;
import uo.ri.model.MedioPago;
import uo.ri.model.TarjetaCredito;

public interface MedioPagoRepository extends Repository<MedioPago> {
	
	List<MedioPago> findPaymentMeansByClientId(Long id);

	List<Bono> findVouchersByClientId(Long id);

	TarjetaCredito findCreditCardByNumber(String cardNumber);

	Bono findVoucherByCode(String code);

	List<MedioPago> findPaymentMeansByInvoiceId(Long idFactura);

	List<MedioPago> findByClientId(Long id);

	Object[] findAggregateVoucherDataByClientId(Long id);
}
