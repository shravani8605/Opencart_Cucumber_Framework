package stepDefinition;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import PageObject.HomePage;
import PageObject.LoginPage;
import PageObject.MyAccountPage;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Steps {
	
	public WebDriver driver;
	public Logger logger;
    public ResourceBundle rb;
    LoginPage lp;
    MyAccountPage macc;
    HomePage hp;
    //Logger logger; //for logging
    //ResourceBundle rb; // for reading properties file
    String br; //to store browser name
    
    @Before
    public void setup() throws IOException
    {
    	logger= LogManager.getLogger(this.getClass());
    	//rb=ResourceBundle.getBundle("config");
    //	br=rb.getString("browser");  */
    	
    	File src = new File(".\\src\\test\\resources\\config.properties");
    	FileInputStream fis = new FileInputStream (src);
    	Properties pro = new Properties();
    	pro.load(fis);
    	br = pro.getProperty("browser");
    	
    
    }
    
    @After
    public void tearDown(Scenario scenario)
    {
    	System.out.println("Scenario status ======>"+scenario.getStatus());
        if(scenario.isFailed()) {
        	
        	TakesScreenshot ts=(TakesScreenshot) driver;
        	byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
        	scenario.attach(screenshot, "image/png",scenario.getName());
        }
    }
    
	@Given("user lanch the browser")
	public void user_lanch_the_browser() throws InterruptedException 
	{
	   // if(br.equals("Chrome"))
		//{
			driver=new ChromeDriver();
			Thread.sleep(1000);
	/*	}
		
		else if(br.equals("edge"))
		{
			driver = new EdgeDriver();
			
		}
		else
		{
			driver = new FirefoxDriver();
			
		} */
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
	}

	@Given("opens URL {string}")
	public void opens_url(String URL) 
	{
		driver.get(URL);
	    driver.manage().window().maximize();
	  	}

	@When("User navigate to MyAccount menu")
	public void user_navigate_to_my_account_menu() 
	{
		hp=new HomePage(driver);
    	hp.clickMyAccount();;
      // logger.info("Clicked on My Account");
            

	}

	@When("click on Login")
	public void click_on_login() {
	   hp.clickLogin();
	 // logger.info("Clicked on Login");
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String pwd) 
	{
		lp=new LoginPage(driver);
        
    	lp.setemail(email);
       // logger.info("Provided Email ");
        lp.setpassoword(pwd);
        //logger.info("Provided Password "); 
	}

	@When("Click on Login button")
	public void click_on_login_button() 
	{
		lp.clicklogin();
       // logger.info("Clicked on Login button");
 
	}

	@Then("User navigates to MyAccount Page")
	public void user_navigates_to_my_account_page()
	{
		macc=new MyAccountPage(driver);
		boolean targetpage=macc.isMyAccountPageExists();
	/*
        if(targetpage)
        {
           // logger.info("Login Success ");
            Assert.assertTrue(true);
        }
        else
        {
            //logger.error("Login Failed ");
            Assert.assertTrue(false);
        }
*/

	}
    }
