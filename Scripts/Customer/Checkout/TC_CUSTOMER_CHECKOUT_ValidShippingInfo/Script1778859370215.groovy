import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl(GlobalVariable.baseUrl + '/login.php')

WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'), 'customer2@gmail.com')

WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'), 'Customer@123456')

WebUI.click(findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in'))

WebUI.verifyElementPresent(findTestObject('CUSTOMER/HomePage/btn_menu_profile'), 10)

WebUI.click(findTestObject('CUSTOMER/HomePage/icon_cart'))

WebUI.delay(2)

boolean checkoutButtonExists = WebUI.verifyElementPresent(findTestObject('CUSTOMER/cart/btn_ProceedToCheckOut'), 3, com.kms.katalon.core.model.FailureHandling.OPTIONAL)

if (!(checkoutButtonExists)) {

    WebUI.click(findTestObject('CUSTOMER/HomePage/btn_menu_Home'))

    WebUI.click(findTestObject('CUSTOMER/product_detail/productname_jacket'))

    WebUI.click(findTestObject('CUSTOMER/product_detail/btn_AddToCart'))

    WebUI.delay(2)

    WebUI.click(findTestObject('CUSTOMER/HomePage/icon_cart'))

    WebUI.delay(2)
}

WebUI.click(findTestObject('CUSTOMER/cart/btn_ProceedToCheckOut'))

WebUI.delay(2)

WebUI.verifyElementPresent(findTestObject('CUSTOMER/Checkout_Page/txtbox_firstName'), 10)

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_firstName'), 'Customer')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_lastName'), '2')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_houseNumber'), '254')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_street'), 'Nguyen Van Linh')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_townOrCity'), 'Da Nang')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_postCode'), '550000')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_countryName'), 'Vietnam')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_contactNumber'), '0987654321')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_emailAddress'), 'customer2@gmail.com')

WebUI.click(findTestObject('CUSTOMER/Checkout_Page/btn_ProceedToPay'))

WebUI.delay(5)

WebUI.verifyElementPresent(findTestObject('CUSTOMER/Payment_Stripe/div_payment_stripe'), 10)

WebUI.closeBrowser()

