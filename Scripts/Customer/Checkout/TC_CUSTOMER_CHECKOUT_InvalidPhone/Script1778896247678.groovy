import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')

WebUI.maximizeWindow()

// Login
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

// Verify login success
WebUI.verifyElementPresent(
	findTestObject('CUSTOMER/HomePage/btn_menu_profile'),
	10
)

// ========================================
// Open Product Detail
// ========================================

WebUI.click(
	findTestObject('CUSTOMER/HomePage/btn_menu_Home')
)

WebUI.click(
	findTestObject('CUSTOMER/product_detail/productname_jacket')
)

// Buy directly
WebUI.click(
	findTestObject('CUSTOMER/product_detail/btn_buy')
)

// Verify checkout page
WebUI.verifyElementPresent(
	findTestObject('CUSTOMER/Checkout_Page/btn_ProceedToPay'),
	10
)

// ========================================
// Fill valid shipping info
// ========================================

WebUI.setText(
	findTestObject('CUSTOMER/Checkout_Page/txtbox_firstName'),
	'Customer'
)

WebUI.setText(
	findTestObject('CUSTOMER/Checkout_Page/txtbox_lastName'),
	'Test'
)

WebUI.setText(
	findTestObject('CUSTOMER/Checkout_Page/txtbox_houseNumber'),
	'03'
)

WebUI.setText(
	findTestObject('CUSTOMER/Checkout_Page/txtbox_street'),
	'Quang Trung'
)

WebUI.setText(
	findTestObject('CUSTOMER/Checkout_Page/txtbox_townOrCity'),
	'Da Nang'
)

WebUI.setText(
	findTestObject('CUSTOMER/Checkout_Page/txtbox_postCode'),
	'55000'
)

WebUI.setText(
	findTestObject('CUSTOMER/Checkout_Page/txtbox_countryName'),
	'Vietnam'
)

// INVALID PHONE
WebUI.setText(
	findTestObject('CUSTOMER/Checkout_Page/txtbox_contactNumber'),
	'123abc'
)

WebUI.setText(
	findTestObject('CUSTOMER/Checkout_Page/txtbox_emailAddress'),
	'customer2@gmail.com'
)

// ========================================
// Proceed To Pay
// ========================================

WebUI.click(
	findTestObject('CUSTOMER/Checkout_Page/btn_ProceedToPay')
)

WebUI.delay(2)

// ========================================
// Verify checkout NOT successful
// ========================================

// Still stays on checkout page
WebUI.verifyElementPresent(
	findTestObject('CUSTOMER/Checkout_Page/btn_ProceedToPay'),
	10
)

// Stripe payment page NOT displayed
WebUI.verifyElementNotPresent(
	findTestObject('CUSTOMER/Payment_Stripe/div_payment_stripe'),
	0
)

WebUI.closeBrowser()