package dar.kz.backend.clientpayment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dar.kz.backend.clientpayment.model.*;
import dar.kz.backend.clientpayment.repository.MunicipalOfficeEntity;
import dar.kz.backend.clientpayment.repository.MunicipalOfficeRepo;
import dar.kz.backend.clientpayment.repository.MunicipalTypesEntity;
import dar.kz.backend.clientpayment.repository.MunicipalTypesRepo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@Slf4j
public class MunicipalOfficeServiceImpl implements MunicipalOfficeService {

    @Autowired
    MunicipalTypesRepo municipalTypesRepo;

    @Autowired
    MunicipalOfficeRepo municipalOfficeRepo;

    ModelMapper modelMapper;

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public MunicipalOfficeServiceImpl(){
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public Page<MunicipalResponse> getMunicipalOfficesByClientId(String clientId, Pageable pageable) {
        Page<MunicipalOfficeEntity> officeEntities = municipalOfficeRepo.getMunicipalOfficeEntitiesByClientId(clientId, pageable);
        Page<MunicipalOfficeResponse> officeResponses = officeEntities.map(municipalOfficeEntity -> modelMapper.map(municipalOfficeEntity, MunicipalOfficeResponse.class));
        List<MunicipalResponse> municipalResponses = new ArrayList<>();
        Iterator iterator = officeResponses.iterator();
        while (iterator.hasNext()){
            MunicipalOfficeResponse officeResponse = (MunicipalOfficeResponse) iterator.next();
            MunicipalTypesResponse typesResponse = modelMapper
                    .map(
                            municipalTypesRepo.getMunicipalTypesEntityByTypeId(officeResponse.getTypeId())
                            , MunicipalTypesResponse.class
                    );
            MunicipalResponse municipalResponse = modelMapper.map(officeResponse, MunicipalResponse.class);
            municipalResponse.setType(typesResponse);
            municipalResponses.add(municipalResponse);
        }
        return new PageImpl<>(municipalResponses,pageable,municipalResponses.size());
    }

    @Override
    public MunicipalTypesResponse getMunicipalTypeByTypeId(String typeId) {
        MunicipalTypesEntity typesEntity = municipalTypesRepo.getMunicipalTypesEntityByTypeId(typeId);
        return modelMapper.map(typesEntity, MunicipalTypesResponse.class);
    }

    @Override
    public MunicipalOfficeResponse createMunicipalOffice(MunicipalOfficeRequest municipalOfficeRequest) throws JsonProcessingException {
        municipalOfficeRequest.setOfficeId(UUID.randomUUID().toString());
        Date date = new Date();
        municipalOfficeRequest.setCreatedDate(date);
        MunicipalOfficeEntity officeEntity = modelMapper.map(municipalOfficeRequest, MunicipalOfficeEntity.class);
        officeEntity = municipalOfficeRepo.save(officeEntity);
        kafkaTemplate.send("post-core", objectMapper.writeValueAsString(municipalOfficeRequest));
        return modelMapper.map(officeEntity, MunicipalOfficeResponse.class);
    }

    @Override
    public MunicipalTypesResponse createMunicipalType(MunicipalTypesRequest municipalTypesRequest) {
        municipalTypesRequest.setTypeId(UUID.randomUUID().toString());
        log.info(municipalTypesRequest.getTypeId());
        MunicipalTypesEntity typesEntity = modelMapper.map(municipalTypesRequest, MunicipalTypesEntity.class);
        typesEntity = municipalTypesRepo.save(typesEntity);
        return modelMapper.map(typesEntity, MunicipalTypesResponse.class);
    }

    @Override
    public Page<MunicipalTypesResponse> getAllTypes(Pageable pageable) {
        Page<MunicipalTypesEntity> typesEntities = municipalTypesRepo.findAll(pageable);
        return typesEntities.map(typesEntity -> modelMapper.map(typesEntity, MunicipalTypesResponse.class));
    }
}
