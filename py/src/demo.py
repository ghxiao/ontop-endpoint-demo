from SPARQLWrapper import SPARQLWrapper, JSON
sparql = SPARQLWrapper("https://sparql.opendatahub.bz.it/sparql")
q = """
PREFIX schema: <http://schema.org/>
PREFIX geo: <http://www.opengis.net/ont/geosparql#>
PREFIX : <http://noi.example.org/ontology/odh#>

SELECT ?pos ?posLabel
WHERE {
  ?p a :Pizzeria ;
     geo:asWKT ?pos ;
     schema:name ?posLabel ;
     schema:geo ?geo .
  FILTER (lang(?posLabel) = 'it')
  }
  LIMIT 100
"""
sparql.setQuery(q)
sparql.setReturnFormat(JSON)
results = sparql.query().convert()
print(results)
