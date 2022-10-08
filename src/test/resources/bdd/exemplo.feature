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
    

