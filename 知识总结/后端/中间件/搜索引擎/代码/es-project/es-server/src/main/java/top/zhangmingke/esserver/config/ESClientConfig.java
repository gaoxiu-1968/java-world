package top.zhangmingke.esserver.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ESClientConfig {
    private final String username = "elastic";
    private final String password = "zjhc123!@#";

    @Bean
    public RestHighLevelClient restHighLevelClient(){
        //设置账号密码
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(username, password));
        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost("10.145.240.1", 9200, "http"));
        restClientBuilder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
            @Override
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
                httpAsyncClientBuilder.disableAuthCaching();
                httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                return httpAsyncClientBuilder;
            }
        });
        RestHighLevelClient client = new RestHighLevelClient(restClientBuilder);
        return client;
    }
}
