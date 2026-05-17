import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

// Open browser
WebUI.openBrowser('')

WebUI.maximizeWindow()

// Navigate to Login page
WebUI.navigateToUrl(GlobalVariable.baseUrl + '/login.php')

// Enter email with leading/trailing spaces
WebUI.setText(
	findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'),
	' customer1@gmail.com '
)

// Enter valid password
WebUI.setText(
	findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'),
	'Customer1'
)

// Click Sign In
WebUI.click(
	findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in')
)

// Verify login successful
WebUI.verifyElementPresent(
	findTestObject('CUSTOMER/HomePage/btn_menu_profile'),
	10
)

// Open profile menu
WebUI.click(
	findTestObject('CUSTOMER/HomePage/btn_menu_profile')
)

// Verify correct username displayed
WebUI.verifyTextPresent(
	'HELLO, CUSTOMER1',
	false
)

// Verify redirected away from login page
WebUI.verifyNotMatch(
	WebUI.getUrl(),
	GlobalVariable.baseUrl + '/login.php',
	false
)

// Close browser
WebUI.closeBrowser()