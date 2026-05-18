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

WebUI.click(
	findTestObject('CUSTOMER/product_detail/btn_AddToCart')
)

WebUI.delay(2)

WebUI.click(
	findTestObject('CUSTOMER/HomePage/icon_cart')
)

WebUI.delay(2)

String beforeQuantityText = WebUI.getAttribute(
	findTestObject('CUSTOMER/cart/input_quantity'),
	'value'
)

int beforeQuantity = Integer.parseInt(beforeQuantityText)

WebUI.click(
	findTestObject('CUSTOMER/cart/btn_increase_quantity')
)

WebUI.delay(2)

String updatedQuantityText = WebUI.getAttribute(
	findTestObject('CUSTOMER/cart/input_quantity'),
	'value'
)

int updatedQuantity = Integer.parseInt(updatedQuantityText)

WebUI.verifyEqual(
	updatedQuantity,
	beforeQuantity + 1
)

WebUI.refresh()

WebUI.delay(3)

WebUI.verifyElementPresent(
	findTestObject('CUSTOMER/cart/lbl_product_name'),
	10
)

String refreshedQuantityText = WebUI.getAttribute(
	findTestObject('CUSTOMER/cart/input_quantity'),
	'value'
)

int refreshedQuantity = Integer.parseInt(refreshedQuantityText)

WebUI.verifyEqual(
	refreshedQuantity,
	updatedQuantity
)

WebUI.closeBrowser()