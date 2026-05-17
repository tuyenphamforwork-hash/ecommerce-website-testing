import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl(GlobalVariable.baseUrl)

useremail = 'customer2@gmail.com'

useraddress = '254 Nguyen Van Linh'

username = 'user2'

userphone = '0965786454'

WebUI.click(findTestObject('GUEST/Homepage/menu_login'))

WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'), useremail)

WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'), 'Customer@123456')

WebUI.click(findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in'))

WebUI.click(findTestObject('CUSTOMER/Profile/btn_edit_name_email'))

WebUI.setText(findTestObject('CUSTOMER/Profile/txtbox_name'), username)

WebUI.setText(findTestObject('CUSTOMER/Profile/txtbox_email'), useremail)

WebUI.click(findTestObject('CUSTOMER/Profile/btn_save_name_email'))

WebUI.click(findTestObject('CUSTOMER/Profile/btn_edit_address'))

WebUI.setText(findTestObject('CUSTOMER/Profile/txtbox_address'), useraddress)

WebUI.click(findTestObject('CUSTOMER/Profile/btn_save_address'))

WebUI.click(findTestObject('CUSTOMER/Profile/btn_edit_contact'))

WebUI.setText(findTestObject('CUSTOMER/Profile/txtbox_phoneNumber'), userphone)

WebUI.click(findTestObject('CUSTOMER/Profile/btn_save_phoneNumber'))

WebUI.refresh()

WebUI.verifyTextPresent(username, false)

WebUI.verifyTextPresent(useraddress, false)

WebUI.verifyTextPresent(userphone, false)

WebUI.closeBrowser()

