import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

// ========================================
// TC_CUSTOMER_PAYMENT_NetworkInterrupted
// ========================================
WebUI.openBrowser('')

WebUI.maximizeWindow()

// ========================================
// Login
// ========================================
WebUI.navigateToUrl(GlobalVariable.baseUrl + '/login.php')

WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'), 'customer2@gmail.com')

WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'), 'Customer@123456')

WebUI.click(findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in'))

// Verify login success
WebUI.verifyElementPresent(findTestObject('CUSTOMER/HomePage/btn_menu_profile'), 10)

// ========================================
// Open Product Detail
// ========================================
WebUI.click(findTestObject('CUSTOMER/HomePage/btn_menu_Home'))

WebUI.click(findTestObject('CUSTOMER/product_detail/productname_jacket'))

// ========================================
// Buy Product
// ========================================
WebUI.click(findTestObject('CUSTOMER/product_detail/btn_buy'))

// ========================================
// Fill Checkout Information
// ========================================
WebUI.verifyElementPresent(findTestObject('CUSTOMER/Checkout_Page/btn_ProceedToPay'), 10)

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
// Proceed To Stripe
// ========================================
WebUI.click(findTestObject('CUSTOMER/Checkout_Page/btn_ProceedToPay'))

WebUI.delay(5)

// ========================================
// Verify Stripe Page
// ========================================
WebUI.verifyElementPresent(findTestObject('CUSTOMER/Payment_Stripe/div_payment_stripe'), 10)

// ========================================
// Fill Stripe Payment Information
// ========================================
// Valid card
WebUI.setText(findTestObject('CUSTOMER/Payment_Stripe/txtbox_cardNumber'), '4242424242424242')

WebUI.setText(findTestObject('CUSTOMER/Payment_Stripe/txtbox_cardExpiry'), '1230')

WebUI.setText(findTestObject('CUSTOMER/Payment_Stripe/txtbox_cardCvc'), '123')

WebUI.setText(findTestObject('CUSTOMER/Payment_Stripe/txtbox_Cardholder name_billingName'), 'Customer 2')

WebUI.selectOptionByLabel(findTestObject('CUSTOMER/Payment_Stripe/select_country'), 'Vietnam', false)

// ========================================
// START PAYMENT
// ========================================
WebUI.click(findTestObject('CUSTOMER/Payment_Stripe/btn_Pay'))

// ========================================
// MANUAL TEST STEP
// ========================================
println('========== MANUAL STEP REQUIRED ==========')

println('1. TURN OFF INTERNET CONNECTION')

println('2. WAIT UNTIL STRIPE PAGE FREEZES / LOADING')

println('3. REFRESH PAGE')

println('4. TURN INTERNET BACK ON')

println('5. REFRESH PAGE AGAIN')

println('==========================================')

// Wait for tester manual actions
WebUI.delay(30)

WebUI.verifyElementPresent(findTestObject('CUSTOMER/Payment_Stripe/msg_NetworkInterrupted'), 0)

WebUI.verifyTextPresent('You’re all done here', false)

WebUI.verifyTextPresent('You’ve either completed your payment or this checkout session has timed out.', false)

// ========================================
// Verify NO success message
// ========================================
WebUI.verifyTextNotPresent('Payment successful! Your order has been placed.', false)

// ========================================
// Go back to website homepage
// ========================================
WebUI.navigateToUrl(GlobalVariable.baseUrl)

// ========================================
// Verify cart still contains item
// ========================================
WebUI.click(findTestObject('CUSTOMER/HomePage/icon_cart'))

// Cart should NOT be empty
WebUI.verifyTextNotPresent('No item available in cart', false)

// ========================================
// Verify no duplicate payment success
// ========================================
// Homepage success payment message should not exist
WebUI.verifyTextNotPresent('Payment successful! Your order has been placed.', false)

// ========================================
// Close Browser
// ========================================
WebUI.closeBrowser()

