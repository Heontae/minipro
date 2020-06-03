package com.javaex.ex03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;


public class PhoneRepository {

    
    //phoneDB.txt 파일을 읽어 모든 전화번호(리스트)를 전달하는 메소드
	public List<Person> getList() throws IOException {
		List<Person> phoneList = new ArrayList<Person>();
		
		Reader ir = new FileReader("./PhoneDB.txt");
		BufferedReader br = new BufferedReader(ir);
		
		while (true) {
			String str = br.readLine();
			if (str == null) {
				break;
			}

			String sArray[] = str.split(",");
			String name = sArray[0].trim();
			String hp = sArray[1].trim();
			String company = sArray[2].trim();
			Person person = new Person(name, hp, company);
			phoneList.add(person);
		}
		
		
		br.close();
		return phoneList;
	}

	//phoneDB.txt 에 모든 전화번호 리스트를 저장하는 메소드
	private void saveInfo(List<Person> list) throws IOException{
		 
		Writer or = new FileWriter("./phoneDB.txt");
		BufferedWriter bo = new BufferedWriter(or);

		for (int i = 0; i < list.size(); i++) {
			bo.write(list.get(i).getName() + "," + list.get(i).getHp() + "," + list.get(i).getCompany());
			bo.newLine();
		}
		
		bo.close();
	}
	
	//기존데이터에 새로입력받은 데이터를 추가하여 모두저장하는 메소드 
	public void addInfo(Person phoneVO) throws IOException {
		List<Person> list = getList();
		list.add(phoneVO);
		saveInfo(list);
	}

	//선택한 번호의 데이터를 삭제하고 저장하는 메소드(모두 다시저장)
	public void delInfo(int num)  throws IOException {
		List<Person> list = getList();
		list.remove(num-1);
		saveInfo(list);
	}
	

}
