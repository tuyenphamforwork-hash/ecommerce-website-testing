import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl(GlobalVariable.baseUrl)

// =========================
// STEP 2: LOGIN
// =========================
String adminEmail = 'admin1@gmail.com'

String adminPassword = 'Admin@1234567890'

WebUI.click(findTestObject('GUEST/Homepage/menu_login'))

WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'), adminEmail)

WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'), adminPassword)

WebUI.click(findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in'))

WebUI.verifyElementPresent(findTestObject('ADMIN/btn_menu_Profile'), 10)

WebUI.verifyTextPresent('HELLO, ADMIN', false)

// =========================
// STEP 3: OPEN ADMIN PANEL
// =========================
WebUI.click(findTestObject('ADMIN/btn_VisitAdminPanel'))

WebUI.setText(findTestObject('ADMIN/AdminPanelLogin/input_Email'), adminEmail)

WebUI.setText(findTestObject('ADMIN/AdminPanelLogin/input_Password'), adminPassword)

WebUI.click(findTestObject('ADMIN/btn_SignIn'))

WebUI.waitForPageLoad(10)

WebUI.click(findTestObject('ADMIN/Menu/btn_menu_Logout'))

WebUI.acceptAlert()

WebUI.waitForPageLoad(10)

WebUI.verifyMatch(WebUI.getUrl(), GlobalVariable.baseUrl + '/admin/login.php', false)

WebUI.closeBrowser()

