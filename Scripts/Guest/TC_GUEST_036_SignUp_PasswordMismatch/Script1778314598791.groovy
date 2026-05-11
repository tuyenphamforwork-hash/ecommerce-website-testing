import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')

WebUI.maximizeWindow()

// STEP 1: Open homepage
WebUI.navigateToUrl(GlobalVariable.baseUrl)

// STEP 2: Open Login page
WebUI.click(findTestObject('Guest/Homepage/menu_login'))

// STEP 3: Open Signup page
WebUI.click(findTestObject('Guest/Page_Login(USER)/btn_Signup'))

// STEP 4: Verify Signup page displayed
WebUI.verifyElementPresent(findTestObject('Guest/Page_HCA E-Commerce/button_Register'), 10)

// STEP 5: Input signup information
WebUI.setText(findTestObject('Guest/Page_HCA E-Commerce/TextBox_FullName'), 'Customer1')

WebUI.setText(findTestObject('Guest/Page_HCA E-Commerce/TextBox_PhoneNumber'), '0965534564')

WebUI.setText(findTestObject('Guest/Page_HCA E-Commerce/TextBox_Email'), 'customer1@gmail.com')

WebUI.setText(findTestObject('Guest/Page_HCA E-Commerce/TextBox_Address'), '1234 Main St')

WebUI.setText(findTestObject('Guest/Page_HCA E-Commerce/TextBox_Password'), 'Customer1')

// Different confirm password
WebUI.setText(findTestObject('Guest/Page_HCA E-Commerce/TextBox_ConfirmPassword'), 'Customer')

// STEP 6: Click Register
WebUI.click(findTestObject('Guest/Page_HCA E-Commerce/button_Register'))

// STEP 7: Verify password mismatch message displayed
WebUI.verifyElementPresent(findTestObject('Guest/msg_SIGNUPPLEASE/Page_HCA E-Commerce/msg_PwnotMatch'), 5)

// STEP 9: Verify Register button still displayed
WebUI.verifyElementPresent(findTestObject('Guest/Page_HCA E-Commerce/button_Register'), 5)

WebUI.closeBrowser()

