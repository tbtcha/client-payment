package dar.kz.backend.clientpayment.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface MunicipalTypesRepo extends ElasticsearchRepository<MunicipalTypesEntity, String> {
    MunicipalTypesEntity getMunicipalTypesEntityByTypeId(String typeId);

    @Override
    Page<MunicipalTypesEntity> findAll(Pageable pageable);
}
