import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

// Open browser
WebUI.openBrowser('')

WebUI.maximizeWindow()

// Navigate to Login page
WebUI.navigateToUrl(GlobalVariable.baseUrl + '/login.php')

// Enter valid email
WebUI.setText(
	findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'),
	'customer1@gmail.com'
)

// Enter SQL Injection payload in Password field
WebUI.setText(
	findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'),
	"' OR '1'='1"
)

// Click Sign In
WebUI.click(
	findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in')
)

// Verify login failed
WebUI.verifyElementNotPresent(
	findTestObject('CUSTOMER/HomePage/btn_menu_profile'),
	5
)

// Verify user remains on Login page
WebUI.verifyMatch(
	WebUI.getUrl(),
	GlobalVariable.baseUrl + '/login.php',
	false
)

// Verify no SQL/database error displayed
WebUI.verifyTextNotPresent(
	'sql',
	false
)

WebUI.verifyTextNotPresent(
	'syntax',
	false
)

WebUI.verifyTextNotPresent(
	'mysqli',
	false
)

WebUI.verifyTextNotPresent(
	'warning',
	false
)

WebUI.verifyTextNotPresent(
	'error in your sql syntax',
	false
)

// Close browser
WebUI.closeBrowser()