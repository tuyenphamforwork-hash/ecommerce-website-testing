import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.configuration.RunConfiguration

// =========================
// STEP 1: OPEN BROWSER
// =========================
WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl(GlobalVariable.baseUrl)

WebUI.comment("🚀 START TC_ADMIN_ADD_PRODUCT_EMPTY_PRODUCT_NAME")

// =========================
// STEP 2: LOGIN
// =========================
String adminEmail = 'admin1@gmail.com'
String adminPassword = 'Admin@1234567890'

WebUI.click(findTestObject('GUEST/Homepage/menu_login'))

WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'), adminEmail)
WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'), adminPassword)

WebUI.click(findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in'))

WebUI.verifyElementPresent(findTestObject('ADMIN/btn_menu_Profile'), 10, FailureHandling.STOP_ON_FAILURE)
WebUI.verifyTextPresent('HELLO, ADMIN', false, FailureHandling.STOP_ON_FAILURE)

// =========================
// STEP 3: OPEN ADMIN PANEL
// =========================
WebUI.click(findTestObject('ADMIN/btn_VisitAdminPanel'))

WebUI.verifyElementPresent(findTestObject('ADMIN/AdminPanelLogin/div_AdminPanelLogin'), 10)

WebUI.setText(findTestObject('ADMIN/AdminPanelLogin/input_Email'), adminEmail)
WebUI.setText(findTestObject('ADMIN/AdminPanelLogin/input_Password'), adminPassword)

WebUI.click(findTestObject('ADMIN/btn_SignIn'))
WebUI.waitForPageLoad(10)

// =========================
// STEP 4: OPEN ADD PRODUCT
// =========================
WebUI.click(findTestObject('ADMIN/Page_Products/btn_AddProducts'))
WebUI.waitForPageLoad(10)

// =========================
// STEP 5: TEST DATA (EMPTY PRODUCT NAME)
// =========================
String productName = ''   // ❌ EMPTY FIELD TEST

WebUI.setText(findTestObject('ADMIN/AddProductPage/input_product_name'), productName)
WebUI.setText(findTestObject('ADMIN/AddProductPage/input_product_price'), '150')
WebUI.setText(findTestObject('ADMIN/AddProductPage/input_discount'), '120')
WebUI.setText(findTestObject('ADMIN/AddProductPage/input_description'), 'A white shirt for men and women')
WebUI.setText(findTestObject('ADMIN/AddProductPage/input_NumberOfItems'), '20')

WebUI.selectOptionByLabel(findTestObject('ADMIN/AddProductPage/select_catagory'), 'Shirt', false)

// =========================
// STEP 6: IMAGE UPLOAD (VALID IMAGE)
// =========================
String imagePath = RunConfiguration.getProjectDir() + '/Include/images/img_whiteShirt.png'

File imgFile = new File(imagePath)
WebUI.comment("IMAGE PATH = " + imagePath)
WebUI.comment("FILE EXISTS = " + imgFile.exists())

assert imgFile.exists() : "❌ Image not found: " + imagePath

WebUI.uploadFile(findTestObject('ADMIN/AddProductPage/img_whiteShirt'), imagePath)

// =========================
// STEP 7: SUBMIT PRODUCT
// =========================
WebUI.click(findTestObject('ADMIN/AddProductPage/btn_Add'))
WebUI.waitForPageLoad(10)

// =========================
// STEP 8: VERIFY FORM NOT SUBMITTED
// =========================
boolean stillOnAddPage = WebUI.verifyElementPresent(
    findTestObject('ADMIN/AddProductPage/btn_Add'),
    5,
    FailureHandling.OPTIONAL
)

assert stillOnAddPage : "❌ Product should NOT be created when Product Name is empty"

WebUI.comment("✅ VALIDATION PASSED: Product Name empty prevented submission")

// =========================
// STEP 9: CLOSE
// =========================
WebUI.closeBrowser()