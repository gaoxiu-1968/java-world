package top.zhangmingke.esserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;

@SpringBootApplication(exclude = {ElasticsearchDataAutoConfiguration.class})
public class EsServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsServerApplication.class, args);
	}

}
