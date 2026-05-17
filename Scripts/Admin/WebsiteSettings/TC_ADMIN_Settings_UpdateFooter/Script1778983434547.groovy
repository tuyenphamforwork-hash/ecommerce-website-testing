import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl(GlobalVariable.baseUrl)

String adminEmail = 'admin1@gmail.com'

String adminPassword = 'Admin@1234567890'

WebUI.click(findTestObject('GUEST/Homepage/menu_login'))

WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'), adminEmail)

WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'), adminPassword)

WebUI.click(findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in'))

WebUI.verifyElementPresent(findTestObject('ADMIN/btn_menu_Profile'), 10, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyTextPresent('HELLO, ADMIN', false, FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('ADMIN/btn_VisitAdminPanel'))

WebUI.verifyElementPresent(findTestObject('ADMIN/AdminPanelLogin/div_AdminPanelLogin'), 10)

WebUI.setText(findTestObject('ADMIN/AdminPanelLogin/input_Email'), adminEmail)

WebUI.setText(findTestObject('ADMIN/AdminPanelLogin/input_Password'), adminPassword)

WebUI.click(findTestObject('ADMIN/btn_SignIn'))

WebUI.waitForPageLoad(10)

WebUI.click(findTestObject('ADMIN/Menu/btn_menu_Setting'))

WebUI.waitForPageLoad(10)

String newFooter = 'New HCA E-Commerce'

WebUI.setText(findTestObject('ADMIN/SettingsPage/input_footer_description'), newFooter)

WebUI.click(findTestObject('ADMIN/SettingsPage/btn_Update'))

WebUI.waitForPageLoad(10)

WebUI.refresh()

WebUI.waitForPageLoad(10)

WebUI.verifyElementPresent(findTestObject('ADMIN/SettingsPage/input_Footer_updated'), 0)

WebUI.closeBrowser()

