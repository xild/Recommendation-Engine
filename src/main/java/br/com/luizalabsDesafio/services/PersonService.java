package br.com.luizalabsDesafio.services;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.luizalabsDesafio.domain.Person;

@Service
@Transactional
public class PersonService implements InitializingBean {


//    @Autowired Neo4jTemplate template;

   

    public void save(Person person){
//    	template.save(person);
    }



	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
//	    template.query("MATCH (n:Person) SET n:`_Person`",null).finish();
//        template.query("MATCH (Product) SET n:`Product`",null).finish();
	}
}
