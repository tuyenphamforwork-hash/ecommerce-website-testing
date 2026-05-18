import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl(GlobalVariable.baseUrl + '/login.php')

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

WebUI.click(
	findTestObject('CUSTOMER/HomePage/btn_menu_Home')
)

WebUI.click(
	findTestObject('CUSTOMER/product_detail/productname_jacket')
)

WebUI.verifyElementPresent(
	findTestObject('CUSTOMER/product_detail/btn_AddToCart'),
	10
)

WebUI.clearText(
	findTestObject('CUSTOMER/product_detail/txtbox_quantity')
)

WebUI.setText(
	findTestObject('CUSTOMER/product_detail/txtbox_quantity'),
	'-1'
)

WebUI.click(
	findTestObject('CUSTOMER/product_detail/btn_AddToCart')
)

WebUI.delay(2)

String validationMessage = WebUI.getAttribute(
	findTestObject('CUSTOMER/product_detail/txtbox_quantity'),
	'validationMessage'
)

WebUI.verifyMatch(
	validationMessage,
	'.*greater than or equal to 1.*',
	true
)

WebUI.closeBrowser()