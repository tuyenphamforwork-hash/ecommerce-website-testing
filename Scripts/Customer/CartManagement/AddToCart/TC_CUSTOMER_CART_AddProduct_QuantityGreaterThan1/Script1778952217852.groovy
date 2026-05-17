import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

// =====================================================
// TC_CUSTOMER_CART_AddProduct_QuantityGreaterThan1
// Verify quantity after add = quantity before + input quantity
// =====================================================

int addQuantity = 3
int quantityBefore = 0

WebUI.openBrowser('')
WebUI.maximizeWindow()

// Login
WebUI.navigateToUrl(GlobalVariable.baseUrl + '/login.php')

WebUI.setText(
    findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'),
    'customer2@gmail.com'
)

WebUI.setText(
    findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'),
    'Customer@123456'
)

WebUI.click(findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in'))

WebUI.verifyElementPresent(
    findTestObject('CUSTOMER/HomePage/btn_menu_profile'),
    10
)

// ========================================
// Check quantity before add
// ========================================
WebUI.click(findTestObject('CUSTOMER/HomePage/icon_cart'))

boolean hasProduct = WebUI.verifyElementPresent(
    findTestObject('CUSTOMER/cart/input_quantity'),
    3,
    com.kms.katalon.core.model.FailureHandling.OPTIONAL
)

if (hasProduct) {
    String oldQty = WebUI.getAttribute(
        findTestObject('CUSTOMER/cart/input_quantity'),
        'value'
    )
    quantityBefore = Integer.parseInt(oldQty)
} else {
    quantityBefore = 0
}

println("Quantity before add: " + quantityBefore)

// Back home
WebUI.click(findTestObject('CUSTOMER/HomePage/btn_menu_Home'))

// ========================================
// Open product detail
// ========================================
WebUI.click(findTestObject('CUSTOMER/product_detail/productname_jacket'))

WebUI.verifyElementPresent(
    findTestObject('CUSTOMER/product_detail/btn_AddToCart'),
    10
)

// Input quantity
WebUI.clearText(findTestObject('CUSTOMER/product_detail/txtbox_quantity'))
WebUI.setText(
    findTestObject('CUSTOMER/product_detail/txtbox_quantity'),
    addQuantity.toString()
)

// Add to cart
WebUI.click(findTestObject('CUSTOMER/product_detail/btn_AddToCart'))
WebUI.delay(2)

// ========================================
// Verify quantity after add
// ========================================
WebUI.click(findTestObject('CUSTOMER/HomePage/icon_cart'))
WebUI.delay(2)

String quantityValue = WebUI.getAttribute(
    findTestObject('CUSTOMER/cart/input_quantity'),
    'value'
)

int quantityAfter = Integer.parseInt(quantityValue)
int expectedQuantity = quantityBefore + addQuantity

println("Expected quantity: " + expectedQuantity)
println("Actual quantity: " + quantityAfter)

WebUI.verifyEqual(quantityAfter, expectedQuantity)

// Verify product exists
WebUI.verifyElementPresent(
    findTestObject('CUSTOMER/cart/lbl_product_name'),
    10
)

WebUI.closeBrowser()