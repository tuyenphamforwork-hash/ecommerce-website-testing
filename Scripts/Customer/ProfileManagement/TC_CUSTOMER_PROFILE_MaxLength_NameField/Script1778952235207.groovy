import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl(GlobalVariable.baseUrl + '/login.php')

WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'), 'customer2@gmail.com')

WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'), 'Customer@123456')

WebUI.click(findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in'))

WebUI.verifyElementPresent(findTestObject('CUSTOMER/HomePage/btn_menu_profile'), 10)

WebUI.click(findTestObject('CUSTOMER/HomePage/btn_menu_profile'))

WebUI.click(findTestObject('CUSTOMER/Profile/btn_edit_name_email'))

String longName = 'A' * 300

WebUI.comment('Input Length: ' + longName.length())

WebUI.setText(findTestObject('CUSTOMER/Profile/txtbox_name'), longName)

WebUI.setText(findTestObject('CUSTOMER/Profile/txtbox_email'), 'customer2@gmail.com')

WebUI.click(findTestObject('CUSTOMER/Profile/btn_save_name_email'))

WebUI.delay(2)

WebUI.refresh()

WebUI.delay(2)

String updatedName = WebUI.getText(findTestObject('CUSTOMER/Profile/lbl_profile_name'))

WebUI.comment('Displayed Name Length: ' + updatedName.length())

WebUI.verifyLessThanOrEqual(updatedName.length(), 255)

WebUI.verifyTextPresent(
	'Name cannot exceed 255 characters',
	false
)

WebUI.closeBrowser()