import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

// Open browser
WebUI.openBrowser('')

WebUI.maximizeWindow()

// Navigate to Login page
WebUI.navigateToUrl(GlobalVariable.baseUrl + '/login.php')

// Login
WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'), 'customer2@gmail.com')

WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'), 'Customer@123456')

WebUI.click(findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in'))

// Verify login successful
WebUI.verifyElementPresent(findTestObject('CUSTOMER/HomePage/btn_menu_profile'), 10)

// ========================================
// Open Cart
// ========================================
WebUI.click(findTestObject('CUSTOMER/HomePage/icon_cart'))

WebUI.delay(2)

// ========================================
// If cart empty -> add product first
// ========================================
boolean checkoutButtonExists = WebUI.verifyElementPresent(findTestObject('CUSTOMER/cart/btn_ProceedToCheckOut'), 3, com.kms.katalon.core.model.FailureHandling.OPTIONAL)

if (!(checkoutButtonExists)) {
    // Go Home
    WebUI.click(findTestObject('CUSTOMER/HomePage/btn_menu_Home'))

    // Open product detail
    WebUI.click(findTestObject('CUSTOMER/product_detail/productname_jacket'))

    // Add to cart
    WebUI.click(findTestObject('CUSTOMER/product_detail/btn_AddToCart'))

    WebUI.delay(2)

    // Open cart again
    WebUI.click(findTestObject('CUSTOMER/HomePage/icon_cart'))

    WebUI.delay(2)
}

// ========================================
// Proceed To Checkout
// ========================================
WebUI.click(findTestObject('CUSTOMER/cart/btn_ProceedToCheckOut'))

WebUI.delay(2)

// ========================================
// Verify Checkout page displayed
// ========================================
WebUI.verifyElementPresent(findTestObject('CUSTOMER/Checkout_Page/txtbox_firstName'), 10)

// ========================================
// Input valid shipping information
// ========================================
WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_firstName'), 'Customer')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_lastName'), '2')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_houseNumber'), '254')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_street'), 'Nguyen Van Linh')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_townOrCity'), 'Da Nang')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_postCode'), '550000')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_countryName'), 'Vietnam')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_contactNumber'), '0987654321')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_emailAddress'), 'customer2@gmail.com')

// ========================================
// Click Proceed To Pay
// ========================================
WebUI.click(findTestObject('CUSTOMER/Checkout_Page/btn_ProceedToPay'))

WebUI.delay(5)

// ========================================
// Verify redirect to Stripe Payment page
// ========================================
WebUI.verifyElementPresent(findTestObject('CUSTOMER/Payment_Stripe/div_payment_stripe'), 10)

// Close browser
WebUI.closeBrowser()

