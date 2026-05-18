import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl(GlobalVariable.baseUrl + '/login.php')

WebUI.setText(
	findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'),
	"' OR '1'='1"
)

WebUI.setText(
	findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'),
	'random123'
)

WebUI.click(
	findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in')
)

WebUI.verifyElementNotPresent(
	findTestObject('CUSTOMER/HomePage/btn_menu_profile'),
	5
)

WebUI.verifyMatch(
	WebUI.getUrl(),
	GlobalVariable.baseUrl + '/login.php',
	false
)

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

WebUI.closeBrowser()