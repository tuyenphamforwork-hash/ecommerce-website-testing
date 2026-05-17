import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl(GlobalVariable.baseUrl + '/login.php')

WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'), 'customer1')

WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'), 'Customer1')

WebUI.click(findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in'))

String validationMessage = WebUI.getAttribute(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'), 'validationMessage')

println('Validation message: ' + validationMessage)

assert validationMessage.contains('include an \'@\'')

assert validationMessage.contains('customer1')

WebUI.closeBrowser()

