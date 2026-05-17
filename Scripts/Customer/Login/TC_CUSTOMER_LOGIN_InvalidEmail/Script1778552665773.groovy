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

// Open browser
WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl(GlobalVariable.baseUrl)

WebUI.click(findTestObject('GUEST/Homepage/menu_login'))

// Enter non-existing email
WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_email'), 'fake@mail.com')

// Enter password
WebUI.setText(findTestObject('CUSTOMER/Page_Login(USER)/txtbox_password'), '123456')

// Click Sign In
WebUI.click(findTestObject('CUSTOMER/Page_Login(USER)/btn_Sign in'))

// Verify error message displayed
WebUI.verifyTextPresent('(unavailable) please signup first', false)

// Verify user still stays on Login page
WebUI.verifyMatch(WebUI.getUrl(), GlobalVariable.baseUrl + '/login.php', false)

// Verify Profile menu does NOT appear
WebUI.verifyElementNotPresent(findTestObject('CUSTOMER/HomePage/btn_menu_profile'), 5)

// Close browser
WebUI.closeBrowser()

