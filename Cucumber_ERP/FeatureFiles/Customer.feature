Feature: validating ERP tests
Scenario Outline: Customer creation Functionality Validation

When Open Browser
When Open Application url "http://webapp.qedgetech.com/"
When Wait For Username with "name" and "username"
When Enter Username with "name" and "username"
When Enter Password with "id" and "password"
When Click On Login with "name" and "btnsubmit"
When wait for customer with "xpath" and "//li[@id='mi_a_customers']//a[@href='a_customerslist.php'][normalize-space()='Customers']"
When Click on customer with "xpath" and "//li[@id='mi_a_customers']//a[@href='a_customerslist.php'][normalize-space()='Customers']"
