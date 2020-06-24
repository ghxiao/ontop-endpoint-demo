# ontop-endpoint-demo

This demo shows how to communicate with a SPARQL endpoint pragmatically. It includes implementations in three most popular languages:

- [Java Example](java)
    - Maven dependency: `org.eclipse.rdf4j:rdf4j-client`
```xml
     <dependency>
         <groupId>org.eclipse.rdf4j</groupId>
         <artifactId>rdf4j-client</artifactId>
         <version>3.2.2</version>
         <type>pom</type>
     </dependency>
```

- [Python Example](py)
    - https://github.com/RDFLib/sparqlwrapper
    
- [JavaScript Example in HTML](js)
    - Use standard Fetch API following the SPARQL HTTP protocol
    - Be careful of CORS setup in the endpoint 
 
