import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl(GlobalVariable.baseUrl + '/login.php')

WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'), 'customer2@gmail.com')

WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'), 'Customer@123456')

WebUI.click(findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in'))

WebUI.verifyElementPresent(findTestObject('CUSTOMER/HomePage/btn_menu_profile'), 10)

WebUI.navigateToUrl(GlobalVariable.baseUrl + '/profile.php')

WebDriver driver = DriverFactory.getWebDriver()

JavascriptExecutor js = (JavascriptExecutor) driver

js.executeScript('window.open();')

WebUI.switchToWindowIndex(1)

WebUI.navigateToUrl(GlobalVariable.baseUrl + '/profile.php')

String tabBUrl = WebUI.getUrl()

WebUI.verifyMatch(tabBUrl.contains('profile.php').toString(), 'true', false)

WebUI.switchToWindowIndex(0)

WebUI.click(findTestObject('CUSTOMER/HomePage/btn_menu_profile'))

WebUI.click(findTestObject('CUSTOMER/HomePage/logout'))

WebUI.delay(2)

String logoutUrl = WebUI.getUrl()

WebUI.verifyMatch(logoutUrl.contains('SuccessfullyLoggedout').toString(), 'true', false)

WebUI.switchToWindowIndex(1)

WebUI.refresh()

WebUI.delay(2)

String currentUrl = WebUI.getUrl()

WebUI.verifyMatch(currentUrl.contains('UnathorizedUser').toString(), 'true', false)

WebUI.closeBrowser()