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

// STEP 1: Open homepage
WebUI.navigateToUrl(GlobalVariable.baseUrl)

// STEP 2: Open Login page
WebUI.click(findTestObject('Guest/Homepage/menu_login'))

// STEP 3: Open Signup page
WebUI.click(findTestObject('Guest/Page_Login(USER)/btn_Signup'))

// STEP 4: Verify Signup page displayed
WebUI.verifyElementPresent(findTestObject('Guest/Page_HCA E-Commerce/button_Register'), 10)

// STEP 5: Input signup information
WebUI.setText(findTestObject('Guest/Page_HCA E-Commerce/TextBox_FullName'), 'Customer1')

WebUI.setText(findTestObject('Guest/Page_HCA E-Commerce/TextBox_PhoneNumber'), '+84 768456754')

WebUI.setText(findTestObject('Guest/Page_HCA E-Commerce/TextBox_Email'), 'customer1@gmail.com')

WebUI.setText(findTestObject('Guest/Page_HCA E-Commerce/TextBox_Address'), '1234 Main St')

WebUI.setText(findTestObject('Guest/Page_HCA E-Commerce/TextBox_Password'), 'Customer1')

// Different confirm password
WebUI.setText(findTestObject('Guest/Page_HCA E-Commerce/TextBox_ConfirmPassword'), 'Customer1')

// STEP 6: Click Register
WebUI.click(findTestObject('Guest/Page_HCA E-Commerce/button_Register'))

