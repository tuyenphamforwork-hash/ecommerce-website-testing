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

WebUI.click(findTestObject('CUSTOMER/HomePage/btn_menu_Home'))

WebUI.click(findTestObject('CUSTOMER/product_detail/productname_jacket'))

WebUI.click(findTestObject('CUSTOMER/product_detail/btn_buy'))

WebUI.verifyElementPresent(findTestObject('CUSTOMER/Checkout_Page/btn_ProceedToPay'), 10)

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_firstName'), '')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_lastName'), 'customer')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_houseNumber'), '03')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_street'), 'Quang Trung')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_townOrCity'), 'Da Nang')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_postCode'), '55000')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_countryName'), 'Vietnam')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_contactNumber'), '0987654321')

WebUI.setText(findTestObject('CUSTOMER/Checkout_Page/txtbox_emailAddress'), 'customer2@gmail.com')

WebUI.click(findTestObject('CUSTOMER/Checkout_Page/btn_ProceedToPay'))

WebUI.delay(2)

WebUI.verifyElementPresent(findTestObject('CUSTOMER/Checkout_Page/msg_PleaseFillAllFields'), 10)

WebUI.verifyElementText(findTestObject('CUSTOMER/Checkout_Page/msg_PleaseFillAllFields'), 'Please fill all fields.')

WebUI.closeBrowser()

