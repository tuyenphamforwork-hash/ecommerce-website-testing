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

WebUI.navigateToUrl(GlobalVariable.baseUrl + '/login.php')

WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'), 'customer2@gmail.com')

WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'), 'Customer@123456')

WebUI.click(findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in'))

WebUI.verifyElementPresent(findTestObject('CUSTOMER/HomePage/btn_menu_profile'), 10)

WebUI.click(findTestObject('CUSTOMER/HomePage/btn_menu_Home'))

WebUI.click(findTestObject('CUSTOMER/product_detail/productname_jacket'))

WebUI.click(findTestObject('CUSTOMER/product_detail/btn_buy'))

WebUI.verifyElementPresent(findTestObject('CUSTOMER/Checkout_Page/btn_ProceedToPay'), 10)

String checkoutEmail = 'customer2@gmail.com'

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_firstName'), 'Customer')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_lastName'), '2')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_houseNumber'), '03')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_street'), 'Quang Trung')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_townOrCity'), 'Da Nang')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_postCode'), '55000')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_countryName'), 'Vietnam')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_contactNumber'), '0912345678')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_emailAddress'), checkoutEmail)

WebUI.click(findTestObject('CUSTOMER/Checkout_Page/btn_ProceedToPay'))

WebUI.delay(5)

WebUI.verifyElementPresent(findTestObject('CUSTOMER/Payment_Stripe/div_payment_stripe'), 10)

WebUI.verifyElementPresent(findTestObject('CUSTOMER/Payment_Stripe/div_order_information'), 10)

String stripeEmail = WebUI.getText(findTestObject('CUSTOMER/Payment_Stripe/lbl_customerEmail')).trim()

WebUI.verifyEqual(stripeEmail, checkoutEmail)

WebUI.setText(findTestObject('CUSTOMER/Payment_Stripe/txtbox_cardNumber'), '4242424242424242')

WebUI.setText(findTestObject('CUSTOMER/Payment_Stripe/txtbox_cardExpiry'), '1230')

WebUI.setText(findTestObject('CUSTOMER/Payment_Stripe/txtbox_cardCvc'), '123')

WebUI.setText(findTestObject('CUSTOMER/Payment_Stripe/txtbox_Cardholder name_billingName'), 'Customer 2')

WebUI.selectOptionByLabel(findTestObject('CUSTOMER/Payment_Stripe/select_country'), 'Vietnam', false)

WebUI.delay(2)

WebUI.click(findTestObject('CUSTOMER/Payment_Stripe/btn_Pay'))

WebUI.delay(5)

WebUI.verifyElementPresent(findTestObject('CUSTOMER/HomePage/btn_menu_profile'), 10)

WebUI.verifyTextPresent('Payment successful! Your order has been placed.', false)

WebUI.verifyElementNotPresent(findTestObject('CUSTOMER/Payment_Stripe/btn_Pay'), 0, FailureHandling.OPTIONAL)

WebUI.delay(3)

WebUI.click(findTestObject('CUSTOMER/HomePage/btn_menu_profile'))

WebUI.delay(3)

WebUI.verifyElementPresent(findTestObject('CUSTOMER/Order_History/txtview_TitleOrder History'), 0)

WebUI.verifyElementPresent(findTestObject('CUSTOMER/Order_History/txtview_totalPrice'), 0)

WebUI.verifyElementPresent(findTestObject('CUSTOMER/Order_History/txtview_status_Payment'), 0)

WebUI.verifyElementPresent(findTestObject('CUSTOMER/Order_History/txtview_date_time'), 0)

WebUI.verifyElementPresent(findTestObject('CUSTOMER/Order_History/btn_ViewDetails'), 10)

String latestOrderDate = WebUI.getText(findTestObject('CUSTOMER/Order_History/txtview_date_time'))

WebUI.verifyMatch(latestOrderDate, '.*', true)

WebUI.click(findTestObject('CUSTOMER/HomePage/icon_cart'))

WebUI.delay(3)

WebUI.verifyTextPresent('No item available in cart', false)

WebUI.verifyElementNotPresent(findTestObject('CUSTOMER/cart/btn_ProceedToCheckOut'), 0)

boolean productStillExists = WebUI.verifyElementPresent(findTestObject('CUSTOMER/cart/lbl_product_name'), 3, com.kms.katalon.core.model.FailureHandling.OPTIONAL)

WebUI.verifyEqual(productStillExists, false)

WebUI.closeBrowser()

