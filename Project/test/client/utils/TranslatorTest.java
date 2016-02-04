package client.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TranslatorTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
//	@Test 
	public void testObject2Json(){

		BufferedReader br;

		String line = new String();
		StringBuilder append = new StringBuilder();
		try{
			br = new BufferedReader(new FileReader("json.txt"));
			while((line = br.readLine()) != null){
				append.append(line);
			}
			br.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println(append.toString());
		Object temp = Translator.jsonToObject(append.toString());
		String json = Translator.objectToJson(temp);
		System.out.println(json);
	}
	@Test
	public void testJson2Object() throws FileNotFoundException{	 
		BufferedReader br;

		String line = new String();
		StringBuilder append = new StringBuilder();
		try{
			br = new BufferedReader(new FileReader("gameModel.txt"));
			while((line = br.readLine()) != null){
				append.append(line);
			}
			br.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		Object temp = Translator.jsonToObject(append.toString());
		System.out.println("hello");

	}

}
