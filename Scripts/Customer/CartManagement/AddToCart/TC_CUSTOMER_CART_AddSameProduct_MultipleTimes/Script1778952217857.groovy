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

WebUI.click(findTestObject('CUSTOMER/product_detail/btn_AddToCart'))

WebUI.delay(2)

WebUI.click(findTestObject('CUSTOMER/HomePage/icon_cart'))

WebUI.verifyElementPresent(findTestObject('CUSTOMER/cart/lbl_product_name'), 10)

WebUI.waitForElementVisible(findTestObject('CUSTOMER/cart/input_quantity'), 10)

String quantityValue = WebUI.getAttribute(findTestObject('CUSTOMER/cart/input_quantity'), 'value')

WebUI.comment('Quantity Value = ' + quantityValue)

int quantity = Integer.parseInt(quantityValue)

WebUI.click(findTestObject('CUSTOMER/HomePage/icon_cart'))

WebUI.waitForElementVisible(findTestObject('CUSTOMER/cart/input_quantity'), 10)

String beforeQuantityValue = WebUI.getAttribute(findTestObject('CUSTOMER/cart/input_quantity'), 'value')

int beforeQuantity = Integer.parseInt(beforeQuantityValue)

WebUI.comment('Before Quantity = ' + beforeQuantity)

WebUI.back()

WebUI.delay(2)

WebUI.click(findTestObject('CUSTOMER/product_detail/btn_AddToCart'))

WebUI.delay(2)

WebUI.click(findTestObject('CUSTOMER/product_detail/btn_AddToCart'))

WebUI.delay(2)

WebUI.click(findTestObject('CUSTOMER/HomePage/icon_cart'))

String afterQuantityValue = WebUI.getAttribute(findTestObject('CUSTOMER/cart/input_quantity'), 'value')

int afterQuantity = Integer.parseInt(afterQuantityValue)

WebUI.comment('After Quantity = ' + afterQuantity)

WebUI.verifyEqual(afterQuantity, beforeQuantity + 2)

WebUI.verifyElementPresent(findTestObject('CUSTOMER/cart/lbl_product_image'), 10)

WebUI.closeBrowser()

