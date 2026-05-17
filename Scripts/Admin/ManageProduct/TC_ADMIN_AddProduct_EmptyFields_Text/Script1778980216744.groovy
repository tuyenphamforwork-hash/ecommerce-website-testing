import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import internal.GlobalVariable as GlobalVariable

// =========================
// STEP 1: OPEN BROWSER
// =========================
WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl(GlobalVariable.baseUrl)

WebUI.comment("🚀 START TC_ADMIN_ADD_PRODUCT_EMPTY_FIELDS")

// =========================
// STEP 2: LOGIN ADMIN
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

// =========================
// STEP 4: OPEN ADD PRODUCT PAGE
// =========================
WebUI.click(findTestObject('ADMIN/Page_Products/btn_AddProducts'))
WebUI.waitForPageLoad(10)

// =========================
// STEP 5: LEAVE ALL FIELDS EMPTY
// =========================
WebUI.comment("⚠️ Submitting empty form to trigger HTML5 validation")

// =========================
// STEP 6: SUBMIT FORM
// =========================
WebUI.click(findTestObject('ADMIN/AddProductPage/btn_Add'))

// =========================
// STEP 7: VERIFY HTML5 VALIDATION
// =========================

// IMPORTANT: HTML5 validation appears in browser tooltip → cannot always detect directly
// So we check that form DID NOT submit

boolean stillOnAddPage = WebUI.verifyElementPresent(
    findTestObject('ADMIN/AddProductPage/btn_Add'),
    3,
    FailureHandling.OPTIONAL
)

assert stillOnAddPage : "❌ Form should NOT submit when fields are empty"

// =========================
// STEP 8: OPTIONAL CHECK (MANUAL DEBUG INFO)
// =========================
WebUI.comment("✅ Expected HTML5 messages:")
WebUI.comment("- Text fields: Please fill out this field.")
WebUI.comment("- File upload: Please select a file.")

// =========================
// STEP 9: CLOSE
// =========================
WebUI.closeBrowser()