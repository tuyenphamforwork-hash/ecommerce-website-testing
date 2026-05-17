import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

// Open browser
WebUI.openBrowser('')

WebUI.maximizeWindow()

// Navigate to Login page
WebUI.navigateToUrl(GlobalVariable.baseUrl + '/login.php')

// Enter invalid email with special character
WebUI.setText(
	findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'),
	'customer1@!mail.com'
)

// Enter password
WebUI.setText(
	findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'),
	'Customer1'
)

// Click Sign In
WebUI.click(
	findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in')
)

// Get HTML5 validation message from Email field
String validationMessage = WebUI.getAttribute(
	findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'),
	'validationMessage'
)

// Verify validation message displayed
WebUI.verifyMatch(
	validationMessage,
	".*should not contain the symbol '!'.*",
	true
)

// Verify user still stays on Login page
WebUI.verifyMatch(
	WebUI.getUrl(),
	GlobalVariable.baseUrl + '/login.php',
	false
)

// Close browser
WebUI.closeBrowser()