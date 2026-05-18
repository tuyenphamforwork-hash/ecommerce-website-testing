import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl(GlobalVariable.baseUrl + '/login.php')

WebUI.setText(
	findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'),
	' customer1@gmail.com '
)

WebUI.setText(
	findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'),
	'Customer1'
)

WebUI.click(
	findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in')
)

WebUI.verifyElementPresent(
	findTestObject('CUSTOMER/HomePage/btn_menu_profile'),
	10
)

WebUI.click(
	findTestObject('CUSTOMER/HomePage/btn_menu_profile')
)

WebUI.verifyTextPresent(
	'HELLO, CUSTOMER1',
	false
)

WebUI.verifyNotMatch(
	WebUI.getUrl(),
	GlobalVariable.baseUrl + '/login.php',
	false
)

WebUI.closeBrowser()