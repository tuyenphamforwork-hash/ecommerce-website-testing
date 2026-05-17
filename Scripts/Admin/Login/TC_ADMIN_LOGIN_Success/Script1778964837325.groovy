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

// =========================
// STEP 2: GO TO LOGIN PAGE
// =========================
WebUI.click(findTestObject('GUEST/Homepage/menu_login'))

// =========================
// STEP 3: ENTER ADMIN CREDENTIALS
// =========================
WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'), 'dev.shahfahad@gmail.com')

WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'), 'adminfahad')

// =========================
// STEP 4: SUBMIT LOGIN
// =========================
WebUI.click(findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in'))

// =========================
// STEP 5: VERIFY LOGIN SUCCESS (PRIMARY ASSERTION)
// =========================
// Stable check 1: profile page loaded (shared page for all users)
WebUI.verifyElementPresent(findTestObject('ADMIN/btn_menu_Profile'), 10, FailureHandling.STOP_ON_FAILURE)

// Stable check 2: correct user identity displayed
WebUI.verifyTextPresent('HELLO, ADMIN', false, FailureHandling.STOP_ON_FAILURE)

// =========================
// STEP 6: VERIFY ADMIN PRIVILEGE (CRITICAL)
// =========================
// This is the REAL differentiator between Admin vs Customer
WebUI.verifyElementPresent(findTestObject('ADMIN/btn_VisitAdminPanel'), 10, FailureHandling.STOP_ON_FAILURE)

// =========================
// STEP 7: VERIFY USER IS LOGGED IN (STABLE METHOD)
// =========================
// Instead of cookies or URL → verify logout/profile UI exists
WebUI.verifyElementPresent(findTestObject('ADMIN/btn_logout'), 10, FailureHandling.OPTIONAL)

// =========================
// STEP 8: CLEANUP
// =========================
WebUI.closeBrowser()

