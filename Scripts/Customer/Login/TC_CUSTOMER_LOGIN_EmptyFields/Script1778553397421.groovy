import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl(GlobalVariable.baseUrl + '/login.php')

WebUI.setText(
	findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'),
	''
)

WebUI.setText(
	findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'),
	''
)

WebUI.click(
	findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in')
)

WebUI.verifyTextPresent(
	'Enter email',
	false
)

WebUI.verifyTextPresent(
	'Enter password',
	false
)

WebUI.verifyMatch(
	WebUI.getUrl(),
	GlobalVariable.baseUrl + '/login.php',
	false
)

WebUI.closeBrowser()