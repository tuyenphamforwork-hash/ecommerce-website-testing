import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

import org.openqa.selenium.WebElement

// Open browser
WebUI.openBrowser('')

WebUI.maximizeWindow()

// Navigate to Login page
WebUI.navigateToUrl(GlobalVariable.baseUrl + '/login.php')

// ========================================
// Login with valid account
// ========================================

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

// Verify login success
WebUI.verifyElementPresent(
	findTestObject('CUSTOMER/HomePage/btn_menu_profile'),
	10
)

// ========================================
// Open Cart page
// ========================================

WebUI.click(
	findTestObject('CUSTOMER/HomePage/icon_cart')
)

WebUI.delay(2)

// ========================================
// Count items before remove
// Count based on Remove buttons
// ========================================

List<WebElement> beforeItems = WebUiCommonHelper.findWebElements(
	findTestObject('CUSTOMER/cart/btn_remove'),
	10
)

int beforeCount = beforeItems.size()

println("Before remove item count = " + beforeCount)

// Verify cart has at least 1 item
WebUI.verifyGreaterThan(beforeCount, 0)

// ========================================
// Click Remove button
// ========================================

WebUI.click(
	findTestObject('CUSTOMER/cart/btn_remove')
)

// Wait alert displayed
WebUI.waitForAlert(5)

// Verify alert message
String alertText = WebUI.getAlertText()

WebUI.verifyMatch(
	alertText,
	'Are you sure you want to remove this item from cart?',
	false
)

// Click OK
WebUI.acceptAlert()

// Wait system update
WebUI.delay(3)

// ========================================
// Count items after remove
// ========================================

List<WebElement> afterItems = WebUiCommonHelper.findWebElements(
	findTestObject('CUSTOMER/cart/btn_remove'),
	10
)

int afterCount = afterItems.size()

println("After remove item count = " + afterCount)

// ========================================
// Verify item count decreased by 1
// ========================================

WebUI.verifyEqual(
	afterCount,
	beforeCount - 1
)

// Close browser
WebUI.closeBrowser()