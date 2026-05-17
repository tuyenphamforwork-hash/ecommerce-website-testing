import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.WebElement

// Open browser
WebUI.openBrowser('')
WebUI.maximizeWindow()

try {

	// ========================================
	// Login
	// ========================================
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

	// ========================================
	// Open Cart
	// ========================================
	WebUI.click(
		findTestObject('CUSTOMER/HomePage/icon_cart')
	)

	WebUI.delay(2)

	// ========================================
	// Check current cart state
	// ========================================
	List<WebElement> removeButtons = WebUiCommonHelper.findWebElements(
		findTestObject('CUSTOMER/cart/btn_remove'),
		3
	)

	if (removeButtons.size() == 0) {
		println('Cart already empty -> continue test')
	} else {
		println('Cart has items -> removing all items')

		while (true) {

			removeButtons = WebUiCommonHelper.findWebElements(
				findTestObject('CUSTOMER/cart/btn_remove'),
				3
			)

			int itemCount = removeButtons.size()
			println('Current item count = ' + itemCount)

			// stop when cart empty
			if (itemCount == 0) {
				break
			}

			// click first remove button
			WebUI.click(
				findTestObject('CUSTOMER/cart/btn_remove')
			)

			// wait alert
			WebUI.waitForAlert(5)

			// verify alert text
			String alertText = WebUI.getAlertText()

			WebUI.verifyMatch(
				alertText,
				'Are you sure you want to remove this item from cart?',
				false
			)

			// accept alert
			WebUI.acceptAlert()

			// wait page update
			WebUI.delay(2)
		}
	}

	// ========================================
	// Verify cart empty
	// ========================================
	WebUI.verifyElementPresent(
		findTestObject('CUSTOMER/cart/lbl_empty_cart_message'),
		10
	)

	WebUI.verifyElementText(
		findTestObject('CUSTOMER/cart/lbl_empty_cart_message'),
		'No item available in cart'
	)

	// ========================================
	// Verify checkout button not displayed
	// ========================================
	boolean checkoutBtnNotPresent = WebUI.verifyElementNotPresent(
		findTestObject('CUSTOMER/cart/btn_ProceedToCheckOut'),
		5,
		FailureHandling.OPTIONAL
	)

	WebUI.verifyEqual(checkoutBtnNotPresent, true)

	println('Empty cart verification passed')

}
finally {
	WebUI.closeBrowser()
}