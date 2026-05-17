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
WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'), 'customer2@gmail.com')

WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'), 'Customer@123456')

WebUI.click(findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in'))

// Verify login successful
WebUI.verifyElementPresent(findTestObject('CUSTOMER/HomePage/btn_menu_profile'), 10)

// ========================================
// Open Product Detail page
// ========================================
WebUI.click(findTestObject('CUSTOMER/HomePage/btn_menu_Home'))

WebUI.click(findTestObject('CUSTOMER/product_detail/productname_jacket'))

// Verify Product page displayed
WebUI.verifyElementPresent(findTestObject('CUSTOMER/product_detail/btn_AddToCart'), 10)

// ========================================
// Add product to cart
// ========================================
WebUI.click(findTestObject('CUSTOMER/product_detail/btn_AddToCart'))

WebUI.delay(2)

// ========================================
// Open Cart page
// ========================================
WebUI.click(findTestObject('CUSTOMER/HomePage/icon_cart'))

WebUI.delay(2)

// ========================================
// Verify quantity field exists
// ========================================
WebUI.verifyElementPresent(findTestObject('CUSTOMER/cart/input_quantity'), 10)

// ========================================
// Set quantity to 1 if current quantity > 1
// ========================================
String quantityText = WebUI.getAttribute(findTestObject('CUSTOMER/cart/input_quantity'), 'value')

int currentQuantity = Integer.parseInt(quantityText)

// Reduce quantity until quantity = 1
while (currentQuantity > 1) {
    WebUI.click(findTestObject('CUSTOMER/cart/btn_decrease_quantity'))

    WebUI.delay(1)

    String updatedQuantityText = WebUI.getAttribute(findTestObject('CUSTOMER/cart/input_quantity'), 'value')

    currentQuantity = Integer.parseInt(updatedQuantityText)
}

// ========================================
// Decrease quantity from 1 -> 0
// ========================================
WebUI.click(findTestObject('CUSTOMER/cart/btn_decrease_quantity'))

WebUI.delay(2)

// ========================================
// Verify alert displayed
// ========================================
WebUI.verifyAlertPresent(10)

// Get alert text
String alertText = WebUI.getAlertText()

// Verify alert message
WebUI.verifyMatch(alertText, '.*Error updating quantity.*', true)

// Accept alert
WebUI.acceptAlert()

// Close browser
WebUI.closeBrowser()

