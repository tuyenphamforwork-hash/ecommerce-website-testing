import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

// =====================================================
// TC_CUSTOMER_CHECKOUT_FromCart
// Verify checkout from cart successfully
// =====================================================
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
// Open Home page
// ========================================
WebUI.click(findTestObject('CUSTOMER/HomePage/btn_menu_Home'))

// ========================================
// Open Product Detail page
// ========================================
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
// Verify product exists in cart
// ========================================
WebUI.verifyElementPresent(findTestObject('CUSTOMER/cart/lbl_product_name'), 10)

// ========================================
// Verify Proceed To CheckOut button displayed
// ========================================
WebUI.verifyElementPresent(findTestObject('CUSTOMER/cart/btn_ProceedToCheckOut'), 10)

// ========================================
// Click Proceed To CheckOut
// ========================================
WebUI.click(findTestObject('CUSTOMER/cart/btn_ProceedToCheckOut'))

WebUI.delay(3)

// ========================================
// Verify redirect to Checkout page
// ========================================
String currentUrl = WebUI.getUrl()

boolean isCheckoutPage = currentUrl.contains('checkout') || currentUrl.contains('payment')

WebUI.verifyEqual(isCheckoutPage, true)

// ========================================
// Verify checkout fields displayed
// ========================================
WebUI.verifyElementPresent(findTestObject('CUSTOMER/Checkout_Page/div_Checkout'), 10)

// Close browser
WebUI.closeBrowser()

