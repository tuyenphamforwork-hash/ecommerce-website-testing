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

String cartCountBefore = WebUI.getText(findTestObject('CUSTOMER/HomePage/tv_cartcount_label')).trim()

int beforeCount = Integer.parseInt(cartCountBefore)

WebUI.click(findTestObject('CUSTOMER/product_detail/btn_AddToCart'))

WebUI.delay(2)

String cartCountAfter = WebUI.getText(findTestObject('CUSTOMER/HomePage/tv_cartcount_label')).trim()

int afterCount = Integer.parseInt(cartCountAfter)

WebUI.verifyEqual(afterCount, beforeCount + 1)

WebUI.click(findTestObject('CUSTOMER/HomePage/icon_cart'))

WebUI.verifyElementPresent(findTestObject('CUSTOMER/cart/cart_product_items'), 10)

WebUI.closeBrowser()

