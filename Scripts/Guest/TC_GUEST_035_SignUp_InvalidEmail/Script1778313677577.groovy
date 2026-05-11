import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable as GlobalVariable

import org.openqa.selenium.Keys as Keys

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

// STEP 5: Input signup information
WebUI.setText(
	findTestObject('Guest/Page_HCA E-Commerce/TextBox_FullName'),
	'Customer1'
)

WebUI.setText(
	findTestObject('Guest/Page_HCA E-Commerce/TextBox_PhoneNumber'),
	'0965534564'
)

WebUI.setText(
	findTestObject('Guest/Page_HCA E-Commerce/TextBox_Email'),
	'customer1'
)

WebUI.setText(
	findTestObject('Guest/Page_HCA E-Commerce/TextBox_Address'),
	'1234 Main St'
)

WebUI.setText(
	findTestObject('Guest/Page_HCA E-Commerce/TextBox_Password'),
	'Customer1'
)

WebUI.setText(
	findTestObject('Guest/Page_HCA E-Commerce/TextBox_ConfirmPassword'),
	'Customer1'
)

// STEP 6: Click Register
WebUI.click(findTestObject('Guest/Page_HCA E-Commerce/button_Register'))

// STEP 7: Get HTML5 validation message
String validationMessage = WebUI.getAttribute(
	findTestObject('Guest/Page_HCA E-Commerce/TextBox_Email'),
	'validationMessage'
)

WebUI.comment('Validation Message: ' + validationMessage)

// STEP 8: Verify validation message displayed
boolean isValidationDisplayed =
	validationMessage != null &&
	validationMessage.contains('@')

if (!isValidationDisplayed) {
	WebUI.takeScreenshot()

	WebUI.comment('❌ BUG: Invalid email validation message not displayed')
}

// STEP 9: Verify user still stays on signup page
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

// STEP 10: Close browser
WebUI.closeBrowser()