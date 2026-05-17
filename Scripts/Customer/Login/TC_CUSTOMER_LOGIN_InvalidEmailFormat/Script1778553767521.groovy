import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')
WebUI.maximizeWindow()

WebUI.navigateToUrl(GlobalVariable.baseUrl + '/login.php')

// nhập email sai format
WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'), 'customer1')
WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'), '123456')

// click sign in để trigger validation
WebUI.click(findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in'))

// lấy validation message từ browser
String validationMessage = WebUI.getAttribute(
    findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'),
    'validationMessage'
)

println('Validation message: ' + validationMessage)

// verify browser validation
assert validationMessage.contains("include an '@'")
assert validationMessage.contains('customer1')

WebUI.closeBrowser()