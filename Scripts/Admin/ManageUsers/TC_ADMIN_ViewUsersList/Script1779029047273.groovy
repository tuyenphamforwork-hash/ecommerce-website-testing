import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.model.FailureHandling as FailureHandling

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

WebUI.click(findTestObject('ADMIN/btn_VisitAdminPanel'))

WebUI.setText(findTestObject('ADMIN/AdminPanelLogin/input_Email'), adminEmail)

WebUI.setText(findTestObject('ADMIN/AdminPanelLogin/input_Password'), adminPassword)

WebUI.click(findTestObject('ADMIN/btn_SignIn'))

WebUI.waitForPageLoad(10)

WebUI.click(findTestObject('ADMIN/Menu/btn_menu_Users'))

WebUI.waitForPageLoad(10)

WebUI.verifyElementPresent(findTestObject('ADMIN/UsersPage/userName'), 10)

WebUI.verifyElementPresent(findTestObject('ADMIN/UsersPage/userPhone'), 10)

WebUI.verifyElementPresent(findTestObject('ADMIN/UsersPage/userAddress'), 10)

WebUI.verifyElementPresent(findTestObject('ADMIN/UsersPage/userRole'), 10)

WebUI.closeBrowser()

