package dar.kz.backend.clientpayment.service;

import dar.kz.backend.clientpayment.model.MunicipalOfficeRequest;
import dar.kz.backend.clientpayment.model.MunicipalOfficeResponse;
import dar.kz.backend.clientpayment.model.MunicipalTypesRequest;
import dar.kz.backend.clientpayment.model.MunicipalTypesResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface MunicipalOfficeService {
    Page<MunicipalOfficeResponse> getMunicipalOfficesByClientId(String clientId, Pageable pageable);
    MunicipalTypesResponse getMunicipalTypeByTypeId(String typeId);
    MunicipalOfficeResponse createMunicipalOffice(MunicipalOfficeRequest municipalOfficeRequest);
    MunicipalTypesResponse createMunicipalType(MunicipalTypesRequest municipalTypesRequest);
}
