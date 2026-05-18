import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.model.FailureHandling as FailureHandling

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

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_lastName'), 'Test')

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

WebUI.setText(findTestObject('CUSTOMER/Payment_Stripe/txtbox_cardNumber'), '4000000000000002')

WebUI.setText(findTestObject('CUSTOMER/Payment_Stripe/txtbox_cardExpiry'), '0130')

WebUI.setText(findTestObject('CUSTOMER/Payment_Stripe/txtbox_cardCvc'), '123')

WebUI.setText(findTestObject('CUSTOMER/Payment_Stripe/txtbox_Cardholder name_billingName'), 'Customer 2')

WebUI.selectOptionByLabel(findTestObject('CUSTOMER/Payment_Stripe/select_country'), 'Vietnam', false)

WebUI.delay(3)

WebUI.click(findTestObject('CUSTOMER/Payment_Stripe/btn_Pay'))

WebUI.delay(8)

WebUI.verifyElementPresent(findTestObject('CUSTOMER/Payment_Stripe/btn_Pay'), 10)

WebUI.verifyElementPresent(findTestObject('CUSTOMER/Payment_Stripe/msg_CardDeclined'), 10)

WebUI.verifyTextPresent('Your credit card was declined. Try paying with a debit card instead.', false)

boolean paymentSuccessDisplayed = WebUI.verifyTextPresent('Payment successful! Your order has been placed.', false, FailureHandling.OPTIONAL)

WebUI.verifyEqual(paymentSuccessDisplayed, false)

boolean homepageDisplayed = WebUI.verifyElementPresent(findTestObject('CUSTOMER/HomePage/btn_menu_profile'), 3, FailureHandling.OPTIONAL)

WebUI.verifyEqual(homepageDisplayed, false)

WebUI.verifyElementPresent(findTestObject('CUSTOMER/Payment_Stripe/div_payment_stripe'), 10)

WebUI.closeBrowser()

