import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('')

WebUI.maximizeWindow()

// ========================================
// Navigate to Login page
// ========================================
WebUI.navigateToUrl(GlobalVariable.baseUrl + '/login.php')

// ========================================
// Login
// ========================================
WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'), 'customer2@gmail.com')

WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'), 'Customer@123456')

WebUI.click(findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in'))

// Verify login successful
WebUI.verifyElementPresent(findTestObject('CUSTOMER/HomePage/btn_menu_profile'), 10)

// ========================================
// Open Product Detail
// ========================================
WebUI.click(findTestObject('CUSTOMER/HomePage/btn_menu_Home'))

WebUI.click(findTestObject('CUSTOMER/product_detail/productname_jacket'))

// Buy directly
WebUI.click(findTestObject('CUSTOMER/product_detail/btn_buy'))

// Verify checkout page
WebUI.verifyElementPresent(findTestObject('CUSTOMER/Checkout_Page/btn_ProceedToPay'), 10)

// ========================================
// Fill shipping information
// ========================================
WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_firstName'), 'Customer')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_lastName'), '2')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_houseNumber'), '03')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_street'), 'Quang Trung')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_townOrCity'), 'Da Nang')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_postCode'), '55000')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_countryName'), 'Vietnam')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_contactNumber'), '0912345678')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_emailAddress'), 'customer2@gmail.com')

// ========================================
// Submit checkout
// ========================================
WebUI.click(findTestObject('CUSTOMER/Checkout_Page/btn_ProceedToPay'))

WebUI.delay(3)

// ========================================
// Verify redirected to payment page
// ========================================
WebUI.verifyElementPresent(findTestObject('CUSTOMER/Payment_Stripe/div_payment_stripe'), 10)

// Save current URL
String paymentUrl = WebUI.getUrl()

// ========================================
// Refresh page
// ========================================
WebUI.refresh()

WebUI.delay(3)

// ========================================
// Verify system handles refresh safely
// ========================================
// Verify still on same payment page
String currentUrl = WebUI.getUrl()

WebUI.verifyEqual(currentUrl, paymentUrl)

// Verify Stripe page still displayed
WebUI.verifyElementPresent(findTestObject('CUSTOMER/Payment_Stripe/div_payment_stripe'), 10)

// Verify no browser resubmission error
WebUI.verifyTextNotPresent('Confirm Form Resubmission', false)

WebUI.verifyTextNotPresent('ERR_CACHE_MISS', false)

// Verify checkout page not shown again
WebUI.verifyElementNotPresent(findTestObject('CUSTOMER/Checkout_Page/btn_ProceedToPay'), 0)

// Close browser
WebUI.closeBrowser()

