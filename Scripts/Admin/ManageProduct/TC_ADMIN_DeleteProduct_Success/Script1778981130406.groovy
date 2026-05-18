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

WebUI.navigateToUrl(GlobalVariable.baseUrl + '/admin/post.php')

WebUI.waitForPageLoad(10)

String productName = WebUI.getText(findTestObject('ADMIN/Page_Products/product_title'))

WebUI.comment('Product to delete: ' + productName)

WebUI.click(findTestObject('ADMIN/Page_Products/btn_delete'))

WebUI.delay(2)

WebUI.acceptAlert(FailureHandling.OPTIONAL)

WebUI.refresh()

WebUI.waitForPageLoad(10)

boolean isDeleted = WebUI.verifyTextNotPresent(productName, false, FailureHandling.OPTIONAL)

assert isDeleted == true

WebUI.comment('PRODUCT DELETED SUCCESSFULLY: ' + productName)

WebUI.closeBrowser()

