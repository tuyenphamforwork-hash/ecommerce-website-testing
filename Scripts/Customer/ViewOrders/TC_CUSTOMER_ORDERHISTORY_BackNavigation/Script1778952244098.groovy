import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

WebUI.openBrowser('')

WebUI.maximizeWindow()

// ========================================
// Navigate To Login Page
// ========================================
WebUI.navigateToUrl(GlobalVariable.baseUrl + '/login.php')

// ========================================
// Login
// ========================================
WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'), 'customer2@gmail.com')

WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'), 'Customer@123456')

WebUI.click(findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in'))

// ========================================
// Open Order History
// ========================================
WebUI.click(findTestObject('CUSTOMER/HomePage/btn_menu_Home'))

WebUI.click(findTestObject('CUSTOMER/HomePage/btn_menu_profile'))

WebUI.delay(3)

// ========================================
// Verify Order History Displayed
// ========================================
WebUI.verifyElementPresent(findTestObject('CUSTOMER/Order_History/div_OrderHistory'), 10)

// ========================================
// Get Current URL
// ========================================
String orderHistoryUrl = WebUI.getUrl()

println('Order History URL: ' + orderHistoryUrl)

// ========================================
// Browser Back
// ========================================
WebUI.back()

WebUI.delay(3)

WebUI.click(findTestObject('CUSTOMER/HomePage/btn_menu_profile'))

// ========================================
// Verify Returned To Order History
// ========================================
WebUI.verifyElementPresent(findTestObject('CUSTOMER/Order_History/div_OrderHistory'), 10)

// ========================================
// Verify URL Returned Correctly
// ========================================
String returnedUrl = WebUI.getUrl()

println('Returned URL: ' + returnedUrl)

WebUI.verifyEqual(returnedUrl, orderHistoryUrl)

// ========================================
// Verify Order List Still Displayed
// ========================================
WebUI.verifyElementPresent(findTestObject('CUSTOMER/Order_History/txtview_totalPrice'), 10)

// ========================================
// Close Browser
// ========================================
WebUI.closeBrowser()

