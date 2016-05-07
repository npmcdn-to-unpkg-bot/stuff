package person.mdc;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
public class McWebStuffApplication {
	@Bean
	@Primary
	public DataSource dataSource() {
		return DataSourceBuilder.create().username("dbUser").password("dbPass").url("jdbc:derby:mcwebstuff_db;create=true")
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(McWebStuffApplication.class, args);
	}
}
