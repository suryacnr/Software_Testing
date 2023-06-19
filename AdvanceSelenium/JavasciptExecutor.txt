//Action can be performed by javascript executor
//1.Flash an element == to higllight the action will performing in automation
//2.Drawing the board around the element== we can draw some border in screenshot to show the dev team where the error is accured in the SS
//3.capture the tilte == we can also capture the title using javascript
//4.click on some elemen == if webdriver is not working we can use javascript executor
//5.generate alert info====if something is fail while executing the code wile runtime we can see the error 
//6.refreshing the page== 
//7. scrolling the page

//agrument [0] == denotes the anonymous browser and argument[0] is refering to element (which means xpath or Weblement)

JavascriptExecutor js = new ((javascriptExecutor)driver) // we are casting or cast the driver object into javascriptExecuotr
public class js{
public static void flash(WebElement element, Webdriver driver){
 // JavascriptExecutor js = new((JavascriptExecutor)drive);
  String backgroudclr= element.getCssValue("backgroundColor"); //this will capture the backgroud color of the element in website
  for(int i=0; i< 500; i++){ // it will loop till 500 times and it will blink 500 tiems if we want we can reduce the color
    changeColor("#00000",element,driver)//1 this hilight the backgroud color  as black
    changeColor(backgroundclr, element, driver);  //2 this will hilight the backgroundcolor, it will take from website 
    }
    }
 public static void changeColor(String color, WebElement element, WedDriver driver){
   JavascriptExecutor js = new((JavascriptExecutor)drive); 
   js.executeScript("arguments[0].style.backgroundColor = '"+ color+"'", element); //arguments[0] is your way of accessing the first argument to the anonymous function and same for arguments[1]
   
   try{
     thread.sleep(20);
   }catch (executprion e){
   }
   
 //draw border 
   public static void drawborder(WebElement element, WedDriver driver){
     JavascriptExecutor js = new((JavascriptExecutor)drive);
     js.executeScript("arguments[0].style.border='3px solid red'",element);
     
   
 }
    
   //get title
   public static String gettitle(WebElement element, WedDriver driver){
     
     JavascriptExecutor js = new((JavascriptExecutor)drive);
     String tilte =js.executeScript("return document.title;").toString();
     return title;
     
   }
  //take screenshot
  File src = ((TakeScreenshot)driver).getScreenshotAs(OutputeType.File);
  File des = new File("C://Downloads/text.png");
  FileUtils.copyFile(src,des);
  
  //cick using javascript

   public static void click(WebElement element, WedDriver driver){
     
     JavascriptExecutor js = new((JavascriptExecutor)drive);
     js.executeScript("argument[0].click();",element)
    
     
   }
  
  //generate alert
  public static void genrateAlert(WebElement element, String tring){
   JavascriptExecutor js = new((JavascriptExecutor)drive);
     js.executeScript("alert("+tring+"));
     js.executeScript("history.go(0);//refresh the page
    
     
   }
   //scroll the web page till the element
    public static void ScollIntoView(WebElement element, Webdriver driver){
   JavascriptExecutor js = new((JavascriptExecutor)drive);
     js.executeScript("argument[0].scrollIntoView(true)",element);
    }
                      
    //scroll the web page till end
      public static void ScollToDown(Webdriver driver){
   JavascriptExecutor js = new((JavascriptExecutor)drive);
     js.executeScript("argument[0].scrollTo(0,document.body.ScrollHight)");
    }   
                      
    JavascriptExecutor js = (JavascriptExecutor)driver;
    //scroll the page till bottam
			js.executeScript("window.scrollBy(0,document.body.ScrollHight)");
			Thread.sleep(5000);
   //scroll the page top till the specified pixel
			js.executeScript("window.scrollBy(0,-100)");
		
			//scroll the page till the specified element
			js.executeScript("arguments[0].scrollIntoView(true)", ele);


}
