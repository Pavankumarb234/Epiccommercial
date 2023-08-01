Feature:admin login successfull

  @Smoke
  Scenario Outline:login successfull
    When user on login home page
    And user enters "<text1>" & "<text2>"&"<lastname>"
    And user clicks on loginbutton
    Then user on profile page
    Examples:
      | text1 | text2     |lastname|
      | ram  | abcd |user36|


#  @Smoke
#  Scenario Outline: user login successful
#    When user on login home page
#    And user enters "<username>" & "<password>"
#    Then user profile displays
#
#    Examples:
#      |username|password|
#      |cc|Bourntec@123|