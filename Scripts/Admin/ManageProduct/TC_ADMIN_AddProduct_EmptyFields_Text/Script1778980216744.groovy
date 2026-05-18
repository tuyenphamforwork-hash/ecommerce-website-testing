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

WebUI.verifyElementPresent(findTestObject('ADMIN/btn_menu_Profile'), 10)
WebUI.verifyTextPresent('HELLO, ADMIN', false)

WebUI.click(findTestObject('ADMIN/btn_VisitAdminPanel'))

WebUI.setText(findTestObject('ADMIN/AdminPanelLogin/input_Email'), adminEmail)
WebUI.setText(findTestObject('ADMIN/AdminPanelLogin/input_Password'), adminPassword)

WebUI.click(findTestObject('ADMIN/btn_SignIn'))
WebUI.waitForPageLoad(10)

WebUI.click(findTestObject('ADMIN/Page_Products/btn_AddProducts'))
WebUI.waitForPageLoad(10)

WebUI.comment("Submitting empty form to trigger HTML5 validation")

WebUI.click(findTestObject('ADMIN/AddProductPage/btn_Add'))

boolean stillOnAddPage = WebUI.verifyElementPresent(
    findTestObject('ADMIN/AddProductPage/btn_Add'),
    3,
    FailureHandling.OPTIONAL
)

assert stillOnAddPage : "Form should NOT submit when fields are empty"

WebUI.comment("Expected HTML5 messages:")
WebUI.comment("- Text fields: Please fill out this field.")
WebUI.comment("- File upload: Please select a file.")

WebUI.closeBrowser()