import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

// Open browser
WebUI.openBrowser('')

WebUI.maximizeWindow()

// Navigate to homepage
WebUI.navigateToUrl(GlobalVariable.baseUrl)

// Open Login page
WebUI.click(findTestObject('GUEST/Homepage/menu_login'))

// Enter valid email
WebUI.setText(
	findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'),
	'customer1@gmail.com'
)

// Enter valid password
WebUI.setText(
	findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'),
	'Customer1'
)

// Click Sign In
WebUI.click(findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in'))

// Verify login success
WebUI.verifyElementPresent(
	findTestObject('CUSTOMER/HomePage/btn_menu_profile'),
	10
)

// Refresh page
WebUI.refresh()

// Verify session still active after refresh
WebUI.verifyElementPresent(
	findTestObject('CUSTOMER/HomePage/btn_menu_profile'),
	10
)

// Open profile page
WebUI.click(findTestObject('CUSTOMER/HomePage/btn_menu_profile'))

// Verify correct username displayed
WebUI.verifyTextPresent('HELLO, CUSTOMER1', false)

// Close browser
WebUI.closeBrowser()