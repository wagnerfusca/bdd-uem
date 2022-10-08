# BDD - Behavior Driven Development - Secomp - UEM - 06 e 07 de outubro de 2022

Passos para criar um projeto Java usando Maven e Netbeans

## - Criar o projeto Java Maven
1. Acesse o menu *File - New Project - Java With Maven - Java Aplication*
2. Adicione o nome Bdd 
3. No campo groupid adicione *br.uem* 
4. No campo package adicione *br.uem.bdd*
5. Após o projeto criado, abra o arquivo **pom.xml**
6. Adicione as dependências do Cucumber e JUnit5 no projeto. Para isso adicione o código abaixo logo abaixo da tag </properties>

~~~java
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>io.cucumber</groupId>
                <artifactId>cucumber-bom</artifactId>
                <version>7.5.0</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>org.junit</groupId>
                <artifactId>junit-bom</artifactId>
                <version>5.9.0</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <dependencies>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-junit-platform-engine</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.junit.platform</groupId>
            <artifactId>junit-platform-suite</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.0.0-M5</version>
                <configuration>
                    <properties>
                        <configurationParameters>
                            cucumber.junit-platform.naming-strategy=long
                        </configurationParameters>
                    </properties>
                </configuration>
            </plugin>
             
        </plugins>
    </build>
~~~

## - Crie uma pasta src/test 
1. (criar um arquivo do tipo JUnitest no Netbeans para aparecer o **Test Package* que é um source folder de testes
2. Para isso, clique com o botão direito no nome do projeto -- New -- JunitTest
3. Na sequência, crie um package chamado java e br.uem.bdd

## - Crie o arquivo de execucao do Cucumber
1. Crie um arquivo Java chamado RunCucumberTest.java
2. Adicione o código abaixo

~~~java
package br.uem.bdd;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("bdd")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
public class RunCucumberTest {
}
~~~

## - crie o arquivo vazio (empty file)
1. crie uma pasta chamada resources **(provavelmente teremos que ir na aba files e não na aba project)!!!!**
2. criar a pasta resources em src/test/
3. criar a pasta bdd em src/test/resources
4. criar um EMPTY FILE chamado *exemplo.feature*
5. Adicione o código abaixo:
~~~
Feature: Is it Friday yet?
  Everybody wants to know when it's Friday

  Scenario: Today is Friday?
    Given today is "<day>"
    When I ask whether it's Friday 
    Then I should be told "<answer>"
    
    Examples:
    |day    |answer |
    |Sunday |Nope   |
    |Monday |Nope   |
    |Friday |Sextou |
    
~~~

## - faça a integração do arquivo Gherkins com o Java
1. **VOLTE** para a aba project 
2. criar o arquivo Java chamado StepDefinitions.java
3. Adicione o código abaixo:

~~~java
package br.uem.bdd;

import io.cucumber.java.en.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IsItFriday {

    static String isItFriday(String today) {
        return "Nope";
    }
}

public class StepDefinitions {

    private String today;
    private String actualAnswer;

    @Given("today is Sunday")
    public void today_is_Sunday() {
        today = "Sunday";
    }

    @When("I ask whether it's Friday yet")
    public void i_ask_whether_it_s_Friday_yet() {
        actualAnswer = IsItFriday.isItFriday(today);
    }

    @Then("I should be told {string}")
    public void i_should_be_told(String expectedAnswer) {
        assertEquals(expectedAnswer, actualAnswer);
    }

}
~~~
## Executando o BDD
1. para executar o BDD use o maven e digite **mvn test** ou vá no arquivo *RunCucumberTest.java* e execute o arquivo **(Run --> Run Test)**


## - Criando um relatório do que aconteceu no BDD
1. Para adicionar um relatório, aba o arquivo RunCucumberTest.java
2. Substitua a linha
~~~java
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
~~~

**por** 

~~~java
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber.html")
~~~
3. Execute arquivo RunCucumberTest e depois procure a pasta target para encontrar o arquivo *cucumber.html*. Abra esse arquivo em um navegador.
