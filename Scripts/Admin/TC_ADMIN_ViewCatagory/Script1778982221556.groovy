import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl(GlobalVariable.baseUrl)

String adminEmail = 'admin1@gmail.com'

String adminPassword = 'Admin@1234567890'

WebUI.click(findTestObject('GUEST/Homepage/menu_login'))

WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'), adminEmail)

WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'), adminPassword)

WebUI.click(findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in'))

WebUI.verifyElementPresent(findTestObject('ADMIN/btn_menu_Profile'), 10)

WebUI.verifyTextPresent('HELLO, ADMIN', false)

WebUI.click(findTestObject('ADMIN/btn_VisitAdminPanel'))

WebUI.verifyElementPresent(findTestObject('ADMIN/AdminPanelLogin/div_AdminPanelLogin'), 10)

WebUI.setText(findTestObject('ADMIN/AdminPanelLogin/input_Email'), adminEmail)

WebUI.setText(findTestObject('ADMIN/AdminPanelLogin/input_Password'), adminPassword)

WebUI.click(findTestObject('ADMIN/btn_SignIn'))

WebUI.waitForPageLoad(10)

WebUI.navigateToUrl(GlobalVariable.baseUrl + '/admin/catagory.php')

WebUI.waitForPageLoad(10)

WebUI.verifyElementPresent(findTestObject('ADMIN/Menu/btn_menu_Catagory'), 10)

WebUI.verifyTextPresent('Category', false)

WebUI.verifyTextPresent('No. of Posts', false)

WebUI.verifyElementPresent(findTestObject('ADMIN/CatagoryPage/div_catagory'), 0)

WebUI.verifyElementPresent(findTestObject('ADMIN/CatagoryPage/catagoryName'), 10)

WebUI.verifyElementPresent(findTestObject('ADMIN/CatagoryPage/NoOfProduct'), 0)

WebUI.closeBrowser()

