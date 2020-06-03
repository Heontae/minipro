package com.javaex.ex02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PhoneManager {

	private List<Person> pList;
	private Scanner sc;

	public PhoneManager() throws IOException {
		sc = new Scanner(System.in);
		pList = new ArrayList<Person>();

		pList = getList();
	}

	// 시작준비 (시작화면 출려과 리스트 가져온다)
	public void showTitle() {
		System.out.println("*************************");
		System.out.println("*     전화번호 관리 프로그램         *");
		System.out.println("*************************");

	}

	// 메뉴 출력과 입력을 받는다.
	public int showMenu() {
		System.out.println("\n1.리스트  2.등록  3.삭제  4.검색  5.종료");
		System.out.println("-------------------------------");
		System.out.print(">메뉴번호: ");

		return sc.nextInt();
	}

	// 1.리스트선택시
	public void showList() {
		System.out.println("<1.리스트>");
		for (int i = 0; i < pList.size(); i++) {
			System.out.print(i + 1 + ". ");
			pList.get(i).draw();
		}
	}

	// 2.등록선택시
	public void showAdd() {
		System.out.print("<2.등록> \n>이름: ");
		String name = sc.next();

		System.out.print(">휴대전화: ");
		String hp = sc.next();

		System.out.print(">회사전화: ");
		String company = sc.next();
		Person person = new Person(name, hp, company);
		pList.add(person);
		System.out.println("[등록되었습니다.]");
	}

	// 3.삭제선택시
	public void showRemove() {
		System.out.print("<3.삭제>\n>번호: ");
		int num2 = sc.nextInt();
		try {
			pList.remove(num2 - 1);
			System.out.println("[삭제되었습니다.]");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("[번호를 잘못 입력하였습니다.]");
		}

	}

	// 4.검색선택시
	public void showSearch() {
		System.out.print("<4.검색>\n>이름: ");
		String search = sc.next();
		for (int i = 0; i < pList.size(); i++) {
			if (pList.get(i).getName().contains(search)) {
				pList.get(i).draw();
			}
		}
	}

	// 5.종료시
	public void showEnd() throws IOException {
		System.out.println("*************************");
		System.out.println("*        감사합니다.       *");
		System.out.println("*************************");
		saveList();
	}
	
	
	// 메뉴번호를 잘못 입력시 안내문구를 출력하는 메소드
	public void showEtc() {
		System.out.println("[다시 입력해주세요.]");
	}
	
	
	// 파일을 읽어 리스트에 담아 전달한다.
	private List<Person> getList() throws IOException  {
		List<Person> pList = new ArrayList<Person>();
		
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
			pList.add(person);
		}
		
		br.close();
		return pList;
	}

	// 리스트를 파일에 저장한다.
	private void saveList() throws IOException {
		Writer or = new FileWriter("./phoneDB.txt");
		BufferedWriter bo = new BufferedWriter(or);

		for (int i = 0; i < pList.size(); i++) {
			bo.write(pList.get(i).getName() + "," + pList.get(i).getHp() + "," + pList.get(i).getCompany());
			bo.newLine();
		}
		
		bo.close();
	}	
	
}
