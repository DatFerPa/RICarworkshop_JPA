package uo.ri.business;

import java.util.List;

import uo.ri.business.dto.MechanicDto;
import uo.ri.business.dto.VoucherSummary;
import uo.ri.util.exception.BusinessException;

public interface AdminService {

	void newMechanic(MechanicDto mecanico) throws BusinessException;

	void deleteMechanic(Long idMecanico) throws BusinessException;

	void updateMechanic(MechanicDto mecanico) throws BusinessException;

	MechanicDto findMechanicById(Long id) throws BusinessException;

	List<MechanicDto> findAllMechanics() throws BusinessException;

	int generateVouchers() throws BusinessException;

	List<VoucherSummary> getVoucherSummary();

}
