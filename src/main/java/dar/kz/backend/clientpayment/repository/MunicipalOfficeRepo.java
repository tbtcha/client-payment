package dar.kz.backend.clientpayment.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface MunicipalOfficeRepo extends ElasticsearchRepository<MunicipalOfficeEntity, String> {
    Page<MunicipalOfficeEntity> getMunicipalOfficeEntitiesByStatus(String status, Pageable pageable);
    Page<MunicipalOfficeEntity> getMunicipalOfficeEntitiesByClientId(String clientId, Pageable pageable);

    MunicipalOfficeEntity getMunicipalOfficeEntityByClientIdAndAndOfficeId(String clientId, String officeId);
}
