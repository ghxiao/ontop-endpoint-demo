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
        String sparqlEndpoint = "https://sparql.opendatahub.bz.it/sparql";
        Repository repo = new SPARQLRepository(sparqlEndpoint);

        try (RepositoryConnection conn = repo.getConnection()) {
            String queryString = "PREFIX schema: <http://schema.org/>\n" +
                    "PREFIX geo: <http://www.opengis.net/ont/geosparql#>\n" +
                    "\n" +
                    "SELECT ?h ?pos ?posLabel ?posColor\n" +
                    "WHERE {\n" +
                    "  ?h a schema:LodgingBusiness ;\n" +
                    "     geo:asWKT ?pos ;\n" +
                    "     schema:name ?posLabel ;\n" +
                    "     schema:address ?a .\n" +
                    "  # ?a schema:postalCode \"39100\" . # Uncomment for Bolzano only\n" +
                    "FILTER (lang(?posLabel) = 'de')}" +
                    "LIMIT 100 ";
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
