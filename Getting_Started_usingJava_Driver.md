**USING DATASTAX JAVA DRIVER**

**_Doc_**: https://docs.datastax.com/en/developer/java-driver-dse/1.5/

_**Javadoc**_: https://docs.datastax.com/en/drivers/java-dse/1.5/

**_Driver_**: The driver is available from Maven central:

`<dependency>
  <groupId>com.datastax.dse</groupId>
  <artifactId>dse-java-driver-core</artifactId>
  <version>1.5.1</version>
</dependency>`


**Issues:**

- Cannot use "describe...." Cql statements. Reason: These are cqlsh extensions and are not part of CQL3 specification.
- Solution: There is a equivalent "select..." (see below)

Eg:

1. Instead of "`describe keyspaces`" you can do:
"`select keyspace_name from system_schema.keyspaces`;"

2. Instead of "`describe <specific keyspace>`" you can do:
"`select * from system_schema.columns where keyspace_name = 'system_auth'`;"

===

**Using Maven with IntelliJ to build a new Project:**

Steps using IntelliJ:

1. Select "Create New Project"
2. Select (on left panel) "Maven". Make sure Project SDK is Have 1.8. Do not select "Create from archetype". Select "Next".
3. Set "GroupId" = "com.datastax.dse.demo" and set "ArtifactId" = "java-dse-demo". Leave "Version" as "1.0-SNAPSHOT". Select "Next".
4. Set "Project name" = "java-dse-demo" and "Project location" = "~/java-dse-demo". Select "Finish".

Observe the pom.xml file created by IntelliJ

5. Add the following into the pom file.

`    <dependency>  
        <groupId>com.datastax.dse</groupId>  
        <artifactId>dse-java-driver-core</artifactId>  
        <version>1.5.1</version>  
    </dependency> ` 

Make sure the above is inside `<dependencies></dependencies>`

Make sure to check:

`Files/"Other Settings"/"Default Settings"/
"Build, Execution, Deployment"/Importing/"Import Maven projects automatically" [Is Checked]`

6. Create a Java source file:

In left panel, go to src/main/java and add a Java file.

Eg: SampleMain.java

`import com.datastax.driver.dse.DseCluster;`

`import com.datastax.driver.dse.DseSession;`

`import com.datastax.driver.core.Row;`

`import com.datastax.driver.core.ResultSet;`

`import java.util.Iterator;`

`public class SampleMain {`

  `public static void main(String[] args)`
  
  `{// code}`
  
`}`

7. Build SampleMain.java:

On Right panel (where it says "Maven projects"), open "Lifecycle".

Click on "install". (This builds and installs the source in target dir.)

8. Running SampleMain:

In left panel, go to src/main/java/SampleMain.
Right click and select "Run SampleMain".


