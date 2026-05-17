import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

// Open browser
WebUI.openBrowser('')

WebUI.maximizeWindow()

// Navigate to Login page
WebUI.navigateToUrl(GlobalVariable.baseUrl + '/login.php')

// Login
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
// Open product page
// ========================================

WebUI.click(
	findTestObject('CUSTOMER/HomePage/btn_menu_Home')
)

WebUI.click(
	findTestObject('CUSTOMER/product_detail/productname_jacket')
)

// Verify Add To Cart button displayed
WebUI.verifyElementPresent(
	findTestObject('CUSTOMER/product_detail/btn_AddToCart'),
	10
)

// ========================================
// Add product to cart
// ========================================

WebUI.click(
	findTestObject('CUSTOMER/product_detail/btn_AddToCart')
)

WebUI.delay(2)

// ========================================
// Open cart page
// ========================================

WebUI.click(
	findTestObject('CUSTOMER/HomePage/icon_cart')
)

WebUI.delay(2)

// ========================================
// Get quantity before update
// ========================================

String beforeQuantityText = WebUI.getAttribute(
	findTestObject('CUSTOMER/cart/input_quantity'),
	'value'
)

int beforeQuantity = Integer.parseInt(beforeQuantityText)

// ========================================
// Increase quantity
// ========================================

WebUI.click(
	findTestObject('CUSTOMER/cart/btn_increase_quantity')
)

WebUI.delay(2)

// ========================================
// Verify quantity updated
// ========================================

String updatedQuantityText = WebUI.getAttribute(
	findTestObject('CUSTOMER/cart/input_quantity'),
	'value'
)

int updatedQuantity = Integer.parseInt(updatedQuantityText)

// Verify quantity increased by 1
WebUI.verifyEqual(
	updatedQuantity,
	beforeQuantity + 1
)

// ========================================
// Refresh page
// ========================================

WebUI.refresh()

WebUI.delay(3)

// ========================================
// Verify cart data still exists
// ========================================

// Verify product still displayed
WebUI.verifyElementPresent(
	findTestObject('CUSTOMER/cart/lbl_product_name'),
	10
)

// Verify quantity still retained after refresh
String refreshedQuantityText = WebUI.getAttribute(
	findTestObject('CUSTOMER/cart/input_quantity'),
	'value'
)

int refreshedQuantity = Integer.parseInt(refreshedQuantityText)

// Verify quantity not lost after refresh
WebUI.verifyEqual(
	refreshedQuantity,
	updatedQuantity
)

// Close browser
WebUI.closeBrowser()