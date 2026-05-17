import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

// =====================================================
// TC_CUSTOMER_CART_AddProduct_QuantityZero
// Verify system validation when quantity = 0
// Expected:
// "Value must be greater than or equal to 1."
// =====================================================

// Open browser
WebUI.openBrowser('')

WebUI.maximizeWindow()

// Navigate to Login page
WebUI.navigateToUrl(GlobalVariable.baseUrl + '/login.php')

// Login with valid account
WebUI.setText(
	findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'),
	'customer2@gmail.com'
)

WebUI.setText(
	findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'),
	'Customer@123456'
)

// Click Sign In
WebUI.click(
	findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in')
)

// Verify login successful
WebUI.verifyElementPresent(
	findTestObject('CUSTOMER/HomePage/btn_menu_profile'),
	10
)

// Open Home page
WebUI.click(
	findTestObject('CUSTOMER/HomePage/btn_menu_Home')
)

// Open Product Detail page
WebUI.click(
	findTestObject('CUSTOMER/product_detail/productname_jacket')
)

// Verify Product page displayed
WebUI.verifyElementPresent(
	findTestObject('CUSTOMER/product_detail/btn_AddToCart'),
	10
)

// ========================================
// Input quantity = 0
// ========================================

WebUI.clearText(
	findTestObject('CUSTOMER/product_detail/txtbox_quantity')
)

WebUI.setText(
	findTestObject('CUSTOMER/product_detail/txtbox_quantity'),
	'0'
)

// Click Add To Cart
WebUI.click(
	findTestObject('CUSTOMER/product_detail/btn_AddToCart')
)

WebUI.delay(2)

// ========================================
// Verify validation message displayed
// ========================================

String validationMessage = WebUI.getAttribute(
	findTestObject('CUSTOMER/product_detail/txtbox_quantity'),
	'validationMessage'
)

// Verify browser validation message
WebUI.verifyMatch(
	validationMessage,
	'.*greater than or equal to 1.*',
	true
)

// Close browser
WebUI.closeBrowser()