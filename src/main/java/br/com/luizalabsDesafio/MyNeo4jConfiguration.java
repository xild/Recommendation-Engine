package br.com.luizalabsDesafio;

import org.springframework.data.neo4j.config.Neo4jConfiguration;

//// tag::config[]
//@EnableTransactionManagement
//@Import(RepositoryRestMvcConfiguration.class)
//@EnableScheduling
//@EnableAutoConfiguration
//@ComponentScan(basePackages = {"br.com.luizalabsDesafio.services"})
//@Configuration
//@EnableNeo4jRepositories(basePackages = "br.com.luizalabsDesafio.services")
public class MyNeo4jConfiguration extends Neo4jConfiguration {
//    public MyNeo4jConfiguration() {
//        setBasePackage("br.com.luizalabsDesafio.domain");
//    }
//
//    public static final String URL = System.getenv("NEO4J_URL") != null ? System.getenv("NEO4J_URL") : "http://localhost:7474/db/data/";
//
//    @Bean
//    public GraphDatabaseService graphDatabaseService() {
//        return new SpringRestGraphDatabase(URL);
//    }
//
//    @Override
//    public TypeRepresentationStrategy<Relationship> relationshipTypeRepresentationStrategy() throws Exception {
//        return new NoopRelationshipTypeRepresentationStrategy();
//    }
}
// end::config[]
