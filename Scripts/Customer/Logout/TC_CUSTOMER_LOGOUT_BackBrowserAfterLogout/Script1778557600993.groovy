import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl(GlobalVariable.baseUrl + '/login.php')

WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'), 'customer2@gmail.com')

WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'), 'Customer@123456')

WebUI.click(findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in'))

WebUI.verifyElementPresent(findTestObject('CUSTOMER/HomePage/btn_menu_profile'), 10)

WebUI.click(findTestObject('CUSTOMER/HomePage/btn_menu_profile'))

WebUI.click(findTestObject('CUSTOMER/HomePage/logout'))

WebUI.delay(2)

String logoutUrl = WebUI.getUrl()

WebUI.verifyMatch(logoutUrl, '.*SuccessfullyLoggedout.*', true)

WebUI.back()

WebUI.delay(2)

String backUrl = WebUI.getUrl()

WebUI.verifyMatch(backUrl, '.*UnathorizedUser.*', true)

WebUI.verifyElementNotPresent(findTestObject('CUSTOMER/HomePage/btn_menu_profile'), 5)

WebUI.closeBrowser()