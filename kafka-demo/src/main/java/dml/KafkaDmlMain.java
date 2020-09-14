package dml;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.DeleteTopicsResult;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.common.KafkaFuture;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * topic api
 */
public class KafkaDmlMain {

    public static KafkaAdminClient getConnectClient(){
        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,"CentOS_lwh:9092");

        KafkaAdminClient adminClient = (KafkaAdminClient)KafkaAdminClient.create(properties);
        return adminClient;
    }

    @Test
    public void list() throws ExecutionException, InterruptedException {
        KafkaAdminClient adminClient = getConnectClient();
        KafkaFuture<Set<String>> names = adminClient.listTopics().names();
        for(String topic : names.get()){
            System.out.println("topic:"+topic);
        }
        adminClient.close();
    }
    @Test
    public void create() throws ExecutionException, InterruptedException {
        KafkaAdminClient adminClient = getConnectClient();
        //创建Topics
        List<NewTopic> newTopics = Arrays.asList(new NewTopic("topic02", 3, (short) 1));
        CreateTopicsResult topics = adminClient.createTopics(newTopics);
        topics.all().get();
        adminClient.close();
    }

    @Test
    public void delete(){
        KafkaAdminClient adminClient = getConnectClient();
        DeleteTopicsResult topic02 = adminClient.deleteTopics(Arrays.asList("topic02"));
        System.out.println(topic02);
        adminClient.close();
    }
    @Test
    public void detile() throws ExecutionException, InterruptedException {
        KafkaAdminClient adminClient = getConnectClient();
        DescribeTopicsResult topic_lwh = adminClient.describeTopics(Arrays.asList("topic_lwh"));
        Map<String, TopicDescription> stringTopicDescriptionMap = topic_lwh.all().get();
        stringTopicDescriptionMap.forEach((s, topicDescription) -> {
            System.out.println(s+":"+topicDescription);
        });
        adminClient.close();
    }






}
