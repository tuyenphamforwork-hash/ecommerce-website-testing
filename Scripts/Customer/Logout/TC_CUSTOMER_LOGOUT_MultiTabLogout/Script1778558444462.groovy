import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver

// Open browser
WebUI.openBrowser('')

WebUI.maximizeWindow()

// Navigate to Login page
WebUI.navigateToUrl(GlobalVariable.baseUrl + '/login.php')

// Login with valid account
WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'), 'customer2@gmail.com')

WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'), 'Customer@123456')

WebUI.click(findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in'))

// Verify login successful
WebUI.verifyElementPresent(findTestObject('CUSTOMER/HomePage/btn_menu_profile'), 10)

// Open Profile page in Tab A
WebUI.navigateToUrl(GlobalVariable.baseUrl + '/profile.php')

// Get WebDriver
WebDriver driver = DriverFactory.getWebDriver()

JavascriptExecutor js = (JavascriptExecutor) driver

// Open new tab (Tab B)
js.executeScript('window.open();')

// Switch to Tab B
WebUI.switchToWindowIndex(1)

// Navigate to Profile page in Tab B
WebUI.navigateToUrl(GlobalVariable.baseUrl + '/profile.php')

// Verify Profile page accessible in Tab B
String tabBUrl = WebUI.getUrl()

WebUI.verifyMatch(tabBUrl.contains('profile.php').toString(), 'true', false)

// Switch back to Tab A
WebUI.switchToWindowIndex(0)

// Open profile menu
WebUI.click(findTestObject('CUSTOMER/HomePage/btn_menu_profile'))

// Click Logout
WebUI.click(findTestObject('CUSTOMER/HomePage/logout'))

// Wait for redirect
WebUI.delay(2)

// Verify logout success
String logoutUrl = WebUI.getUrl()

WebUI.verifyMatch(logoutUrl.contains('SuccessfullyLoggedout').toString(), 'true', false)

// Switch to Tab B
WebUI.switchToWindowIndex(1)

// Refresh Tab B
WebUI.refresh()

// Wait for page reload
WebUI.delay(2)

// Get current URL after refresh
String currentUrl = WebUI.getUrl()

// Verify session invalidated in Tab B
WebUI.verifyMatch(currentUrl.contains('UnathorizedUser').toString(), 'true', false)

// Close browser
WebUI.closeBrowser()