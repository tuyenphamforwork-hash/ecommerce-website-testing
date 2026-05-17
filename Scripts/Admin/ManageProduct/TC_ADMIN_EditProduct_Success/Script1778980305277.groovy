import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration

// =========================
// STEP 1: OPEN BROWSER
// =========================
WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl(GlobalVariable.baseUrl)

WebUI.comment('START TC_ADMIN_EDIT_PRODUCT_SUCCESS')

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
// STEP 4: OPEN PRODUCT LIST
// =========================
WebUI.navigateToUrl(GlobalVariable.baseUrl + '/admin/post.php')

WebUI.waitForPageLoad(10)

// =========================
// STEP 5: CLICK EDIT PRODUCT
// =========================
WebUI.click(findTestObject('ADMIN/Page_Products/icon_edit'))

WebUI.waitForPageLoad(10)

// =========================
// STEP 6: UPDATE DATA
// =========================
String updatedProductName = 'Updated Shirt ' + System.currentTimeMillis()

WebUI.setText(findTestObject('ADMIN/EditPage/input_title_edit'), updatedProductName)

WebUI.setText(findTestObject('ADMIN/EditPage/input_price_edit'), '200')

WebUI.setText(findTestObject('ADMIN/EditPage/input_discount_edit'), '180')

WebUI.setText(findTestObject('ADMIN/EditPage/input_description_edit'), 'Updated product description')

WebUI.setText(findTestObject('ADMIN/EditPage/input_noofitem_edit'), '50')

WebUI.selectOptionByLabel(findTestObject('ADMIN/EditPage/select_catagory'), 'Shirt', false)

// =========================
// STEP 7: IMAGE (OPTIONAL - KEEP OR CHANGE)
// =========================
String imagePath = RunConfiguration.getProjectDir() + '/Include/images/img_whiteShirt.png'

File imgFile = new File(imagePath)

WebUI.comment('IMAGE PATH = ' + imagePath)

WebUI.comment('FILE EXISTS = ' + imgFile.exists())

assert imgFile.exists()

WebUI.uploadFile(findTestObject('ADMIN/AddProductPage/img_whiteShirt'), imagePath)

// =========================
// STEP 8: SUBMIT UPDATE
// =========================
WebUI.click(findTestObject('ADMIN/EditPage/btn_Update'))

WebUI.waitForPageLoad(10)

// =========================
// STEP 9: VERIFY UPDATE SUCCESS
// =========================
WebUI.navigateToUrl(GlobalVariable.baseUrl + '/admin/post.php')

WebUI.waitForPageLoad(10)

WebUI.verifyTextPresent(updatedProductName, false, FailureHandling.STOP_ON_FAILURE)

WebUI.comment('PRODUCT UPDATED SUCCESSFULLY: ' + updatedProductName)

// =========================
// STEP 10: CLOSE
// =========================
WebUI.closeBrowser()

