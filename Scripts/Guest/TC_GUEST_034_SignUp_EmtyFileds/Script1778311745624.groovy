import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling

import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')

WebUI.maximizeWindow()

// STEP 1: Open homepage
WebUI.navigateToUrl(GlobalVariable.baseUrl)

// STEP 2: Open Login page
WebUI.click(findTestObject('Guest/Homepage/menu_login'))

// STEP 3: Open Signup page
WebUI.click(findTestObject('Guest/Page_Login(USER)/btn_Signup'))

// STEP 4: Verify Register button displayed
WebUI.verifyElementPresent(
	findTestObject('Guest/Page_HCA E-Commerce/button_Register'),
	10
)

// STEP 5: Click Register without entering data
WebUI.click(findTestObject('Guest/Page_HCA E-Commerce/button_Register'))

// STEP 6: Get HTML5 validation message
String validationMessage = WebUI.getAttribute(
	findTestObject('Guest/Page_HCA E-Commerce/TextBox_FullName'),
	'validationMessage'
)

WebUI.comment('Validation Message: ' + validationMessage)

// STEP 7: Verify validation exists
boolean isValidationDisplayed =
	validationMessage != null &&
	validationMessage.trim().length() > 0

// STEP 8: Verify still on signup page
String currentUrl = WebUI.getUrl()

boolean isStillOnSignupPage =
	currentUrl.contains('register') ||
	currentUrl.contains('signup')

// STEP 9: Final verify
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