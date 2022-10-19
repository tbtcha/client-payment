package dar.kz.backend.clientpayment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import dar.kz.backend.clientpayment.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface MunicipalOfficeService {
    Page<MunicipalResponse> getMunicipalOfficesByClientId(String clientId, Pageable pageable);
    MunicipalTypesResponse getMunicipalTypeByTypeId(String typeId);
    MunicipalOfficeResponse createMunicipalOffice(MunicipalOfficeRequest municipalOfficeRequest) throws JsonProcessingException;
    MunicipalTypesResponse createMunicipalType(MunicipalTypesRequest municipalTypesRequest);

    Page<MunicipalTypesResponse> getAllTypes(Pageable pageable);
}
