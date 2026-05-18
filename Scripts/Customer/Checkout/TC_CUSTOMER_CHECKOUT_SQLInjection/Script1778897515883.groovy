import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')

WebUI.maximizeWindow()

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

WebUI.verifyElementPresent(
	findTestObject('CUSTOMER/HomePage/btn_menu_profile'),
	10
)

WebUI.click(
	findTestObject('CUSTOMER/HomePage/btn_menu_Home')
)

WebUI.click(
	findTestObject('CUSTOMER/product_detail/productname_jacket')
)

WebUI.click(
	findTestObject('CUSTOMER/product_detail/btn_buy')
)

WebUI.verifyElementPresent(
	findTestObject('CUSTOMER/Checkout_Page/btn_ProceedToPay'),
	10
)

String sqlPayload = "' OR '1'='1"

WebUI.setText(
	findTestObject('CUSTOMER/Checkout_Page/txtbox_firstName'),
	sqlPayload
)

WebUI.setText(
	findTestObject('CUSTOMER/Checkout_Page/txtbox_lastName'),
	sqlPayload
)

WebUI.setText(
	findTestObject('CUSTOMER/Checkout_Page/txtbox_houseNumber'),
	sqlPayload
)

WebUI.setText(
	findTestObject('CUSTOMER/Checkout_Page/txtbox_street'),
	sqlPayload
)

WebUI.setText(
	findTestObject('CUSTOMER/Checkout_Page/txtbox_townOrCity'),
	sqlPayload
)

WebUI.setText(
	findTestObject('CUSTOMER/Checkout_Page/txtbox_postCode'),
	'55000'
)

WebUI.setText(
	findTestObject('CUSTOMER/Checkout_Page/txtbox_countryName'),
	sqlPayload
)

WebUI.setText(
	findTestObject('CUSTOMER/Checkout_Page/txtbox_contactNumber'),
	'0912345678'
)

WebUI.setText(
	findTestObject('CUSTOMER/Checkout_Page/txtbox_emailAddress'),
	'customer2@gmail.com'
)


WebUI.click(
	findTestObject('CUSTOMER/Checkout_Page/btn_ProceedToPay')
)

WebUI.delay(3)

WebUI.verifyTextNotPresent('SQL syntax', false)

WebUI.verifyTextNotPresent('mysql_fetch', false)

WebUI.verifyTextNotPresent('mysqli', false)

WebUI.verifyTextNotPresent('ORA-', false)

WebUI.verifyTextNotPresent('syntax error', false)

WebUI.verifyTextNotPresent('Unclosed quotation mark', false)

boolean checkoutPageExists = WebUI.verifyElementPresent(
	findTestObject('CUSTOMER/Checkout_Page/btn_ProceedToPay'),
	3,
	com.kms.katalon.core.model.FailureHandling.OPTIONAL
)

boolean paymentPageExists = WebUI.verifyElementPresent(
	findTestObject('CUSTOMER/Payment_Stripe/div_payment_stripe'),
	3,
	com.kms.katalon.core.model.FailureHandling.OPTIONAL
)

WebUI.verifyEqual(
	checkoutPageExists || paymentPageExists,
	true
)

WebUI.closeBrowser()