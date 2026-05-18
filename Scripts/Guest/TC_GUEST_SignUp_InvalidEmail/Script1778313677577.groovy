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

WebUI.navigateToUrl(GlobalVariable.baseUrl)

WebUI.click(findTestObject('GUEST/Homepage/menu_login'))

WebUI.click(findTestObject('GUEST/Page_Login(USER)/btn_Signup'))

WebUI.verifyElementPresent(
	findTestObject('GUEST/Page_HCA E-Commerce/button_Register'),
	10
)

WebUI.setText(
	findTestObject('GUEST/Page_HCA E-Commerce/TextBox_FullName'),
	'Customer1'
)

WebUI.setText(
	findTestObject('GUEST/Page_HCA E-Commerce/TextBox_PhoneNumber'),
	'0965534564'
)

WebUI.setText(
	findTestObject('GUEST/Page_HCA E-Commerce/TextBox_Email'),
	'customer1'
)

WebUI.setText(
	findTestObject('GUEST/Page_HCA E-Commerce/TextBox_Address'),
	'1234 Main St'
)

WebUI.setText(
	findTestObject('GUEST/Page_HCA E-Commerce/TextBox_Password'),
	'Customer1'
)

WebUI.setText(
	findTestObject('GUEST/Page_HCA E-Commerce/TextBox_ConfirmPassword'),
	'Customer1'
)

WebUI.click(findTestObject('GUEST/Page_HCA E-Commerce/button_Register'))

String validationMessage = WebUI.getAttribute(
	findTestObject('GUEST/Page_HCA E-Commerce/TextBox_Email'),
	'validationMessage'
)

WebUI.comment('Validation Message: ' + validationMessage)

boolean isValidationDisplayed =
	validationMessage != null &&
	validationMessage.contains('@')

if (!isValidationDisplayed) {
	WebUI.takeScreenshot()

	WebUI.comment('BUG: Invalid email validation message not displayed')
}

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