import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
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

// Open browser
WebUI.openBrowser('')

WebUI.maximizeWindow()

// Navigate to Login page
WebUI.navigateToUrl(GlobalVariable.baseUrl + '/login.php')

// Login
WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'), 'customer2@gmail.com')

WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'), 'Customer@123456')

WebUI.click(findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in'))

// Verify login success
WebUI.verifyElementPresent(findTestObject('CUSTOMER/HomePage/btn_menu_profile'), 10)

// Open Home page
WebUI.click(findTestObject('CUSTOMER/HomePage/btn_menu_Home'))

// Open Product Detail page
WebUI.click(findTestObject('CUSTOMER/product_detail/productname_jacket'))

// Verify Add To Cart button displayed
WebUI.verifyElementPresent(findTestObject('CUSTOMER/product_detail/btn_AddToCart'), 10)

// ========================================
// Add product to cart
// ========================================
WebUI.click(findTestObject('CUSTOMER/product_detail/btn_AddToCart'))

WebUI.delay(2)

// ========================================
// Open Cart page
// ========================================
WebUI.click(findTestObject('CUSTOMER/HomePage/icon_cart'))

WebUI.delay(2)

// ========================================
// Increase quantity until reaching 10
// ========================================
while (true) {
    String quantityText = WebUI.getAttribute(findTestObject('CUSTOMER/cart/input_quantity'), 'value')

    int currentQuantity = Integer.parseInt(quantityText)

    // Stop when quantity = 10
    if (currentQuantity >= 10) {
        break
    }
    
    // Increase quantity
    WebUI.click(findTestObject('CUSTOMER/cart/btn_increase_quantity'))

    WebUI.delay(1)
}

// ========================================
// Verify current quantity = 10
// ========================================
String maxQuantityText = WebUI.getAttribute(findTestObject('CUSTOMER/cart/input_quantity'), 'value')

int maxQuantity = Integer.parseInt(maxQuantityText)

WebUI.verifyEqual(maxQuantity, 10)

// ========================================
// Try increasing from 10 -> 11
// ========================================
WebUI.click(findTestObject('CUSTOMER/cart/btn_increase_quantity'))

WebUI.delay(1)

// ========================================
// Verify alert displayed
// ========================================
String alertText = WebUI.getAlertText()

WebUI.verifyMatch(alertText, '.*Error updating quantity.*', true)

// Accept alert
WebUI.acceptAlert()

// ========================================
// Verify quantity remains 10
// ========================================
String finalQuantityText = WebUI.getAttribute(findTestObject('CUSTOMER/cart/input_quantity'), 'value')

int finalQuantity = Integer.parseInt(finalQuantityText)

WebUI.verifyEqual(finalQuantity, 10)

WebUI.click(findTestObject('CUSTOMER/cart/btn_decrease_quantity'))

// Close browser
WebUI.closeBrowser()

