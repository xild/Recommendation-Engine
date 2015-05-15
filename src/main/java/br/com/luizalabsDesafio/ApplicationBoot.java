package br.com.luizalabsDesafio;

import java.io.IOException;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;

/**
 * @author luis vieira
 * @since 13.05.2015
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableNeo4jRepositories
public class ApplicationBoot extends Neo4jConfiguration {

	public ApplicationBoot() {
		setBasePackage("br.com.luizalabsDesafio");
	}

	@Bean(destroyMethod = "shutdown")
	public GraphDatabaseService graphDatabaseService() {
		return new GraphDatabaseFactory().newEmbeddedDatabase("target/hello.db");
	}
    public static void main(String[] args) throws IOException {
        SpringApplication.run(ApplicationBoot.class, args);
    }



}
