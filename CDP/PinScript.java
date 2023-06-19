@BeforeSuite
	public void BrowserSetup() {
		ReportSetup.intialize();
		WebDriverManager.chromedriver().setup();
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--Remote-Allow-origins=*");
		decoratedDriver =new ChromeDriver(option); //creating new instenaces
		decoratedDriver.manage().window().maximize();
		DevTools tools = ((ChromeDriver) decoratedDriver).getDevTools();
		tools.createSession();
		tools.getDomains().javascript().pin("notifications","""
        window.onload = () => {
            if (!window.jQuery) {
                var jquery = document.createElement('script'); 
                jquery.type = 'text/javascript';
                jquery.src = 'https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js';
                document.getElementsByTagName('head')[0].appendChild(jquery);
            } else {
                $ = window.jQuery;
            }
            $.getScript('https://cdnjs.cloudflare.com/ajax/libs/jquery-jgrowl/1.4.8/jquery.jgrowl.min.js')
            $('head').append('<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-jgrowl/1.4.8/jquery.jgrowl.min.css" type="text/css" />');
        }
        function highlight(element){
            let defaultBG = element.style.backgroundColor;
            let defaultOutline = element.style.outline;
            element.style.backgroundColor = '#FDFF47';
            element.style.outline = '#f00 solid 2px';

            setTimeout(function()
            {
                element.style.backgroundColor = defaultBG;
                element.style.outline = defaultOutline;
            }, 1000);
        }
        """);

		var listener = new WebDriverListener() {
			@Override
			public void beforeAnyWebElementCall(WebElement element, Method method, Object[] args) {
				try {
					growlMessage(String.format("About to call a method %s in element", method.getName()));
					//highlighElement(element);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void afterAnyWebElementCall(WebElement element, Method method, Object[] args, Object result) {
				try {
//					highlighElement(element);
					growlMessage(String.format("%s called for element %s", method.getName(), element.getTagName()));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void beforeAnyWebDriverCall(WebDriver driver, Method method, Object[] args) {
				try {
					growlMessage(String.format("%s webdriver call", method.getName()));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void afterAnyWebDriverCall(WebDriver driver, Method method, Object[] args, Object result) {
				try {
					growlMessage(String.format("%s webdriver call", method.getName()));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		driver = new EventFiringDecorator(listener).decorate(decoratedDriver);
		webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_FOR_ELEMENT_TIMEOUT));
		actions = new Actions(driver);


	}
	@BeforeClass
	public void onetimelogin() {
		
		basepage = new BasePage(driver);
		basepage.setdriver(driver);
		ReportSetup.TestDescription("Before class Executed");
		
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		ReportSetup.test.log(Status.INFO,"Entered the URL");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		ReportSetup.test.log(Status.INFO,"Waiting For 10 sec");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		
		Pages_TC_001 loged =new Pages_TC_001(driver);
		loged.Login();
	}
	@BeforeMethod()
	public void beforMerhod(ITestResult tr) {
		
		currentTestName = tr.getMethod().getMethodName();
		System.out.println("The Current Method on BeforeMetho "+ currentTestName);
		M1=ReportSetup.report.createTest(currentTestName);
	}
	
	@AfterMethod()
	public void afterMerhod(ITestResult tr) {
		System.out.println("The Current Method on AfterMethod "+ currentTestName);
		FertchedName(currentTestName);
		ReportSetup.result(tr,driver);
		
	}
	
	@AfterClass
	public void afterclass() {
		//ReportSetup.result(tr);
		try {
			Thread.sleep(5000);
			driver.manage().deleteAllCookies();
			Thread.sleep(5000);
			driver.navigate().to("https://google.com");
			Thread.sleep(5000);
			driver.manage().deleteAllCookies();
			driver.navigate().refresh();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@AfterSuite
	public void teardown() {
		
		driver.quit();
		ReportSetup.flush();
		
		
	}

	private void growlMessage(String message) throws InterruptedException {
		Thread.sleep(500);
		((JavascriptExecutor)driver).executeScript(String.format("$.jGrowl('%s', { header: 'Important' });", message));
	}
	
	public void FertchedName(String currentTestName) {
		

	
		int LogsCount =M1.getModel().getLogs().size();
		System.out.println(LogsCount);
		List<String> testSteps = new ArrayList<>();
		for(int i=0; i<LogsCount; i++) {
			String Statlog=M1.getModel().getLogs().get(i).getDetails();
			String Statuslog=M1.getModel().getLogs().get(i).toString();
			testSteps.add(Statlog);
			String StatusFail=M1.getModel().getLogs().get(i).getStatus().getName();
		   // if(StatusFail.equals("Fail")) {
		    //	System.out.println("Failed test case as been captured");
		    	for(String Steps: testSteps) {
					System.out.println("The test steps as been captured: "+Steps);
				}
		   // }
		}
	}
    

}
