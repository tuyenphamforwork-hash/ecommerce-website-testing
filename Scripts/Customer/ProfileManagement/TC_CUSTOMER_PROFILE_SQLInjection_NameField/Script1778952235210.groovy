import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

// Open browser
WebUI.openBrowser('')

WebUI.maximizeWindow()

// Navigate to Login page
WebUI.navigateToUrl(GlobalVariable.baseUrl + '/login.php')

// Login
WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'), 'customer2@gmail.com')

WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'), 'Customer@123456')

WebUI.click(findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in'))

// Verify login successful
WebUI.verifyElementPresent(findTestObject('CUSTOMER/HomePage/btn_menu_profile'), 10)

// Open profile page
WebUI.click(findTestObject('CUSTOMER/HomePage/btn_menu_profile'))

// Click edit profile
WebUI.click(findTestObject('CUSTOMER/Profile/btn_edit_name_email'))

// SQL Injection payload
String sqlPayload = '\' OR \'1\'=\'1'

// Input payload
WebUI.setText(findTestObject('CUSTOMER/Profile/txtbox_name'), sqlPayload)

WebUI.setText(findTestObject('CUSTOMER/Profile/txtbox_email'), 'customer2@gmail.com')

// Save profile
WebUI.click(findTestObject('CUSTOMER/Profile/btn_save_name_email'))

WebUI.refresh()

WebUI.delay(2)

// Get stored value
String updatedName = WebUI.getAttribute(
	findTestObject('CUSTOMER/Profile/txtbox_name'),
	'value'
)

WebUI.comment('Stored Name Value: ' + updatedName)

// EXPECTED:
// System should reject payload completely
// Name should NOT become unexpected value like "1"

// FAIL if system changes payload unexpectedly
WebUI.verifyEqual(updatedName, 'Customer2')

// Close browser
WebUI.closeBrowser()