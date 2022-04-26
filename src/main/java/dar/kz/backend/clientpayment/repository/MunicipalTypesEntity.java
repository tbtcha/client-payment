package dar.kz.backend.clientpayment.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "municipal-types")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MunicipalTypesEntity {
    @Id
    @Field(type = FieldType.Keyword)
    private String TypesId;

    @Field(type = FieldType.Text)
    private String TypeName;

    @Field(type = FieldType.Text)
    private String CompanyName;
}
