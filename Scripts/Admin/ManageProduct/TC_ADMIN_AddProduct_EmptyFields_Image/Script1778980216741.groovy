import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.configuration.RunConfiguration

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

String productName = 'White Shirt ' + System.currentTimeMillis()

WebUI.setText(findTestObject('ADMIN/AddProductPage/input_product_name'), productName)
WebUI.setText(findTestObject('ADMIN/AddProductPage/input_product_price'), '150')
WebUI.setText(findTestObject('ADMIN/AddProductPage/input_discount'), '120')
WebUI.setText(findTestObject('ADMIN/AddProductPage/input_description'), 'A white shirt for men and women')
WebUI.setText(findTestObject('ADMIN/AddProductPage/input_NumberOfItems'), '20')

WebUI.selectOptionByLabel(findTestObject('ADMIN/AddProductPage/select_catagory'), 'Shirt', false)

WebUI.comment("IMAGE NOT UPLOADED FOR VALIDATION TEST")

WebUI.click(findTestObject('ADMIN/AddProductPage/btn_Add'))
WebUI.waitForPageLoad(10)

boolean stillOnAddPage = WebUI.verifyElementPresent(
    findTestObject('ADMIN/AddProductPage/btn_Add'),
    5,
    FailureHandling.OPTIONAL
)

assert stillOnAddPage : "Product should NOT be created when image is empty"

WebUI.comment("VALIDATION PASSED: Image required field blocked submission")

WebUI.closeBrowser()