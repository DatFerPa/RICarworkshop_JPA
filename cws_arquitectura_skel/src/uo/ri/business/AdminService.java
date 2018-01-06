package uo.ri.business;

import java.util.List;

import uo.ri.business.dto.MechanicDto;
import uo.ri.util.exception.BusinessException;

public interface AdminService {

	void newMechanic(MechanicDto mecanico) throws BusinessException;
	void deleteMechanic(Long idMecanico) throws BusinessException;
	void updateMechanic(MechanicDto mecanico) throws BusinessException;

	MechanicDto findMechanicById(Long id) throws BusinessException;
	List<MechanicDto> findAllMechanics() throws BusinessException;
	//se generan los bono
	int generateVouchers();
	
	// resto de métodos que faltan...

}
