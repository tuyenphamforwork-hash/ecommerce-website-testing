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

WebUI.click(findTestObject('CUSTOMER/HomePage/btn_menu_Home'))

WebUI.click(findTestObject('CUSTOMER/product_detail/productname_jacket'))

WebUI.verifyElementPresent(findTestObject('CUSTOMER/product_detail/btn_AddToCart'), 10)

WebUI.click(findTestObject('CUSTOMER/product_detail/btn_AddToCart'))

WebUI.delay(2)

WebUI.click(findTestObject('CUSTOMER/HomePage/icon_cart'))

WebUI.delay(2)

WebUI.verifyElementPresent(findTestObject('CUSTOMER/cart/lbl_product_name'), 10)

WebUI.verifyElementPresent(findTestObject('CUSTOMER/cart/btn_ProceedToCheckOut'), 10)

WebUI.click(findTestObject('CUSTOMER/cart/btn_ProceedToCheckOut'))

WebUI.delay(3)

String currentUrl = WebUI.getUrl()

boolean isCheckoutPage = currentUrl.contains('checkout') || currentUrl.contains('payment')

WebUI.verifyEqual(isCheckoutPage, true)

WebUI.verifyElementPresent(findTestObject('CUSTOMER/Checkout_Page/div_Checkout'), 10)

WebUI.closeBrowser()

