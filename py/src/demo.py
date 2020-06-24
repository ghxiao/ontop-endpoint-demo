from SPARQLWrapper import SPARQLWrapper, JSON
sparql = SPARQLWrapper("http://testbed.inode.igd.fraunhofer.de:18000/sparql")
q = """
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
SELECT * WHERE {
  ?project a <http://unics.cloud/ontology#Project> ; 
       <http://unics.cloud/ontology#year> ?y;
	  <http://unics.cloud/ontology#name> ?name .
} 
LIMIT 1000
"""
sparql.setQuery(q)
sparql.setReturnFormat(JSON)
results = sparql.query().convert()
print(results)
