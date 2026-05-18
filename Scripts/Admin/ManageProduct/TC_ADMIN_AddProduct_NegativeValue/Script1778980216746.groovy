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

WebUI.verifyElementPresent(findTestObject('ADMIN/btn_menu_Profile'), 10, FailureHandling.STOP_ON_FAILURE)
WebUI.verifyTextPresent('HELLO, ADMIN', false, FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('ADMIN/btn_VisitAdminPanel'))

WebUI.verifyElementPresent(findTestObject('ADMIN/AdminPanelLogin/div_AdminPanelLogin'), 10)

WebUI.setText(findTestObject('ADMIN/AdminPanelLogin/input_Email'), adminEmail)
WebUI.setText(findTestObject('ADMIN/AdminPanelLogin/input_Password'), adminPassword)

WebUI.click(findTestObject('ADMIN/btn_SignIn'))
WebUI.waitForPageLoad(10)

WebUI.click(findTestObject('ADMIN/Page_Products/btn_AddProducts'))
WebUI.waitForPageLoad(10)

String productName = 'Invalid Numeric Product ' + System.currentTimeMillis()

WebUI.setText(findTestObject('ADMIN/AddProductPage/input_product_name'), productName)

WebUI.setText(findTestObject('ADMIN/AddProductPage/input_product_price'), '-100')
WebUI.setText(findTestObject('ADMIN/AddProductPage/input_discount'), '120')
WebUI.setText(findTestObject('ADMIN/AddProductPage/input_NumberOfItems'), '-5')

WebUI.setText(findTestObject('ADMIN/AddProductPage/input_description'), 'Testing invalid numeric input')

WebUI.selectOptionByLabel(findTestObject('ADMIN/AddProductPage/select_catagory'), 'Shirt', false)

String imagePath = RunConfiguration.getProjectDir() + '/Include/images/img_whiteShirt.png'

File imgFile = new File(imagePath)
WebUI.comment("IMAGE PATH = " + imagePath)
WebUI.comment("FILE EXISTS = " + imgFile.exists())

assert imgFile.exists() : "Image not found: " + imagePath

WebUI.uploadFile(findTestObject('ADMIN/AddProductPage/img_whiteShirt'), imagePath)

WebUI.click(findTestObject('ADMIN/AddProductPage/btn_Add'))
WebUI.waitForPageLoad(10)

boolean stillOnAddPage = WebUI.verifyElementPresent(
    findTestObject('ADMIN/AddProductPage/btn_Add'),
    5,
    FailureHandling.OPTIONAL
)

WebUI.navigateToUrl(GlobalVariable.baseUrl + '/admin/post.php')
WebUI.waitForPageLoad(10)

boolean productExists = WebUI.verifyTextPresent(
    productName,
    false,
    FailureHandling.OPTIONAL
)

assert stillOnAddPage == true : "Form should NOT submit with negative values"
assert productExists == false : "Product should NOT be created with invalid numeric input"

WebUI.comment("VALIDATION PASSED: Negative price/quantity blocked")

WebUI.closeBrowser()