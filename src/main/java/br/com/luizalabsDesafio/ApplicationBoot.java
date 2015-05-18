package br.com.luizalabsDesafio;

import java.io.File;
import java.io.IOException;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.kernel.impl.util.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.rest.SpringRestGraphDatabase;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author luis vieira
 * @since 13.05.2015
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableNeo4jRepositories
@EnableTransactionManagement
public class ApplicationBoot extends Neo4jConfiguration {

	public ApplicationBoot() {
		setBasePackage("br.com.luizalabsDesafio");
	}

	@Bean(destroyMethod = "shutdown")
	public GraphDatabaseService graphDatabaseService() {
				 
				 
		return new SpringRestGraphDatabase("http://localhost:7474/db/data", "neo4j", "lilica89" ) ;
//		return new GraphDatabaseFactory().newEmbeddedDatabase("target/luiza.db");
	}
    public static void main(String[] args) throws IOException {
    	  FileUtils.deleteRecursively(new File("target/luiza.db"));
        SpringApplication.run(ApplicationBoot.class, args);
    }



}
