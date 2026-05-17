import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

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

// ========================================
// Buy product directly
// ========================================
WebUI.click(findTestObject('CUSTOMER/product_detail/btn_buy'))

// ========================================
// Verify Checkout Page displayed
// ========================================
WebUI.verifyElementPresent(findTestObject('CUSTOMER/Checkout_Page/btn_ProceedToPay'), 10)

// ========================================
// Fill Shipping Information
// ========================================
WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_firstName'), 'Customer')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_lastName'), 'Test')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_houseNumber'), '03')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_street'), 'Quang Trung')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_townOrCity'), 'Da Nang')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_postCode'), '55000')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_countryName'), 'Vietnam')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_contactNumber'), '0912345678')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_emailAddress'), 'customer2@gmail.com')

// ========================================
// Proceed To Stripe Payment
// ========================================
WebUI.click(findTestObject('CUSTOMER/Checkout_Page/btn_ProceedToPay'))

WebUI.delay(5)

// ========================================
// Verify Stripe Payment Page displayed
// ========================================
WebUI.verifyElementPresent(findTestObject('CUSTOMER/Payment_Stripe/div_payment_stripe'), 10)

// ========================================
// Fill EXPIRED Card Information
// ========================================
// Valid Stripe test card
WebUI.setText(findTestObject('CUSTOMER/Payment_Stripe/txtbox_cardNumber'), '4242424242424242')

// EXPIRED DATE
// Example: 01/20
WebUI.setText(findTestObject('CUSTOMER/Payment_Stripe/txtbox_cardExpiry'), '0120')

// CVC
WebUI.setText(findTestObject('CUSTOMER/Payment_Stripe/txtbox_cardCvc'), '123')

// Cardholder Name
WebUI.setText(findTestObject('CUSTOMER/Payment_Stripe/txtbox_Cardholder name_billingName'), 'Customer 2')

// Country
WebUI.selectOptionByLabel(findTestObject('CUSTOMER/Payment_Stripe/select_country'), 'Vietnam', false)

WebUI.delay(3)

// ========================================
// Click Pay
// ========================================
WebUI.click(findTestObject('CUSTOMER/Payment_Stripe/btn_Pay'))

WebUI.delay(5)

// ========================================
// Verify payment blocked
// ========================================
// Verify still on Stripe payment page
WebUI.verifyElementPresent(findTestObject('CUSTOMER/Payment_Stripe/div_payment_stripe'), 10)

// Verify Pay button still displayed
WebUI.verifyElementPresent(findTestObject('CUSTOMER/Payment_Stripe/btn_Pay'), 10)

WebUI.verifyElementPresent(findTestObject('CUSTOMER/Payment_Stripe/msg_ExpiredCard'), 0)

// ========================================
// Verify expiration error displayed
// ========================================
// Stripe usually shows:
// "Your card's expiration year is in the past"
// or
// "Your card has expired"
boolean expiredErrorDisplayed = WebUI.verifyTextPresent('Your card’s expiration year is in the past.', false, FailureHandling.OPTIONAL)

WebUI.verifyEqual(expiredErrorDisplayed, true)

// ========================================
// Verify payment NOT successful
// ========================================
boolean paymentSuccessDisplayed = WebUI.verifyTextPresent('Payment successful! Your order has been placed.', false, FailureHandling.OPTIONAL)

WebUI.verifyEqual(paymentSuccessDisplayed, false)

// ========================================
// Verify user NOT redirected to Homepage
// ========================================
boolean homepageDisplayed = WebUI.verifyElementPresent(findTestObject('CUSTOMER/HomePage/btn_menu_profile'), 3, FailureHandling.OPTIONAL)

WebUI.verifyEqual(homepageDisplayed, false)

// ========================================
// Close Browser
// ========================================
WebUI.closeBrowser()

