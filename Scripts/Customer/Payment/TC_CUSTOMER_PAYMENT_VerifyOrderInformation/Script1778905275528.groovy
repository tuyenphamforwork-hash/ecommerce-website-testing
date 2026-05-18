import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import java.time.LocalDateTime as LocalDateTime
import java.time.format.DateTimeFormatter as DateTimeFormatter
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

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_firstName'), 'Customer')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_lastName'), 'Test')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_houseNumber'), '03')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_street'), 'Quang Trung')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_townOrCity'), 'Da Nang')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_postCode'), '55000')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_countryName'), 'Vietnam')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_contactNumber'), '0912345678')

String checkoutEmail = 'customer2@gmail.com'

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_emailAddress'), checkoutEmail)

WebUI.click(findTestObject('CUSTOMER/Checkout_Page/btn_ProceedToPay'))

WebUI.delay(1)

WebUI.verifyElementPresent(findTestObject('CUSTOMER/Payment_Stripe/div_payment_stripe'), 10)

String stripeEmail = WebUI.getText(findTestObject('CUSTOMER/Payment_Stripe/lbl_customerEmail'))

println('Checkout Email: ' + checkoutEmail)

println('Stripe Email: ' + stripeEmail)

WebUI.verifyEqual(stripeEmail.trim(), checkoutEmail)

String stripeTotalPrice = WebUI.getText(findTestObject('CUSTOMER/Payment_Stripe/lbl_totalPrice'))

println('Stripe Total Price: ' + stripeTotalPrice)

WebUI.setText(findTestObject('CUSTOMER/Payment_Stripe/txtbox_cardNumber'), '4242424242424242')

WebUI.setText(findTestObject('CUSTOMER/Payment_Stripe/txtbox_cardExpiry'), '1230')

WebUI.setText(findTestObject('CUSTOMER/Payment_Stripe/txtbox_cardCvc'), '123')

WebUI.setText(findTestObject('CUSTOMER/Payment_Stripe/txtbox_Cardholder name_billingName'), 'Customer 2')

WebUI.selectOptionByLabel(findTestObject('CUSTOMER/Payment_Stripe/select_country'), 'Vietnam', false)

WebUI.click(findTestObject('CUSTOMER/Payment_Stripe/btn_Pay'))

LocalDateTime now = LocalDateTime.now()

DateTimeFormatter formatter = DateTimeFormatter.ofPattern('yyyy-MM-dd HH:mm')

String currentDateTime = now.format(formatter)

println('Current Datetime: ' + currentDateTime)

WebUI.verifyElementPresent(findTestObject('CUSTOMER/Profile/btn_menu_profile'), 0)

WebUI.verifyTextPresent('Payment successful! Your order has been placed.', false)

WebUI.click(findTestObject('CUSTOMER/HomePage/btn_menu_profile'))

String latestOrderTotalPrice = WebUI.getText(findTestObject('CUSTOMER/Order_History/txtview_totalPrice'))

String latestOrderDateTime = WebUI.getText(findTestObject('CUSTOMER/Order_History/txtview_date_time'))

println('Latest Order Total Price: ' + latestOrderTotalPrice)

println('Latest Order DateTime: ' + latestOrderDateTime)

WebUI.verifyEqual(latestOrderTotalPrice.trim(), stripeTotalPrice.trim())

String currentMinute = currentDateTime.substring(0, 16)

WebUI.verifyMatch(latestOrderDateTime, ('.*' + currentMinute) + '.*', true)

WebUI.closeBrowser()

