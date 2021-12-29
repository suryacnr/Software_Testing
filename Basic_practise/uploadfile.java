how to upload files

 if type = file then we can directly use sendkeys method 
 dirve.findElement(By.xpth("")).sendkeys("C:\\Happ\\sample.txt");
if you dont have file type in input tagname theb we have to use robort class


 dirve.findElement(By.xpth("")).click(); //if the click method does not work then you have to use
 // javascript
WebElement element = dirve.findElement(By.xpth(""));
JavaScriptExecutor js =(JavaScriptExecutor)driver;
js.executeScript("arguments[0].click();, elemrnt");

Robot rb =new Robot();
rb.delay(1000);

StringSelection sc = new StringSelection("C:\\Happ\\sample.txt");
Toolkit.getDefaultkit().getSystemclipboard().setContents(ss,null);

rb.KeyPress(KeyEvent.VK_CONTROL);
rb.KeyPress(KeyEvent.VK_V);
rb.KeyRelease(KeyEvent.VK_CONTROL);
rb.KeyRelease(KeyEvent.VK_V);
rb.KeyPress(KeyEvent.VK_ENTER);
rb.KeyRelease(KeyEvent.VK_ENTER)
