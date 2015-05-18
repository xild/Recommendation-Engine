package br.com.luizalabsDesafio.domain;

import org.neo4j.graphdb.RelationshipType;

public enum MyRelationshipType   implements RelationshipType
{
    VIEWED, BOUGHT, ADD_TO_CART;
}
