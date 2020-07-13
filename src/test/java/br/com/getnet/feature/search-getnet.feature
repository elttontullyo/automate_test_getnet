@tag
Feature: Submit Form

  @tag1
  Scenario: search by superget
		Given search field is visible
    	When click in the search field
    	And input text is present 
    	And insert text
    	And click button search
    	And results screen appears
    	And seek result
    	And click on result
    	And modal is open
    	Then message expected displayed