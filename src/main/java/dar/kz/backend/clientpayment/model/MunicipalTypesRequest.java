package dar.kz.backend.clientpayment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MunicipalTypesRequest {
    private String TypeId;
    private String CompanyName;
    private String TypeName;
}
