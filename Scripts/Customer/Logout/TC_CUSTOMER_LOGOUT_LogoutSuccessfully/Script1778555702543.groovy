import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

// Open browser
WebUI.openBrowser('')

WebUI.maximizeWindow()

// Navigate to Login page
WebUI.navigateToUrl(GlobalVariable.baseUrl + '/login.php')

// Login with valid account
WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'), 'customer2@gmail.com')

WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'), 'Customer@123456')

WebUI.click(findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in'))

// Verify login successful
WebUI.verifyElementPresent(findTestObject('CUSTOMER/HomePage/btn_menu_profile'), 10)

// Open account/profile menu
WebUI.click(findTestObject('CUSTOMER/HomePage/btn_menu_profile'))

// Click Logout
WebUI.click(findTestObject('CUSTOMER/HomePage/logout'))

// Wait for redirect
WebUI.delay(2)

// Get current URL
String currentUrl = WebUI.getUrl()

// Verify redirected to Homepage
WebUI.verifyMatch(currentUrl, '.*index.php.*', true)

// Verify logout success parameter exists
WebUI.verifyMatch(currentUrl, '.*SuccessfullyLoggedout.*', true)

// Verify session destroyed
WebUI.verifyElementNotPresent(findTestObject('CUSTOMER/HomePage/btn_menu_profile'), 5)

// Try accessing protected page
WebUI.navigateToUrl(GlobalVariable.baseUrl + '/profile.php')

// Get unauthorized URL
String unauthorizedUrl = WebUI.getUrl()

// Verify unauthorized redirect
WebUI.verifyMatch(unauthorizedUrl, '.*UnathorizedUser.*', true)

// Close browser
WebUI.closeBrowser()