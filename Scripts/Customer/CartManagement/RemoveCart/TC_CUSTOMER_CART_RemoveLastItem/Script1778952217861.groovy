import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

import org.openqa.selenium.WebElement
import com.kms.katalon.core.model.FailureHandling

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

// Verify login successful
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
// Remove all items from cart
// ========================================

while (true) {

	List<WebElement> removeButtons = WebUiCommonHelper.findWebElements(
		findTestObject('CUSTOMER/cart/btn_remove'),
		3
	)

	int itemCount = removeButtons.size()

	println("Current cart item count = " + itemCount)

	// Stop loop if cart is empty
	if (itemCount == 0) {
		break
	}

	// Click first Remove button
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

	// Accept alert
	WebUI.acceptAlert()

	// Wait processing completed
	WebUI.delay(2)
}

// ========================================
// Verify cart becomes empty
// ========================================

boolean removeButtonExists = WebUI.verifyElementPresent(
	findTestObject('CUSTOMER/cart/btn_remove'),
	3,
	FailureHandling.OPTIONAL
)

WebUI.verifyEqual(removeButtonExists, false)

// ========================================
// Verify empty cart message displayed
// ========================================

WebUI.verifyElementPresent(
	findTestObject('CUSTOMER/cart/lbl_empty_cart_message'),
	10
)

WebUI.verifyElementText(
	findTestObject('CUSTOMER/cart/lbl_empty_cart_message'),
	'No item available in cart'
)

// Close browser
WebUI.closeBrowser()