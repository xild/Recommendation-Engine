package br.com.luizalabsDesafio.domain;

import org.neo4j.graphdb.RelationshipType;

public enum ProjectRelationshipTypes   implements RelationshipType
{
    VIEWED, BOUGHT, ADD_TO_CART;
}
