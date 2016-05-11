package person.mdc.web;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import person.mdc.web.model.Account;
import person.mdc.web.model.AccountRepository;

@SpringBootApplication
public class McWebStuffApplication {
	@Bean
	@Primary
	public DataSource dataSource() {
		return DataSourceBuilder.create().username("dbUser").password("dbPass")
				.url("jdbc:derby:mcwebstuff_db;create=true").build();
	}

	@Bean
	CommandLineRunner init(final AccountRepository accountRepository) {
		return new CommandLineRunner() {
			@Override
			public void run(String... arg0) throws Exception {
				Account acct = accountRepository.findByUsername("misha");
				if(acct == null) {
					accountRepository.save(new Account("misha", "masha"));
				}

			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(McWebStuffApplication.class, args);
	}
}
