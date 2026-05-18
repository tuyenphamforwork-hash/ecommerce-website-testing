import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

import org.openqa.selenium.WebElement

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

List<WebElement> beforeItems = WebUiCommonHelper.findWebElements(
	findTestObject('CUSTOMER/cart/btn_remove'),
	10
)

int beforeCount = beforeItems.size()

println("Before remove item count = " + beforeCount)

WebUI.verifyGreaterThan(beforeCount, 0)

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

WebUI.delay(3)

List<WebElement> afterItems = WebUiCommonHelper.findWebElements(
	findTestObject('CUSTOMER/cart/btn_remove'),
	10
)

int afterCount = afterItems.size()

println("After remove item count = " + afterCount)

WebUI.verifyEqual(
	afterCount,
	beforeCount - 1
)

WebUI.closeBrowser()