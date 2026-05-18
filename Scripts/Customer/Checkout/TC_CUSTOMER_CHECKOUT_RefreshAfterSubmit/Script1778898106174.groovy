import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

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

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_lastName'), '2')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_houseNumber'), '03')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_street'), 'Quang Trung')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_townOrCity'), 'Da Nang')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_postCode'), '55000')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_countryName'), 'Vietnam')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_contactNumber'), '0912345678')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_emailAddress'), 'customer2@gmail.com')

WebUI.click(findTestObject('CUSTOMER/Checkout_Page/btn_ProceedToPay'))

WebUI.delay(3)

WebUI.verifyElementPresent(findTestObject('CUSTOMER/Payment_Stripe/div_payment_stripe'), 10)

String paymentUrl = WebUI.getUrl()

WebUI.refresh()

WebUI.delay(3)

String currentUrl = WebUI.getUrl()

WebUI.verifyEqual(currentUrl, paymentUrl)

WebUI.verifyElementPresent(findTestObject('CUSTOMER/Payment_Stripe/div_payment_stripe'), 10)

WebUI.verifyTextNotPresent('Confirm Form Resubmission', false)

WebUI.verifyTextNotPresent('ERR_CACHE_MISS', false)

WebUI.verifyElementNotPresent(findTestObject('CUSTOMER/Checkout_Page/btn_ProceedToPay'), 0)

WebUI.closeBrowser()

