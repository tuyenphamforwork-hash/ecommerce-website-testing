import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

import java.net.URLEncoder

WebUI.openBrowser('')

WebUI.maximizeWindow()

// ========================================
// Login
// ========================================

WebUI.navigateToUrl(
	GlobalVariable.baseUrl + '/login.php'
)

WebUI.setText(
	findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'),
	'customer2@gmail.com'
)

WebUI.setText(
	findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'),
	'Customer@123456'
)

WebUI.click(
	findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in')
)

// Verify login successful
WebUI.verifyElementPresent(
	findTestObject('CUSTOMER/HomePage/btn_menu_profile'),
	10
)

// ========================================
// SQL Injection Payload
// ========================================

String payload = "' OR '1'='1"

String encodedPayload =
	URLEncoder.encode(payload, 'UTF-8')

// ========================================
// Inject Into Existing Page
// ========================================

// IMPORTANT:
// Replace profile.php with your REAL page

String maliciousUrl =
	GlobalVariable.baseUrl +
	'/profile.php?id=' +
	encodedPayload

println(maliciousUrl)

WebUI.navigateToUrl(maliciousUrl)

WebUI.delay(3)

// ========================================
// Verify No SQL Error
// ========================================

WebUI.verifyTextNotPresent(
	'SQL syntax',
	false
)

WebUI.verifyTextNotPresent(
	'mysqli',
	false
)

WebUI.verifyTextNotPresent(
	'mysql',
	false
)

WebUI.verifyTextNotPresent(
	'Fatal error',
	false
)

WebUI.verifyTextNotPresent(
	'Warning',
	false
)

// ========================================
// Verify Website Still Stable
// ========================================

WebUI.verifyElementPresent(
	findTestObject('CUSTOMER/HomePage/btn_menu_profile'),
	10
)

// ========================================
// Verify Unauthorized Data NOT Displayed
// ========================================

WebUI.verifyTextNotPresent(
	'admin@gmail.com',
	false
)

WebUI.verifyTextNotPresent(
	'customer1@gmail.com',
	false
)

// ========================================
// Close Browser
// ========================================

WebUI.closeBrowser()