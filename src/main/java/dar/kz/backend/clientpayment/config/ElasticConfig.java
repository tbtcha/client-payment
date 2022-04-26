package dar.kz.backend.clientpayment.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

@Configuration
public class ElasticConfig extends AbstractElasticsearchConfiguration {
    @Override
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("ruslan.es.eu-west-1.aws.found.io:9243")
                .usingSsl()
                .withBasicAuth("elastic", "L068NgXz8oV1usKr9tW4X3M9")
                .build();
        return RestClients.create(clientConfiguration).rest();
    }
}
