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

String sqlPayload = '\' OR \'1\'=\'1'

WebUI.setText(findTestObject('CUSTOMER/Profile/txtbox_name'), sqlPayload)

WebUI.setText(findTestObject('CUSTOMER/Profile/txtbox_email'), 'customer2@gmail.com')

WebUI.click(findTestObject('CUSTOMER/Profile/btn_save_name_email'))

WebUI.refresh()

WebUI.delay(2)

String updatedName = WebUI.getAttribute(
	findTestObject('CUSTOMER/Profile/txtbox_name'),
	'value'
)

WebUI.comment('Stored Name Value: ' + updatedName)

WebUI.verifyEqual(updatedName, 'Customer2')

WebUI.closeBrowser()