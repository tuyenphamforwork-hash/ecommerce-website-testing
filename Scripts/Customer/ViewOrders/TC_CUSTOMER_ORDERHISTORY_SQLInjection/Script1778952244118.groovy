import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

import java.net.URLEncoder

WebUI.openBrowser('')

WebUI.maximizeWindow()


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

WebUI.verifyElementPresent(
	findTestObject('CUSTOMER/HomePage/btn_menu_profile'),
	10
)

String payload = "' OR '1'='1"

String encodedPayload =
	URLEncoder.encode(payload, 'UTF-8')

String maliciousUrl =
	GlobalVariable.baseUrl +
	'/profile.php?id=' +
	encodedPayload

println(maliciousUrl)

WebUI.navigateToUrl(maliciousUrl)

WebUI.delay(3)

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

WebUI.verifyElementPresent(
	findTestObject('CUSTOMER/HomePage/btn_menu_profile'),
	10
)

WebUI.verifyTextNotPresent(
	'admin@gmail.com',
	false
)

WebUI.verifyTextNotPresent(
	'customer1@gmail.com',
	false
)

WebUI.closeBrowser()