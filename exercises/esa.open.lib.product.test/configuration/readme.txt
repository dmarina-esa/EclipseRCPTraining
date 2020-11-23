CAUTION:
Please take note of Eclipse Bug 190425 - The "config.ini" path in the .product file can be incorrect if choosen with the browse functionality
https://bugs.eclipse.org/bugs/show_bug.cgi?id=190425 

Use a plugin-ID relative path not the Project-Name relative path:

E.g.: 

CORRECT: /esa.open.lib.product.test/configuration/config.ini
WRONG:   /eud.product.test/configuration/config.ini


Also note the following:
Similar problem with Maven Tycho.
<!-- Workaround: Use an existing config.ini file (caused by the problem that tycho will always generate a default one) -->
<plugin>
  <artifactId>maven-resources-plugin</artifactId>
  <executions>
    <execution>
      <phase>package</phase>
      <goals>
        <goal>copy-resources</goal>
      </goals>
      <configuration>
        <resources>
          <resource>
            <directory>${project.build.directory}/../</directory>
            <filtering>false</filtering>
            <includes>
              <include>config.ini</include>
            </includes>
          </resource>
        </resources>
        <outputDirectory>${product.outputDirectory}/configuration</outputDirectory>
        <overwrite>true</overwrite>
      </configuration>
    </execution>
  </executions>
</plugin>