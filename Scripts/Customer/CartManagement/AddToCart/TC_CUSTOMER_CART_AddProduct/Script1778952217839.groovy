import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

// =====================================================
// TC_CUSTOMER_CART_AddProduct_Success
// Verify add product to cart successfully
// =====================================================
// Open browser
WebUI.openBrowser('')

WebUI.maximizeWindow()

// Navigate to Login page
WebUI.navigateToUrl(GlobalVariable.baseUrl + '/login.php')

// Enter valid Email
WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'), 'customer2@gmail.com')

// Enter valid Password
WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'), 'Customer@123456')

// Click Sign In button
WebUI.click(findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in'))

// Verify login successful
WebUI.verifyElementPresent(findTestObject('CUSTOMER/HomePage/btn_menu_profile'), 10)

// Open Home page
WebUI.click(findTestObject('CUSTOMER/HomePage/btn_menu_Home'))

// Open Product Detail page
WebUI.click(findTestObject('CUSTOMER/product_detail/productname_jacket'))

// Verify product page displayed
WebUI.verifyElementPresent(findTestObject('CUSTOMER/product_detail/btn_AddToCart'), 10)

// Get current cart count
String cartCountBefore = WebUI.getText(findTestObject('CUSTOMER/HomePage/tv_cartcount_label')).trim()

int beforeCount = Integer.parseInt(cartCountBefore)

// Click Add To Cart button
WebUI.click(findTestObject('CUSTOMER/product_detail/btn_AddToCart'))

// Wait for cart update
WebUI.delay(2)

// Get updated cart count
String cartCountAfter = WebUI.getText(findTestObject('CUSTOMER/HomePage/tv_cartcount_label')).trim()

int afterCount = Integer.parseInt(cartCountAfter)

// Verify cart count increased
WebUI.verifyEqual(afterCount, beforeCount + 1)

// Open Cart page
WebUI.click(findTestObject('CUSTOMER/HomePage/icon_cart'))

// Verify added product exists in cart
WebUI.verifyElementPresent(findTestObject('CUSTOMER/cart/cart_product_items'), 10)

// Close browser
WebUI.closeBrowser()

