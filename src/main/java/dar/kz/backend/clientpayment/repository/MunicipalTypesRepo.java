package dar.kz.backend.clientpayment.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface MunicipalTypesRepo extends ElasticsearchRepository<MunicipalTypesEntity, String> {
    MunicipalTypesEntity getMunicipalTypesEntityByTypesId(String typeId);
}
