import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl(GlobalVariable.baseUrl + '/login.php')

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

WebUI.verifyElementPresent(
	findTestObject('CUSTOMER/HomePage/btn_menu_profile'),
	10
)

WebUI.deleteAllCookies()

WebUI.refresh()

WebUI.navigateToUrl(
	GlobalVariable.baseUrl + '/profile.php'
)

WebUI.verifyMatch(
	WebUI.getUrl(),
	GlobalVariable.baseUrl + '/index.php?UnathorizedUser',
	false
)

WebUI.verifyElementNotPresent(
	findTestObject('CUSTOMER/HomePage/btn_menu_profile'),
	5
)

WebUI.closeBrowser()