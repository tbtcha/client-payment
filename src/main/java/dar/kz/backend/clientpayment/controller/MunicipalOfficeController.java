package dar.kz.backend.clientpayment.controller;

import dar.kz.backend.clientpayment.model.MunicipalOfficeRequest;
import dar.kz.backend.clientpayment.model.MunicipalOfficeResponse;
import dar.kz.backend.clientpayment.model.MunicipalTypesRequest;
import dar.kz.backend.clientpayment.model.MunicipalTypesResponse;
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
    public MunicipalOfficeResponse createOffice(@RequestBody MunicipalOfficeRequest officeRequest){
        return officeService.createMunicipalOffice(officeRequest);
    }

    @PostMapping("/types")
    public MunicipalTypesResponse createType(@RequestBody MunicipalTypesRequest typesRequest){
        return officeService.createMunicipalType(typesRequest);
    }

    @GetMapping("/{id}")
    public Page<MunicipalOfficeResponse> getOffices(@PathVariable String id, Pageable pageable){
        return officeService.getMunicipalOfficesByClientId(id, pageable);
    }

    @GetMapping("/{id}")
    public MunicipalTypesResponse getType(@PathVariable String id){
        return officeService.getMunicipalTypeByTypeId(id);
    }
}
