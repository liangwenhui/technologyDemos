package dml;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.KafkaFuture;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class KafkaDmlMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,"CentOS_lwh:9092");

        KafkaAdminClient adminClient = (KafkaAdminClient)KafkaAdminClient.create(properties);
        KafkaFuture<Set<String>> names = adminClient.listTopics().names();
        for(String topic : names.get()){
            System.out.println("topic:"+topic);
        }
        //创建Topics
//        List<NewTopic> newTopics = Arrays.asList(new NewTopic("topic02", 3, (short) 1));
//        CreateTopicsResult topics = adminClient.createTopics(newTopics);
//        topics.all().get();

        /**
         * //删除Topic
         *        // adminClient.deleteTopics(Arrays.asList("topic02"));
         *
         *         //查看Topic详情
         *         DescribeTopicsResult describeTopics =
         *                 adminClient.describeTopics(Arrays.asList("topic01"));
         *         Map<String, TopicDescription> tdm = describeTopics.all().get();
         *         for (Map.Entry<String, TopicDescription> entry : tdm.entrySet()) {
         *             System.out.println(entry.getKey()+"\t"+entry.getValue());
         *         }
         */

        adminClient.close();
    }
}
