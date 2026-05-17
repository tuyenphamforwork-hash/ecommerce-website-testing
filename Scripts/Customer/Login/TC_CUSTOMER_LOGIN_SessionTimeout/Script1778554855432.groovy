import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

// Open browser
WebUI.openBrowser('')

WebUI.maximizeWindow()

// Navigate to Login page
WebUI.navigateToUrl(GlobalVariable.baseUrl + '/login.php')

// Login with valid account
WebUI.setText(
	findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'),
	'customer1@gmail.com'
)

WebUI.setText(
	findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'),
	'Customer1'
)

WebUI.click(
	findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in')
)

// Verify login successful
WebUI.verifyElementPresent(
	findTestObject('CUSTOMER/HomePage/btn_menu_profile'),
	10
)

// Simulate session timeout
WebUI.deleteAllCookies()

// Refresh page
WebUI.refresh()

// Access protected page
WebUI.navigateToUrl(
	GlobalVariable.baseUrl + '/profile.php'
)

// Verify redirected due to unauthorized access
WebUI.verifyMatch(
	WebUI.getUrl(),
	GlobalVariable.baseUrl + '/index.php?UnathorizedUser',
	false
)

// Verify profile menu no longer displayed
WebUI.verifyElementNotPresent(
	findTestObject('CUSTOMER/HomePage/btn_menu_profile'),
	5
)

// Close browser
WebUI.closeBrowser()