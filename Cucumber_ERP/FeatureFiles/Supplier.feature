Feature: validating ERP tests
Scenario Outline: Supplier creation Functionality Validation

When Open Browser
When Open Application url "http://webapp.qedgetech.com/"
When Wait For Username with "name" and "username"
When Enter Username with "name" and "username"
When Enter Password with "id" and "password"
When Click On Login with "name" and "btnsubmit"
When Wait For Supplier with "xpath" and"(//a[starts-with(text(),'Suppliers')])[2]"
When Click On Supplier with "xpath" and "(//a[starts-with(text(),'Suppliers')])[2]"
When Wait For AddButton with "xpath" and "(//span[@data-caption='Add'])[1]"
When Click On AddButton with "xpath" and "(//span[@data-caption='Add'])[1]"
When Wait For SupplierNumber with "name" and "x_Supplier_Number"
Then Capture Data with "name" and "x_Supplier_Number"
When Enter in "<SupplierName>" with "id" and "x_Supplier_Name" 
When Enter in "<address>" with "xpath" and "//*[@id='x_Address']" 
When Enter in "<city>" with "xpath" and "//*[@id='x_City']" 
When Enter in "<country>" with "xpath" and "//*[@id='x_Country']" 
When Enter in "<cperson>" with "xpath" and "//*[@id='x_Contact_Person']" 
When Enter in "<pnumber>" with "xpath" and "//*[@id='x_Phone_Number']" 
When Enter in "<mail>" with "xpath" and "//*[@id='x__Email']" 
When Enter in "<mnumber>" with "xpath" and "//*[@id='x_Mobile_Number']" 
When Enter in "<note>" with "xpath" and "//*[@id='x_Notes']" 
When Click On Add Button after adding notes with "id" and "btnAction"
When Wait For Ok Button with "xpath" and "//button[contains(text(),'OK!')]"
When Click On Ok Button with "xpath" and "//button[contains(text(),'OK!')]"
When Wait For Alert with "xpath" and "(//button[starts-with(text(),'OK')])[6]"
When Click On Alert with "xpath" and "(//button[starts-with(text(),'OK')])[6]"
Then user validate the supplier table
When user closes the browser

Examples:
|SupplierName|address|city|country|cperson|pnumber|mail|mnumber|note|
|Sree|Jublie Hills|Hyderabad|India|Anjali|1234567|test@gmail.com|9885098850|purchasing laptob|
|Raj|Madhapur|Hyderabad|India|Anand|1234567|test@gmail.com|9440024365|iam purchasing iphone|
|kiran|Hitech city|Hyderabad|India|radha|1234567|games@gmail.com|9848012345|iam purchasing playstation|


