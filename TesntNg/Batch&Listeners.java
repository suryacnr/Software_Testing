//Batch file executing mutliple teast cases as a suite is called batch file
// test suite means ==> smoke testing and regression testing,retesting,sanitiy testing all thiscomes under test suite etc...
//if we want we can create and run test cases as whole or we can divide  them by test suite
//to create suite we need to test suite with .xml file, the xml file must be present in the same package 
// to create our own xml file we should remeber comment first it starts with <suite> then <test> then <classes> then <class> and close the </class> tag if we have mulitiple
  // class we can have to create multiple class tags then close all the above tags
//if we use suite and test we have to provide any name eg <suite name="sanity">, verbose name means it give logs verbos as 1 to 10 <test Verbose = "2" name="sanitytesst>
     //no need to classes and for class we need to provide complete java class name <class name ='packagename.classname"/>
//we can also run all the testsuite using single XMl file,first it starts with <suite> then <suite-files> then <suite-file path ="location of the xml file means sanity suite 
    //file location "/> can create multiple suite file now close the suie files and suite



