import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

// ==========================
// TC_CUSTOMER_PROFILE_XSSInjection_NameField_Blocked
// Verify XSS protection in Name field
// ==========================

// Open browser
WebUI.openBrowser('')

WebUI.maximizeWindow()

// Navigate to Login page
WebUI.navigateToUrl(GlobalVariable.baseUrl + '/login.php')

// Login with valid account
WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'), 'customer2@gmail.com')

WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'), 'Customer@123456')

WebUI.click(findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in'))

// Verify login successful
WebUI.verifyElementPresent(findTestObject('CUSTOMER/HomePage/btn_menu_profile'), 10)

// Open Profile page
WebUI.click(findTestObject('CUSTOMER/HomePage/btn_menu_profile'))

// Click Edit Profile
WebUI.click(findTestObject('CUSTOMER/Profile/btn_edit_name_email'))

// Input XSS payload into Name field
String xssPayload = '<script>alert(1)</script>'

WebUI.setText(findTestObject('CUSTOMER/Profile/txtbox_name'), xssPayload)

WebUI.setText(findTestObject('CUSTOMER/Profile/txtbox_email'), 'customer2@gmail.com')

// Save profile
WebUI.click(findTestObject('CUSTOMER/Profile/btn_save_name_email'))

// Refresh page to trigger stored XSS if vulnerable
WebUI.refresh()

WebUI.delay(2)

// Check whether alert appears
boolean alertPresent = WebUI.waitForAlert(5, FailureHandling.OPTIONAL)

// Expected: alert should NOT appear
// If alert appears => XSS vulnerability exists => Test FAIL
WebUI.verifyEqual(alertPresent, false)

// Handle alert if vulnerability exists
if (alertPresent) {
	
	String alertText = WebUI.getAlertText()
	
	WebUI.comment('XSS Alert detected: ' + alertText)
	
	WebUI.acceptAlert()
}

// Verify script tag is sanitized
String updatedName = WebUI.getAttribute(
	findTestObject('CUSTOMER/Profile/txtbox_name'),
	'value'
)

// Verify raw script is not stored
WebUI.verifyNotMatch(updatedName, '.*<script>.*', true)

// Close browser
WebUI.closeBrowser()