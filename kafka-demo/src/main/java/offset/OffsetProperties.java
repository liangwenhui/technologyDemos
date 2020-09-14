package offset;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.consumer.OffsetCommitCallback;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class OffsetProperties {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"CentOS_lwh:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"topic03_group");


        //latest，earliest，none
        //第一次订阅topic的偏移量配置
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"latest");
        //自动提交 默认开启
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,true);
        //自动提交间隔 默认5秒
        properties.put(ConsumerConfig.CONNECTIONS_MAX_IDLE_MS_CONFIG,5000);

        /**
         * 手动提交offset
         */
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        while (true){
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(1));
            Iterator<ConsumerRecord<String, String>> recordIterator = consumerRecords.iterator();
            while (recordIterator.hasNext()){
                ConsumerRecord<String, String> record = recordIterator.next();
                String key = record.key();
                String value = record.value();
                long offset = record.offset();
                int partition = record.partition();
                Map<TopicPartition, OffsetAndMetadata> offsets=new HashMap<TopicPartition, OffsetAndMetadata>();

                offsets.put(new TopicPartition(record.topic(),partition),new OffsetAndMetadata(offset));
                consumer.commitAsync(offsets, new OffsetCommitCallback() {
                    @Override
                    public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
                        System.out.println("完成："+offset+"提交！");
                    }
                });
                System.out.println("key:"+key+",value:"+value+",partition:"+partition+",offset:"+offset);

            }
        }

    }
}
