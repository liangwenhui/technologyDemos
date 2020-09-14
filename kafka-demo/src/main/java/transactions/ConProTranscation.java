package transactions;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class ConProTranscation {

    public static void main(String[] args) {

        //1.生产者&消费者
        KafkaProducer<String,String> producer=buildKafkaProducer();
        KafkaConsumer<String, String> consumer = buildKafkaConsumer("group01");

        consumer.subscribe(Arrays.asList("topic01"));
        producer.initTransactions();//初始化事务

        try{
            while(true){
                ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(1));
                Iterator<ConsumerRecord<String, String>> consumerRecordIterator = consumerRecords.iterator();
                //开启事务控制
                producer.beginTransaction();
                Map<TopicPartition, OffsetAndMetadata> offsets=new HashMap<TopicPartition, OffsetAndMetadata>();
                while (consumerRecordIterator.hasNext()){
                    ConsumerRecord<String, String> record = consumerRecordIterator.next();
                    //创建Record
                    ProducerRecord<String,String> producerRecord
                            =new ProducerRecord<String,String>("topic02",record.key(),record.value());
                    producer.send(producerRecord);
                    //记录元数据
                    offsets.put(new TopicPartition(record.topic(),record.partition()),new OffsetAndMetadata(record.offset()+1));
                }
                //提交事务
                producer.sendOffsetsToTransaction(offsets,"group01");
                producer.commitTransaction();
            }
        }catch (Exception e){
            producer.abortTransaction();//终止事务
        }finally {
            producer.close();
        }
    }
    public static KafkaProducer<String,String> buildKafkaProducer(){
        Properties props=new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG,"transaction-id");
        return new KafkaProducer<String, String>(props);
    }
    public static KafkaConsumer<String,String> buildKafkaConsumer(String group){
        Properties props=new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG,group);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,false);
        props.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG,"read_committed");

        return new KafkaConsumer<String, String>(props);
    }
}
