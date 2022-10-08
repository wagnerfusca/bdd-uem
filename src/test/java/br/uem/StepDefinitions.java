package br.uem;

import io.cucumber.java.en.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IsItFriday {

    static String isItFriday(String today) {
    	if (today.equals("Friday")) {
    		return "Sextou";
    	}
        return "Nope";
    }
}

public class StepDefinitions {

    private String today;
    private String actualAnswer;

    @Given("today is {string}")
    public void testGiven(String parametro) {
        today = parametro;
    }

    @When("I ask whether it's Friday")
    public void testWhen() {
        actualAnswer = IsItFriday.isItFriday(today);
    }

    @Then("I should be told {string}")
    public void testThen(String expectedAnswer) {
        assertEquals(expectedAnswer, actualAnswer);
    }


}
