package kafka_start;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;
import java.util.regex.Pattern;

public class ConsumerDemo {
    @Test
    public void consumer(){
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"CentOS_lwh:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"topic03_group");

        KafkaConsumer<String, String> c = new KafkaConsumer<>(properties);
        //订阅topic 以 topic开头
        c.subscribe(Pattern.compile("^topic.*$"));
        //c.subscribe(Arrays.asList("topic03"));

        while(true){
            ConsumerRecords<String,String> records = c.poll(Duration.ofSeconds(1));
            Iterator<ConsumerRecord<String,String>> iterator = records.iterator();
           while (iterator.hasNext()){
               ConsumerRecord<String, String> record = iterator.next();
               String key = record.key();
               String value = record.value();
               long offset = record.offset();
               int partition = record.partition();
               System.out.println("key:"+key+",value:"+value+",partition:"+partition+",offset:"+offset);
           }


        }


    }

}
