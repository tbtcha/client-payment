package dar.kz.backend.clientpayment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MunicipalOfficeRequest {
    private String OfficeId;
    private String TypeId;
    private String ClientId;
    private String Status;
    private int Price;
    private Date CreatedDate;
    private Date PaymentDate;
}
