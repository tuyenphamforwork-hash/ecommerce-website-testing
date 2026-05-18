import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

import org.openqa.selenium.WebElement
import com.kms.katalon.core.model.FailureHandling

WebUI.openBrowser('')

WebUI.maximizeWindow()

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

WebUI.click(
	findTestObject('CUSTOMER/HomePage/icon_cart')
)

WebUI.delay(2)

while (true) {

	List<WebElement> removeButtons = WebUiCommonHelper.findWebElements(
		findTestObject('CUSTOMER/cart/btn_remove'),
		3
	)

	int itemCount = removeButtons.size()

	println("Current cart item count = " + itemCount)

	if (itemCount == 0) {
		break
	}

	WebUI.click(
		findTestObject('CUSTOMER/cart/btn_remove')
	)

	WebUI.waitForAlert(5)

	String alertText = WebUI.getAlertText()

	WebUI.verifyMatch(
		alertText,
		'Are you sure you want to remove this item from cart?',
		false
	)

	WebUI.acceptAlert()

	WebUI.delay(2)
}

boolean removeButtonExists = WebUI.verifyElementPresent(
	findTestObject('CUSTOMER/cart/btn_remove'),
	3,
	FailureHandling.OPTIONAL
)

WebUI.verifyEqual(removeButtonExists, false)

WebUI.verifyElementPresent(
	findTestObject('CUSTOMER/cart/lbl_empty_cart_message'),
	10
)

WebUI.verifyElementText(
	findTestObject('CUSTOMER/cart/lbl_empty_cart_message'),
	'No item available in cart'
)

WebUI.closeBrowser()