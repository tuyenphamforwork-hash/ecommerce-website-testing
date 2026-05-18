import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl(GlobalVariable.baseUrl)

WebUI.click(findTestObject('GUEST/Homepage/menu_login'))

WebUI.click(findTestObject('GUEST/Page_Login(USER)/btn_Signup'))

WebUI.verifyElementPresent(findTestObject('GUEST/Page_HCA E-Commerce/button_Register'), 10)

WebUI.setText(findTestObject('GUEST/Page_HCA E-Commerce/TextBox_FullName'), 'Customer1')

WebUI.setText(findTestObject('GUEST/Page_HCA E-Commerce/TextBox_PhoneNumber'), '0965534564')

WebUI.setText(findTestObject('GUEST/Page_HCA E-Commerce/TextBox_Email'), 'customer1@gmail.com')

WebUI.setText(findTestObject('GUEST/Page_HCA E-Commerce/TextBox_Address'), '1234 Main St')

WebUI.setText(findTestObject('GUEST/Page_HCA E-Commerce/TextBox_Password'), 'Customer1')

WebUI.setText(findTestObject('GUEST/Page_HCA E-Commerce/TextBox_ConfirmPassword'), 'Customer')

WebUI.click(findTestObject('GUEST/Page_HCA E-Commerce/button_Register'))

WebUI.verifyElementPresent(findTestObject('GUEST/msg_SIGNUPPLEASE/Page_HCA E-Commerce/msg_PwnotMatch'), 5)

WebUI.verifyElementPresent(findTestObject('GUEST/Page_HCA E-Commerce/button_Register'), 5)

WebUI.closeBrowser()

