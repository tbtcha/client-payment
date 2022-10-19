package dar.kz.backend.clientpayment.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SendServiceImpl implements SendService{
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Override
    public void send(String message) {
        kafkaTemplate.send("post-core", message);
    }
}
