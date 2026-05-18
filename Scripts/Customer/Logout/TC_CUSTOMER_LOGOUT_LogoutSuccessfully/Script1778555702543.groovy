import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

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

String currentUrl = WebUI.getUrl()

WebUI.verifyMatch(currentUrl, '.*index.php.*', true)

WebUI.verifyMatch(currentUrl, '.*SuccessfullyLoggedout.*', true)

WebUI.verifyElementNotPresent(findTestObject('CUSTOMER/HomePage/btn_menu_profile'), 5)

WebUI.navigateToUrl(GlobalVariable.baseUrl + '/profile.php')

String unauthorizedUrl = WebUI.getUrl()

WebUI.verifyMatch(unauthorizedUrl, '.*UnathorizedUser.*', true)

WebUI.closeBrowser()