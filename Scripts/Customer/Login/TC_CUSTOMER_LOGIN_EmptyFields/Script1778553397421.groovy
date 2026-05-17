import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

// Open browser
WebUI.openBrowser('')

WebUI.maximizeWindow()

// Navigate to Login page
WebUI.navigateToUrl(GlobalVariable.baseUrl + '/login.php')

// Leave Email empty
WebUI.setText(
	findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'),
	''
)

// Leave Password empty
WebUI.setText(
	findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'),
	''
)

// Click Sign In
WebUI.click(
	findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in')
)

// Verify validation message for Email
WebUI.verifyTextPresent(
	'Enter email',
	false
)

// Verify validation message for Password
WebUI.verifyTextPresent(
	'Enter password',
	false
)

// Verify still on Login page
WebUI.verifyMatch(
	WebUI.getUrl(),
	GlobalVariable.baseUrl + '/login.php',
	false
)

// Close browser
WebUI.closeBrowser()