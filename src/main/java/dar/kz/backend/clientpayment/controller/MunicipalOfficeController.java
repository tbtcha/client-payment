package dar.kz.backend.clientpayment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import dar.kz.backend.clientpayment.model.*;
import dar.kz.backend.clientpayment.service.MunicipalOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/municipal")
public class MunicipalOfficeController {

    @Autowired
    MunicipalOfficeService officeService;

    @PostMapping("/office")
    public MunicipalOfficeResponse createOffice(@RequestBody MunicipalOfficeRequest officeRequest) throws JsonProcessingException {
        return officeService.createMunicipalOffice(officeRequest);
    }

    @PostMapping("/types")
    public MunicipalTypesResponse createType(@RequestBody MunicipalTypesRequest typesRequest){
        return officeService.createMunicipalType(typesRequest);
    }

    @GetMapping("/office/{id}")
    public Page<MunicipalResponse> getOffices(@PathVariable String id, Pageable pageable){
        Page<MunicipalResponse> municipalResponse =officeService.getMunicipalOfficesByClientId(id, pageable);

        return municipalResponse;
    }

    @GetMapping("/types/{id}")
    public MunicipalTypesResponse getType(@PathVariable String id){
        return officeService.getMunicipalTypeByTypeId(id);
    }

    @GetMapping("/types")
    public Page<MunicipalTypesResponse> getAllTypes(Pageable pageable){
        return officeService.getAllTypes(pageable);
    }
}
