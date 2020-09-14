package kafka_start;

import dml.KafkaDmlMain;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;

import java.util.Properties;

public class ProducerDemo {

    @Test
    public void product(){
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"CentOS_lwh:9092");
        //消息 k-v 以字符串形式序列化
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> stringStringKafkaProducer = new KafkaProducer<>(properties);

        for(int i=0;i<5;i++){
            ProducerRecord<String, String> record
                    = new ProducerRecord<String, String>("topic03","pro_"+i,"val_i");
            stringStringKafkaProducer.send(record);
        }

        stringStringKafkaProducer.close();

    }

}
