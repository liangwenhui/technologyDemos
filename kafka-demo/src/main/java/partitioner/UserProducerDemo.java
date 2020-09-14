package partitioner;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class UserProducerDemo {

    public void product(){
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"CentOS_lwh:9092");
        //消息 k-v 以字符串形式序列化
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //配置自定义的分区器
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,UserDefPartitioner.class.getName());
        KafkaProducer<String, String> stringStringKafkaProducer = new KafkaProducer<>(properties);

    }
}
