import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

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

// Open Profile page
WebUI.click(findTestObject('CUSTOMER/HomePage/btn_menu_profile'))

// Logout
WebUI.click(findTestObject('CUSTOMER/HomePage/logout'))

// Wait for redirect
WebUI.delay(2)

// Verify logout success
String logoutUrl = WebUI.getUrl()

WebUI.verifyMatch(logoutUrl, '.*SuccessfullyLoggedout.*', true)

// Click browser Back button
WebUI.back()

// Wait page load
WebUI.delay(2)

// Get current URL after Back
String backUrl = WebUI.getUrl()

// Verify protected page is blocked
WebUI.verifyMatch(backUrl, '.*UnathorizedUser.*', true)

// Verify user cannot access profile menu anymore
WebUI.verifyElementNotPresent(findTestObject('CUSTOMER/HomePage/btn_menu_profile'), 5)

// Close browser
WebUI.closeBrowser()