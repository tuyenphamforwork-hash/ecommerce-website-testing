import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling
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
// OPTION 1:
// Buy directly from Product Detail
// ========================================
boolean buyButtonExists = WebUI.verifyElementPresent(findTestObject('CUSTOMER/product_detail/btn_buy'), 5, FailureHandling.OPTIONAL)

if (buyButtonExists) {
    // Click Buy button
    WebUI.click(findTestObject('CUSTOMER/product_detail/btn_buy')) // ========================================
    // OPTION 2:
    // Add to Cart -> Proceed To Checkout
    // ========================================
    // Add product to cart
    // Open Cart page
    // Verify Proceed To Checkout button displayed
    // Click Proceed To Checkout
} else {
    WebUI.click(findTestObject('CUSTOMER/product_detail/btn_AddToCart'))

    WebUI.delay(2)

    WebUI.click(findTestObject('CUSTOMER/HomePage/icon_cart'))

    WebUI.delay(2)

    WebUI.verifyElementPresent(findTestObject('CUSTOMER/cart/btn_ProceedToCheckOut'), 10)

    WebUI.click(findTestObject('CUSTOMER/cart/btn_ProceedToCheckOut'))
}

// ========================================
// Verify redirect to Checkout/Payment page
// ========================================
WebUI.delay(3)

// Verify URL contains checkout/payment
String currentUrl = WebUI.getUrl()

boolean isCheckoutPage = currentUrl.contains('checkout') || currentUrl.contains('payment')

WebUI.verifyEqual(isCheckoutPage, true)

// ========================================
// Verify checkout information displayed
// ========================================
WebUI.verifyElementPresent(findTestObject('CUSTOMER/Checkout_Page/div_Checkout'), 10)

// Close browser
WebUI.closeBrowser()

