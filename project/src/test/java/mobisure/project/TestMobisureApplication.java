package mobisure.project;

import org.springframework.boot.SpringApplication;

public class TestMobisureApplication {

	public static void main(String[] args) {
		SpringApplication.from(MobisureApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
