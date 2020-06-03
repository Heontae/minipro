package com.javaex.ex01;

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

public class PhoneApp {

	public static void main(String[] args) throws IOException {

		List<Person> pList = new ArrayList<Person>();

		Reader ir = new FileReader("./PhoneDB.txt");
		BufferedReader br = new BufferedReader(ir);
		Scanner sc = new Scanner(System.in);
		System.out.println("*************************");
		System.out.println("*     전화번호 관리 프로그램         *");
		System.out.println("*************************");
		
		// 현재 PhonDB에있는값을 Array리스트에 저장하기
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
		
		boolean exit = true;
		while (exit) {

			System.out.println("\n1.리스트  2.등록  3.삭제  4.검색  5.종료");
			System.out.println("-------------------------------");
			System.out.print(">메뉴번호: ");

			int num = sc.nextInt();

			switch (num) {
			// 리스트
			case 1:
				System.out.println("<1.리스트>");
				for (int i = 0; i < pList.size(); i++) {
					System.out.print(i + 1 + ". ");
					pList.get(i).draw();
				}
				break;
			// 등록하기
			case 2:
				System.out.print("<2.등록> \n>이름: ");
				String name = sc.next();

				System.out.print(">휴대전화: ");
				String hp = sc.next();

				System.out.print(">회사전화: ");
				String company = sc.next();
				Person person = new Person(name, hp, company);
				pList.add(person);
				
			
				System.out.println("[등록되었습니다.]");
				break;
			// 삭제하기
			case 3:
				System.out.print("<3.삭제>\n>번호: ");
				int num2 = sc.nextInt();
				try {
					pList.remove(num2 - 1);
					System.out.println("[삭제되었습니다.]");
				} catch (IndexOutOfBoundsException e) {
					System.out.println("[번호를 잘못 입력하였습니다.]");
				}

				break;
			// 검색하기
			case 4:
				System.out.print("<4.검색>\n>이름: ");
				String search = sc.next();
				for (int i = 0; i < pList.size(); i++) {
					if (pList.get(i).getName().contains(search)) {
						pList.get(i).draw();
					}
				}
				break;
			// 종료하기
			case 5:
				Writer or = new FileWriter("./phoneDB.txt");
				BufferedWriter bo = new BufferedWriter(or);

				for (int i = 0; i < pList.size(); i++) {
					bo.write(pList.get(i).getName() + "," + pList.get(i).getHp() + "," + pList.get(i).getCompany());
					bo.newLine();
				}
				
				bo.close();
				exit = false;
				System.out.println("*************************");
				System.out.println("*        감사합니다.       *");
				System.out.println("*************************");
				break;
			// 다른값 입력
			default:
				System.out.println("[다시 입력해주세요.]");
				break;
			}// switch

		} // while
		
		br.close();
		sc.close();
	}
}