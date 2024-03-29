package uo.ri.business;

import java.util.List;
import java.util.Map;

import uo.ri.business.dto.BreakdownDto;
import uo.ri.business.dto.CardDto;
import uo.ri.business.dto.InvoiceDto;
import uo.ri.business.dto.PaymentMeanDto;
import uo.ri.business.dto.VoucherDto;
import uo.ri.util.exception.BusinessException;

public interface CashService {

	InvoiceDto createInvoiceFor(List<Long> idsAveria) throws BusinessException;

	InvoiceDto findInvoice(Long numeroFactura) throws BusinessException;

	List<PaymentMeanDto> findPayMethodsForInvoice(Long idFactura) throws BusinessException;

	InvoiceDto settleInvoice(Long idFactura, Map<Long, Double> cargos) throws BusinessException;

	List<BreakdownDto> findRepairsByClient(String dni) throws BusinessException;

	void deletePaymentMean(Long id);

	void addCardPaymentMean(CardDto card) throws BusinessException;

	InvoiceDto findInvoiceByNumber(Long numero);

	void addVoucherPaymentMean(VoucherDto voucher);

}
