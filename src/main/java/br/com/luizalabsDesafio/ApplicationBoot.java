package br.com.luizalabsDesafio;

import java.io.File;
import java.io.IOException;
import java.util.Map;

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
		/**local test
		 * need to set env vars
		 * 
		 * String login = System.getenv("NEO4J_LOGIN");
		String pwd = System.getenv("NEO4J_PASSWORD");
		String url = System.getenv("NEO4J_REST_URL");

		return new SpringRestGraphDatabase(url, login, pwd ) ;*/
		
		/* HEROKU DEPLOY 
		 * */
		String neo4j = System.getenv("GRAPHENEDB_URL");
		String url = neo4j.split("@")[1];
		String[] split = neo4j.split("@")[0].substring(7).split(":");
		String user = split[0];
		String pwd = split[1];
		return new SpringRestGraphDatabase("http://"+url+"/db/data", user, pwd ) ;
	}
    public static void main(String[] args) throws IOException {
//    	  FileUtils.deleteRecursively(new File("target/luiza.db"));
    	 String webPort = System.getenv("PORT");
    	    if (webPort == null || webPort.isEmpty()) {
    	        webPort = "8080";
    	    }
    	    System.setProperty("server.port", webPort);
        SpringApplication.run(ApplicationBoot.class, args);
    }



}
