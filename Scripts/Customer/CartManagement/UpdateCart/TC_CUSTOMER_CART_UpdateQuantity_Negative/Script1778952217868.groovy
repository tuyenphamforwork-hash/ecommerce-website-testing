import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

// Open browser
WebUI.openBrowser('')

WebUI.maximizeWindow()

// Navigate to Login page
WebUI.navigateToUrl(GlobalVariable.baseUrl + '/login.php')

// ========================================
// Login with valid account
// ========================================
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
// Open Home page
// ========================================
WebUI.click(
	findTestObject('CUSTOMER/HomePage/btn_menu_Home')
)

// ========================================
// Open Product Detail page
// ========================================
WebUI.click(
	findTestObject('CUSTOMER/product_detail/productname_jacket')
)

// Verify Product page displayed
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
// Open Cart page
// ========================================
WebUI.click(
	findTestObject('CUSTOMER/HomePage/icon_cart')
)

WebUI.delay(2)

// ========================================
// Verify quantity input displayed
// ========================================
WebUI.verifyElementPresent(
	findTestObject('CUSTOMER/cart/input_quantity'),
	10
)

// ========================================
// Get current quantity
// ========================================
String quantityText = WebUI.getAttribute(
	findTestObject('CUSTOMER/cart/input_quantity'),
	'value'
)

int currentQuantity = Integer.parseInt(quantityText)

// ========================================
// Decrease quantity until system rejects
// ========================================
for (int i = 0; i <= currentQuantity; i++) {

	WebUI.click(
		findTestObject('CUSTOMER/cart/btn_decrease_quantity')
	)

	WebUI.delay(1)

	// Check if alert appears
	boolean alertPresent = WebUI.waitForAlert(2)

	if (alertPresent) {

		String alertText = WebUI.getAlertText()

		// Verify alert message
		WebUI.verifyEqual(
			alertText,
			'Error updating quantity'
		)

		// Accept alert
		WebUI.acceptAlert()

		break
	}
}

// Close browser
WebUI.closeBrowser()