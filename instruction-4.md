claude "Create sample page object classes for Android automation using POM:

1. LoginPage.java - Login screen page object with:

   - Locators using @AndroidFindBy for username, password, login button
   - Methods: enterUsername(), enterPassword(), clickLogin(), isLoginButtonEnabled()
   - Return HomePage object after successful login

2. HomePage.java - Home screen page object with:

   - Navigation elements locators
   - Methods for common home screen actions
   - Method to verify home screen is displayed

3. All page objects should:
   - Extend BasePage class
   - Use proper Page Factory pattern
   - Include meaningful method names
   - Have proper return types (page objects or void)

Use realistic Android locators (id, xpath, accessibility-id) and include proper Java documentation."
