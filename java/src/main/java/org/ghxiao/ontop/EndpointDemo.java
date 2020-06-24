package org.ghxiao.ontop;

import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.sparql.SPARQLRepository;

public class EndpointDemo {
    public static void main(String[] args) {
        String sparqlEndpoint = "http://testbed.inode.igd.fraunhofer.de:18000/sparql";
        Repository repo = new SPARQLRepository(sparqlEndpoint);

        try (RepositoryConnection conn = repo.getConnection()) {
            String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "SELECT * WHERE {\n" +
                    "  ?project a <http://unics.cloud/ontology#Project> ; \n" +
                    "       <http://unics.cloud/ontology#year> ?y;\n" +
                    "\t  <http://unics.cloud/ontology#name> ?name .\n" +
                    "} \n" +
                    "LIMIT 1000";
            TupleQuery tupleQuery = conn.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
            //TupleQueryResult result = tupleQuery.evaluate();
            try (TupleQueryResult result = tupleQuery.evaluate()) {
                while (result.hasNext()) {
                    BindingSet bindingSet = result.next();
                    System.out.println(bindingSet);
                }
            }
        }
    }
}
