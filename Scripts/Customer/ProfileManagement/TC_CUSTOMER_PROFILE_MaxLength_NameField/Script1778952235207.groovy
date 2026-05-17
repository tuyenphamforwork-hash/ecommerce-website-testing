import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

// =============================================
// TC_CUSTOMER_PROFILE_MaxLength_NameField
// Verify maximum character limit for Name field
// Expected: Validation shown OR input limited
// Current behavior: System allows oversized input
// -> Test should FAIL for bug reporting
// =============================================

// Open browser
WebUI.openBrowser('')

WebUI.maximizeWindow()

// Navigate to Login page
WebUI.navigateToUrl(GlobalVariable.baseUrl + '/login.php')

// Login with valid customer account
WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'), 'customer2@gmail.com')

WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'), 'Customer@123456')

WebUI.click(findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in'))

// Verify login successful
WebUI.verifyElementPresent(findTestObject('CUSTOMER/HomePage/btn_menu_profile'), 10)

// Open Profile page
WebUI.click(findTestObject('CUSTOMER/HomePage/btn_menu_profile'))

// Click Edit Profile
WebUI.click(findTestObject('CUSTOMER/Profile/btn_edit_name_email'))

// Generate very long name (300 chars)
String longName = 'A' * 300

WebUI.comment('Input Length: ' + longName.length())

// Enter long name
WebUI.setText(findTestObject('CUSTOMER/Profile/txtbox_name'), longName)

WebUI.setText(findTestObject('CUSTOMER/Profile/txtbox_email'), 'customer2@gmail.com')

// Save profile
WebUI.click(findTestObject('CUSTOMER/Profile/btn_save_name_email'))

WebUI.delay(2)

WebUI.refresh()

WebUI.delay(2)

// Get stored/displayed name
String updatedName = WebUI.getText(findTestObject('CUSTOMER/Profile/lbl_profile_name'))

WebUI.comment('Displayed Name Length: ' + updatedName.length())


// Verify displayed name length does NOT exceed 255
WebUI.verifyLessThanOrEqual(updatedName.length(), 255)

// Optional validation message check
WebUI.verifyTextPresent(
	'Name cannot exceed 255 characters',
	false
)

// Close browser
WebUI.closeBrowser()