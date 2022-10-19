package dar.kz.backend.clientpayment.repository;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "municipal-office")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MunicipalOfficeEntity {

    @Id
    @Field(type = FieldType.Keyword)
    private String officeId;

    @Field(type = FieldType.Keyword)
    private String typeId;

    @Field(type = FieldType.Keyword)
    private String clientId;

    @Field(type = FieldType.Text)
    private String status;

    @Field(type = FieldType.Integer)
    private int price;

    @Field(type = FieldType.Date)
    private Date createdDate;

    @Field(type = FieldType.Date)
    private Date paymentDate;
}
