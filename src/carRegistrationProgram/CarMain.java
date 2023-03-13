package carRegistrationProgram;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarMain {
	public static Scanner sc = new Scanner(System.in);
	public static final int REGISTRATION = 1, PRINT = 2, SEARCH = 3, UPDATE = 4, DATE_SORT = 5, DELETE = 6, EXIT = 7;
	public static final int RESIDENT = 1, VISITOR = 2;

	public static void main(String[] args) {
		boolean run = true;
		DBConnection dbCon = new DBConnection();
		int no = 0;
		while (run) {
			System.out.println("+-----------------------------------------------------------------+");
			System.out.println("1.등록 2.출력 3.검색 4.수정 5.날짜 순 정렬 6.삭제 7.종료");
			System.out.println("+-----------------------------------------------------------------+");
			System.out.print(">> ");
			try {
				no = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("입력 오류" + e.getMessage());
			}
			switch (no) {
			case REGISTRATION:
				int no2 = 0;
				System.out.println("+-----------------------------------------------------------------+");
				System.out.println("1. 입주민 2. 방문객");
				System.out.println("+-----------------------------------------------------------------+");
				System.out.print(">> ");
				no2 = Integer.parseInt(sc.nextLine());

				switch (no2) {
				case RESIDENT:
					Car car = inputResidentData();
					int rValue = dbCon.insertResident(car);
					if (rValue == 1) {
						System.out.println("등록성공");
					} else {
						System.out.println("등록실패");
					}
					break;
				case VISITOR:
					Car car2 = inputVisitorData();
					int rValue2 = dbCon.insertVisitor(car2);
					if (rValue2 == 1) {
						System.out.println("등록성공");
					} else {
						System.out.println("등록실패");
					}
					break;
				}
				break;
			case PRINT:
				int no3 = 0;
				System.out.println("+-----------------------------------------------------------------+");
				System.out.println("1. 입주민 2. 방문객");
				System.out.println("+-----------------------------------------------------------------+");
				System.out.print(">> ");
				no3 = Integer.parseInt(sc.nextLine());

				switch (no3) {
				case RESIDENT:
					ArrayList<Car> list2 = dbCon.selectResident();
					if (list2.size() >= 1) {
						printResidentCar(list2);
					} else {
						System.out.println("데이터가 없습니다.");
					}
					break;
				case VISITOR:
					ArrayList<Car> list3 = dbCon.selectVisitor();
					if (list3.size() >= 1) {
						printVisitorCar(list3);
					} else {
						System.out.println("데이터가 없습니다.");
					}
					break;
				}
				break;
			case SEARCH:
				int no4 = 0;
				System.out.println("+-----------------------------------------------------------------+");
				System.out.println("1. 입주민 2. 방문객");
				System.out.println("+-----------------------------------------------------------------+");
				System.out.print(">> ");
				no4 = Integer.parseInt(sc.nextLine());

				switch (no4) {
				case RESIDENT:
					String residentName = searchPerson();
					ArrayList<Car> list4 = dbCon.residentSearchSelect(residentName);
					if (list4.size() >= 1) {
						printResidentCar(list4);
					} else {
						System.out.println("이름 검색 오류");
					}
					break;
				case VISITOR:
					String visitorName = searchPerson();
					ArrayList<Car> list5 = dbCon.visitorSearchSelect(visitorName);
					if (list5.size() >= 1) {
						printVisitorCar(list5);
					} else {
						System.out.println("이름 검색 오류");
					}
					break;
				}
				break;
			case UPDATE:
				int no5 = 0;
				System.out.println("+-----------------------------------------------------------------+");
				System.out.println("1. 입주민 2. 방문객");
				System.out.println("+-----------------------------------------------------------------+");
				System.out.print(">> ");
				no5 = Integer.parseInt(sc.nextLine());

				switch (no5) {
				case RESIDENT:
					int updateReturnValue = 0;
					String residentLicensePlate = inputlicensePlate("차량 번호판");
					Car c = dbCon.residentLicensePlate(residentLicensePlate);
					if (c == null) {
						System.out.println("해당 차량은 존재하지 않습니다.");
					} else {
						Car updateCar = updateResident(c);
						updateReturnValue = dbCon.updateResident(updateCar);
					}
					if (updateReturnValue == 1) {
						System.out.println("update 성공");
					} else {
						System.out.println("update 실패");
					}
					break;
				case VISITOR:
					int updateReturnValue2 = 0;
					String visitorLicensePlate = inputlicensePlate("차량 번호판");
					Car c2 = dbCon.visitorLicensePlate(visitorLicensePlate);
					if (c2 == null) {
						System.out.println("해당 차량은 존재하지 않습니다.");
					} else {
						Car updateCar = updateCar2(c2);
						updateReturnValue2 = dbCon.updateVisitor(updateCar);
					}
					if (updateReturnValue2 == 1) {
						System.out.println("update 성공");
					} else {
						System.out.println("update 실패");
					}
					break;
				}
				break;
			case DATE_SORT:
				int no6 = 0;
				System.out.println("+-----------------------------------------------------------------+");
				System.out.println("1. 입주민 2. 방문객");
				System.out.println("+-----------------------------------------------------------------+");
				System.out.print(">> ");
				no6 = Integer.parseInt(sc.nextLine());

				switch (no6) {
				case RESIDENT:
					ArrayList<Car> list6 = dbCon.selectSortResident();
					if (list6 == null) {
						System.out.println("정렬 실패");
					} else {
						printResidentDateSort(list6);
					}
					break;
				case VISITOR:
					ArrayList<Car> list7 = dbCon.selectSortVisitor();
					if (list7 == null) {
						System.out.println("정렬 실패");
					} else {
						printVisitorDateSort(list7);
					}
					break;
				}
				break;
			case DELETE:
				int no7 = 0;
				System.out.println("+-----------------------------------------------------------------+");
				System.out.println("1. 입주민 2. 방문객");
				System.out.println("+-----------------------------------------------------------------+");
				System.out.print(">> ");
				no7 = Integer.parseInt(sc.nextLine());

				switch (no7) {
				case RESIDENT:
					String deleteResidentData = inputlicensePlate("차량 번호판");
					int deleteReturnValue = dbCon.deleteResident(deleteResidentData);
					if (deleteReturnValue == 1) {
						System.out.println("삭제 성공");
					} else {
						System.out.println("님은 존재하지 않습니다.");
					}
					break;
				case VISITOR:
					String deleteVisitorData = inputlicensePlate("차량 번호판");
					int deleteReturnValue2 = dbCon.deleteVisitor(deleteVisitorData);
					if (deleteReturnValue2 == 1) {
						System.out.println("삭제 성공");
					} else {
						System.out.println("님은 존재하지 않습니다.");
					}
					break;
				}
				break;
			case EXIT:
				run = false;
				System.out.println("종료");
				break;
			default:
				System.err.println("메뉴를 확인해 주세요.");
				continue;
			}
		}
	}

	private static void printResidentDateSort(ArrayList<Car> list6) {
		for (Car data : list6) {
			System.out.println(data.toString());
		}
	}
	
	private static void printVisitorDateSort(ArrayList<Car> list6) {
		for (Car data : list6) {
			System.out.println(data.toString1());
		}
	}

	private static Car updateResident(Car c) {
		String dongHosu = inputNewDongHosu("동호수");
		c.setDongHosu(dongHosu);
		String date = inputNewDate("날짜");
		c.setDate(date);
		String engine = inputNewEngine("엔진");
		c.setEngine(engine);
		System.out.println("\n" + "이름" + "\t" + "동호수" + "\t\t" + "날짜" + "\t\t" + "엔진");
		System.out.println(c.getName()+"\t"+c.getDongHosu()+"\t"+c.getDate()+"\t"+c.getEngine());
		return c;
	}

	private static Car updateCar2(Car c) {
		String reason = inputNewReason("방문사유");
		c.setReason(reason);
		String date = inputNewDate("날짜");
		c.setDate(date);
		System.out.println("\n" + "이름" + "\t" + "방문사유" + "\t" + "날짜");
		System.out.println(c.toString2());
		return c;
	}

	private static String inputNewReason(String string) {
		String reason = null;
		while (true) {
			try {
				System.out.print("방문사유: ");
				reason = sc.nextLine();
				Pattern pattern = Pattern.compile("^[가-힣]{1,30}$");
				Matcher matcher = pattern.matcher(reason);
				if (!matcher.find()) {
					System.out.println("방문사유 입력오류발생 재입력요청합니다.");
				} else {
					break;
				}
			} catch (Exception e) {
				System.out.println("입력값에서 오류가 발생했습니다.");
				break;
			}
		}
		return reason;
	}

	private static String inputNewDongHosu(String name) {
		String data = null;
		int dong = 0;
		int hosu = 0;
		while (true) {
			try {
				System.out.print("동: ");
				dong = Integer.parseInt(sc.nextLine());
				Pattern pattern = Pattern.compile("^[0-9]{1,3}$");
				Matcher matcher = pattern.matcher(String.valueOf(dong));
				if (!matcher.find()) {
					System.out.println("동 입력오류발생 재입력요청합니다.");
				} else {
					break;
				}
			} catch (Exception e) {
				System.out.println("입력값에서 오류가 발생했습니다.");
				continue;
			}
		}
		while (true) {
			try {
				System.out.print("호수: ");
				hosu = Integer.parseInt(sc.nextLine());
				Pattern pattern1 = Pattern.compile("^[0-9]{1,4}$");
				Matcher matcher1 = pattern1.matcher(String.valueOf(hosu));
				if (!matcher1.find()) {
					System.out.println("호수 입력오류발생 재입력요청합니다.");
				} else {
					break;
				}
			} catch (Exception e) {
				System.out.println("입력값에서 오류가 발생했습니다.");
				continue;
			}
		}
		data = dong + "동 " + hosu + "호";
		return data;
	}

	private static String inputlicensePlate(String name) {
		String data = null;
		while (true) {
			System.out.print(name + " >>(ex.00가0000) ");
			try {
				data = sc.nextLine();
				Pattern pattern = Pattern.compile("^\\d{2,3}[가-힣]\\d{4}");
				Matcher matcher = pattern.matcher(data);
				if (!matcher.find()) {
					System.out.println("입력오류발생 재입력요청합니다.");
				} else {
					break;
				}
			} catch (Exception e) {
				System.out.println("입력값에서 오류가 발생했습니다.");
			}
		}
		return data;
	}

	private static String inputNewEngine(String name) {
		String data = null;
		while (true) {
			System.out.print(name + " >>(전기차/내연기관) ");
			try {
				data = sc.nextLine();
				if (data.equals("전기차") || data.equals("내연기관")) {
					break;
				} else {
					System.out.println("다시 입력해주세요");
				}
			} catch (Exception e) {
				System.out.println("입력값에서 오류가 발생했습니다.");
				break;
			}
		}
		return data;
	}

	private static String inputNewDate(String name) {
		String data = null;
		while (true) {
			System.out.print(name + " >>(yyyy-mm-dd) ");
			try {
				data = sc.nextLine();
				LocalDate ld = LocalDate.parse(data);
				name = String.valueOf(ld);
				break;
			} catch (Exception e) {
				System.out.println("입력값에서 오류가 발생했습니다. ");
				continue;
			}
		}
		return data;
	}

	private static String searchPerson() {
		String name = null;
		name = matchingNamePattern();
		return name;
	}

	private static void printResidentCar(ArrayList<Car> list2) {
		System.out.println("\n" + "이름" + "\t" + "동호수" + "\t\t" + "날짜" + "\t\t" + "엔진" + "\t" + "번호판");
		for (Car data : list2) {

			System.out.println(data);
		}
	}

	private static void printVisitorCar(ArrayList<Car> list2) {
		System.out.println("\n" + "이름" + "\t" + "방문사유" + "\t" + "날짜" + "\t\t" + "번호판");
		for (Car data : list2) {
			System.out.println(data.toString1());
		}
	}

	private static Car inputResidentData() {
		String name = matchingNamePattern();
		String dongHosu = inputDongHosu();
		String date = inputDate();
		String engine = inputEngine();
		String licensePlate = inputLicesePlate();
		Car car = new Car(name, dongHosu, date, engine, licensePlate);
		return car;
	}

	private static Car inputVisitorData() {
		String name = matchingNamePattern();
		String reason = inputReason();
		String date = inputDate();
		String licensePlate = inputLicesePlate();
		Car car = new Car(name, reason, date, licensePlate);
		return car;
	}

	private static String inputReason() {
		String reason = null;
		while (true) {
			try {
				System.out.print("방문사유: ");
				reason = sc.nextLine();
				Pattern pattern = Pattern.compile("^[가-힣]{1,30}$");
				Matcher matcher = pattern.matcher(reason);
				if (!matcher.find()) {
					System.out.println("방문사유 입력오류발생 재입력요청합니다.");
				} else {
					break;
				}
			} catch (Exception e) {
				System.out.println("입력값에서 오류가 발생했습니다.");
				break;
			}
		}
		return reason;
	}

	private static String inputDongHosu() {
		String dongHosu = null;
		int dong = 0;
		int hosu = 0;
		while (true) {
			try {
				System.out.print("동: ");
				dong = Integer.parseInt(sc.nextLine());
				Pattern pattern = Pattern.compile("^[0-9]{1,3}$");
				Matcher matcher = pattern.matcher(String.valueOf(dong));
				if (!matcher.find()) {
					System.out.println("동 입력오류발생 재입력요청합니다.");
				} else {
					break;
				}
			} catch (Exception e) {
				System.out.println("입력값에서 오류가 발생했습니다.");
				continue;
			}
		}
		while (true) {
			try {
				System.out.print("호수: ");
				hosu = Integer.parseInt(sc.nextLine());
				Pattern pattern1 = Pattern.compile("^[0-9]{1,4}$");
				Matcher matcher1 = pattern1.matcher(String.valueOf(hosu));
				if (!matcher1.find()) {
					System.out.println("호수 입력오류발생 재입력요청합니다.");
				} else {
					break;
				}
			} catch (Exception e) {
				System.out.println("입력값에서 오류가 발생했습니다.");
				continue;
			}
		}
		dongHosu = dong + "동 " + hosu + "호";
		return dongHosu;
	}

	private static String inputLicesePlate() {
		String licensePlate = null;
		while (true) {
			try {
				System.out.print("번호판(ex.00가0000): ");
				licensePlate = sc.nextLine();
				Pattern pattern = Pattern.compile("^\\d{2,3}[가-힣]\\d{4}");
				Matcher matcher = pattern.matcher(licensePlate);
				if (!matcher.find()) {
					System.out.println("입력오류발생 재입력요청합니다.");
				} else {
					break;
				}
			} catch (Exception e) {
				System.out.println("입력값에서 오류가 발생했습니다.");
			}
		}
		return licensePlate;
	}

	private static String inputEngine() {
		String engine = null;
		while (true) {
			try {
				System.out.print("엔진(전기차/내연기관): ");
				engine = sc.nextLine();
				if (engine.equals("전기차") || engine.equals("내연기관")) {
					break;
				} else {
					System.out.println("다시 입력해주세요");
				}
			} catch (Exception e) {
				System.out.println("입력값에서 오류가 발생했습니다.");
				break;
			}
		}
		return engine;
	}

	private static String inputDate() {
		String date = null;
		while (true) {
			try {
				System.out.print("날짜(yyyy-mm-dd): ");
				String input = sc.nextLine();
				LocalDate ld = LocalDate.parse(input);
				date = String.valueOf(ld);
				break;
			} catch (Exception e) {
				System.out.println("입력값에서 오류가 발생했습니다. ");
				continue;
			}
		}
		return date;
	}

	private static String matchingNamePattern() {
		String name = null;
		while (true) {
			try {
				System.out.print("이름: ");
				name = sc.nextLine();
				Pattern pattern = Pattern.compile("^[가-힣]{2,4}$");
				Matcher matcher = pattern.matcher(name);
				if (!matcher.find()) {
					System.out.println("이름입력오류발생 재입력요청합니다.");
				} else {
					break;
				}
			} catch (Exception e) {
				System.out.println("입력값에서 오류가 발생했습니다.");
				break;
			}
		}
		return name;
	}
}
