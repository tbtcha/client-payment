package dar.kz.backend.clientpayment.service;

import dar.kz.backend.clientpayment.model.MunicipalOfficeRequest;
import dar.kz.backend.clientpayment.model.MunicipalOfficeResponse;
import dar.kz.backend.clientpayment.model.MunicipalTypesRequest;
import dar.kz.backend.clientpayment.model.MunicipalTypesResponse;
import dar.kz.backend.clientpayment.repository.MunicipalOfficeEntity;
import dar.kz.backend.clientpayment.repository.MunicipalOfficeRepo;
import dar.kz.backend.clientpayment.repository.MunicipalTypesEntity;
import dar.kz.backend.clientpayment.repository.MunicipalTypesRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MunicipalOfficeServiceImpl implements MunicipalOfficeService {

    @Autowired
    MunicipalTypesRepo municipalTypesRepo;

    @Autowired
    MunicipalOfficeRepo municipalOfficeRepo;

    ModelMapper modelMapper;

    public MunicipalOfficeServiceImpl(){
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public Page<MunicipalOfficeResponse> getMunicipalOfficesByClientId(String clientId, Pageable pageable) {
        Page<MunicipalOfficeEntity> officeEntities = municipalOfficeRepo.getMunicipalOfficeEntitiesByClientId(clientId, pageable);
        return officeEntities.map(municipalOfficeEntity -> modelMapper.map(municipalOfficeEntity, MunicipalOfficeResponse.class));
    }

    @Override
    public MunicipalTypesResponse getMunicipalTypeByTypeId(String typeId) {
        MunicipalTypesEntity typesEntity = municipalTypesRepo.getMunicipalTypesEntityByTypesId(typeId);
        return modelMapper.map(typesEntity, MunicipalTypesResponse.class);
    }

    @Override
    public MunicipalOfficeResponse createMunicipalOffice(MunicipalOfficeRequest municipalOfficeRequest) {
        municipalOfficeRequest.setOfficeId(UUID.randomUUID().toString());
        MunicipalOfficeEntity officeEntity = modelMapper.map(municipalOfficeRequest, MunicipalOfficeEntity.class);
        officeEntity = municipalOfficeRepo.save(officeEntity);
        return modelMapper.map(officeEntity, MunicipalOfficeResponse.class);
    }

    @Override
    public MunicipalTypesResponse createMunicipalType(MunicipalTypesRequest municipalTypesRequest) {
        municipalTypesRequest.setTypeId(UUID.randomUUID().toString());
        MunicipalTypesEntity typesEntity = modelMapper.map(municipalTypesRequest, MunicipalTypesEntity.class);
        typesEntity = municipalTypesRepo.save(typesEntity);
        return modelMapper.map(typesEntity, MunicipalTypesResponse.class);
    }
}
