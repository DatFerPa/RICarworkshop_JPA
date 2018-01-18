package uo.ri.business.dto;

/**
 * An aggregated result of all vouchers of a client
 */
public class VoucherSummary {

	public String dni;
	public String name;
	public String surname;
	public int emitted;
	public double totalAmount;
	public double available;
	public double consumed;

}
