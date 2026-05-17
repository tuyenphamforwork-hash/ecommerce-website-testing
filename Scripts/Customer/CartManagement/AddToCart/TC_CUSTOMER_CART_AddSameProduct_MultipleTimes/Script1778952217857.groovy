import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

// =====================================================
// TC_CUSTOMER_CART_AddSameProduct_MultipleTimes
// Verify adding same product multiple times
// =====================================================
// Open browser
WebUI.openBrowser('')

WebUI.maximizeWindow()

// Navigate to Login page
WebUI.navigateToUrl(GlobalVariable.baseUrl + '/login.php')

// Login
WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'), 'customer2@gmail.com')

WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'), 'Customer@123456')

WebUI.click(findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in'))

// Verify login success
WebUI.verifyElementPresent(findTestObject('CUSTOMER/HomePage/btn_menu_profile'), 10)

// Open Home page
WebUI.click(findTestObject('CUSTOMER/HomePage/btn_menu_Home'))

// Open Product Detail page
WebUI.click(findTestObject('CUSTOMER/product_detail/productname_jacket'))

// Verify Add To Cart button displayed
WebUI.verifyElementPresent(findTestObject('CUSTOMER/product_detail/btn_AddToCart'), 10)

// ========================================
// First Add To Cart
// ========================================
WebUI.click(findTestObject('CUSTOMER/product_detail/btn_AddToCart'))

WebUI.delay(2)

// ========================================
// Second Add To Cart
// ========================================
WebUI.click(findTestObject('CUSTOMER/product_detail/btn_AddToCart'))

WebUI.delay(2)

// Open Cart page
WebUI.click(findTestObject('CUSTOMER/HomePage/icon_cart'))

// Verify product displayed in cart
WebUI.verifyElementPresent(findTestObject('CUSTOMER/cart/lbl_product_name'), 10)

// Wait for quantity field visible
WebUI.waitForElementVisible(findTestObject('CUSTOMER/cart/input_quantity'), 10)

// Get quantity value
String quantityValue = WebUI.getAttribute(findTestObject('CUSTOMER/cart/input_quantity'), 'value')

WebUI.comment('Quantity Value = ' + quantityValue)

// Convert to integer
int quantity = Integer.parseInt(quantityValue)

// ========================================
// Get current quantity BEFORE adding
// ========================================
// Open Cart page first
WebUI.click(findTestObject('CUSTOMER/HomePage/icon_cart'))

// Wait for quantity visible
WebUI.waitForElementVisible(findTestObject('CUSTOMER/cart/input_quantity'), 10)

// Get quantity before
String beforeQuantityValue = WebUI.getAttribute(findTestObject('CUSTOMER/cart/input_quantity'), 'value')

int beforeQuantity = Integer.parseInt(beforeQuantityValue)

WebUI.comment('Before Quantity = ' + beforeQuantity)

// ========================================
// Continue adding same product
// ========================================
// Back to product page
WebUI.back()

WebUI.delay(2)

// First Add
WebUI.click(findTestObject('CUSTOMER/product_detail/btn_AddToCart'))

WebUI.delay(2)

// Second Add
WebUI.click(findTestObject('CUSTOMER/product_detail/btn_AddToCart'))

WebUI.delay(2)

// Open Cart again
WebUI.click(findTestObject('CUSTOMER/HomePage/icon_cart'))

// Get updated quantity
String afterQuantityValue = WebUI.getAttribute(findTestObject('CUSTOMER/cart/input_quantity'), 'value')

int afterQuantity = Integer.parseInt(afterQuantityValue)

WebUI.comment('After Quantity = ' + afterQuantity)

// ========================================
// Verify quantity increased correctly
// ========================================
WebUI.verifyEqual(afterQuantity, beforeQuantity + 2)

// Verify product image displayed
WebUI.verifyElementPresent(findTestObject('CUSTOMER/cart/lbl_product_image'), 10)

// Close browser
WebUI.closeBrowser()

