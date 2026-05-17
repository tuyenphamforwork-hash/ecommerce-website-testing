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
// Get quantity before update
// ========================================
String beforeQuantityText = WebUI.getAttribute(findTestObject('CUSTOMER/cart/input_quantity'), 'value')

int beforeQuantity = Integer.parseInt(beforeQuantityText)

// ========================================
// Increase quantity
// ========================================
WebUI.click(findTestObject('CUSTOMER/cart/btn_increase_quantity'))

WebUI.delay(2)

// ========================================
// Get quantity after increase
// ========================================
String afterIncreaseText = WebUI.getAttribute(findTestObject('CUSTOMER/cart/input_quantity'), 'value')

int afterIncreaseQuantity = Integer.parseInt(afterIncreaseText)

// Verify quantity increased by 1
WebUI.verifyEqual(afterIncreaseQuantity, beforeQuantity + 1)

// ========================================
// Decrease quantity
// ========================================
WebUI.click(findTestObject('CUSTOMER/cart/btn_decrease_quantity'))

WebUI.delay(2)

// ========================================
// Get quantity after decrease
// ========================================
String afterDecreaseText = WebUI.getAttribute(findTestObject('CUSTOMER/cart/input_quantity'), 'value')

int afterDecreaseQuantity = Integer.parseInt(afterDecreaseText)

// Verify quantity returned to original
WebUI.verifyEqual(afterDecreaseQuantity, beforeQuantity)

// Close browser
WebUI.closeBrowser()

