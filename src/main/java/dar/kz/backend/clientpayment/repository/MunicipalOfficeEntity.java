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
    private String OfficeId;

    @Field(type = FieldType.Keyword)
    private String TypeId;

    @Field(type = FieldType.Keyword)
    private String ClientId;

    @Field(type = FieldType.Text)
    private String Status;

    @Field(type = FieldType.Integer)
    private int Price;

    @Field(type = FieldType.Date)
    private Date CreatedDate;

    @Field(type = FieldType.Date)
    private Date PaymentDate;
}
