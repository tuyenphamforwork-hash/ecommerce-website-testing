import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling

import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl(GlobalVariable.baseUrl)

WebUI.click(findTestObject('GUEST/Homepage/menu_login'))

WebUI.click(findTestObject('GUEST/Page_Login(USER)/btn_Signup'))

WebUI.verifyElementPresent(
	findTestObject('GUEST/Page_HCA E-Commerce/button_Register'),
	10
)

WebUI.click(findTestObject('GUEST/Page_HCA E-Commerce/button_Register'))

String validationMessage = WebUI.getAttribute(
	findTestObject('GUEST/Page_HCA E-Commerce/TextBox_FullName'),
	'validationMessage'
)

WebUI.comment('Validation Message: ' + validationMessage)

boolean isValidationDisplayed =
	validationMessage != null &&
	validationMessage.trim().length() > 0

String currentUrl = WebUI.getUrl()

boolean isStillOnSignupPage =
	currentUrl.contains('register') ||
	currentUrl.contains('signup')

WebUI.verifyEqual(
	isValidationDisplayed,
	true,
	FailureHandling.CONTINUE_ON_FAILURE
)

WebUI.verifyEqual(
	isStillOnSignupPage,
	true,
	FailureHandling.CONTINUE_ON_FAILURE
)

WebUI.closeBrowser()