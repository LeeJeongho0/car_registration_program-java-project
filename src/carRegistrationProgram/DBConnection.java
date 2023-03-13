package carRegistrationProgram;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class DBConnection {
	private Connection connection = null;

	public void connect() {
		Properties properties = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("C:/Users/user/eclipse-workspace/CarRegistrtion/src/sec01/exam1/db.properties");
			properties.load(fis);
		} catch (FileNotFoundException e) {
			System.out.println("FileInputStream Error" + e.getStackTrace());
		} catch (IOException e) {
			System.out.println("properties.load Error" + e.getStackTrace());
		}

		try {
			Class.forName(properties.getProperty("driverName"));
			connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"),
					properties.getProperty("password"));
		} catch (ClassNotFoundException e) {
			System.out.println("[데이터베이스 로드오류]" + e.getStackTrace());
		} catch (SQLException e) {
			System.out.println("[데이터베이스 연결오류]" + e.getStackTrace());
		}
	}

	public int insertResident(Car c) {
		this.connect();
		PreparedStatement ps = null;
		int returnValue = -1;
		String query = "insert into residentCarTBL values (?,?,?,?,?);";

		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, c.getName());
			ps.setString(2, c.getDongHosu());
			ps.setString(3, c.getDate());
			ps.setString(4, c.getEngine());
			ps.setString(5, c.getLicensePlate());

			returnValue = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("insert 오류발생" + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				System.out.println("ps close 오류" + e.getMessage());
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("connection close 오류" + e.getMessage());
			}
		} 
		return returnValue;
	}

	public int insertVisitor(Car c) {
		this.connect();
		PreparedStatement ps = null;
		int returnValue = -1;
		String query = "insert into visitorCarTBL values (?,?,?,?);";

		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, c.getName());
			ps.setString(2, c.getReason());
			ps.setString(3, c.getDate());
			ps.setString(4, c.getLicensePlate());

			// 삽입성공하면 1 리턴
			returnValue = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("insert 오류발생" + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				System.out.println("ps close 오류" + e.getMessage());
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("connection close 오류" + e.getMessage());
			}
		} 
		return returnValue;
	}

	public ArrayList<Car> selectResident() {
		ArrayList<Car> list = new ArrayList<>();
		this.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from residentCarTBL";

		try {
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs == null) {
				return null;
			}
			while (rs.next()) {
				String name = rs.getString("name");
				String dongHosu = rs.getString("dongHosu");
				String date = rs.getString("date");
				String engine = rs.getString("engine");
				String licensePlate = rs.getString("licensePlate");
				list.add(new Car(name, dongHosu, date, engine, licensePlate));
			}
		} catch (Exception e) {
			System.out.println("select 오류발생" + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				System.out.println("ps close 오류" + e.getMessage());
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("connection close 오류" + e.getMessage());
			}
		} 
		return list;
	}

	public ArrayList<Car> selectVisitor() {
		ArrayList<Car> list = new ArrayList<>();
		this.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from visitorCarTBL";

		try {
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs == null) {
				return null;
			}
			while (rs.next()) {
				String name = rs.getString("name");
				String reason = rs.getString("reason");
				String date = rs.getString("date");
				String licensePlate = rs.getString("licensePlate");
				list.add(new Car(name, reason, date, licensePlate));
			}
		} catch (Exception e) {
			System.out.println("select 오류발생" + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				System.out.println("ps close 오류" + e.getMessage());
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("connection close 오류" + e.getMessage());
			}
		} 
		return list;
	}

	public ArrayList<Car> residentSearchSelect(String dataName) {
		ArrayList<Car> list = new ArrayList<>();
		this.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from residentCarTBL where name = ?";

		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, dataName);
			rs = ps.executeQuery();
			if (rs == null) {
				return null;
			}
			while (rs.next()) {
				String name = rs.getString("name");
				String dongHosu = rs.getString("dongHosu");
				String date = rs.getString("date");
				String engine = rs.getString("engine");
				String licensePlate = rs.getString("licensePlate");
				list.add(new Car(name, dongHosu, date, engine, licensePlate));
			}
		} catch (Exception e) {
			System.out.println("select 오류발생" + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				System.out.println("ps close 오류" + e.getMessage());
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("connection close 오류" + e.getMessage());
			}
		} 
		return list;
	}

	public ArrayList<Car> visitorSearchSelect(String dataName) {
		ArrayList<Car> list = new ArrayList<>();
		this.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from visitorCarTBL where name = ?";

		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, dataName);
			rs = ps.executeQuery();
			if (rs == null) {
				return null;
			}
			while (rs.next()) {
				String name = rs.getString("name");
				String reason = rs.getString("reason");
				String date = rs.getString("date");
				String licensePlate = rs.getString("licensePlate");
				list.add(new Car(name, reason, date, licensePlate));
			}
		} catch (Exception e) {
			System.out.println("select 오류발생" + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				System.out.println("ps close 오류" + e.getMessage());
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("connection close 오류" + e.getMessage());
			}
		} 
		return list;
	}

	public Car residentLicensePlate(String residentLicensePlate) {
		Car car = null;
		this.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from residentCarTBL where licensePlate = ?";

		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, residentLicensePlate);
			rs = ps.executeQuery();
			if (rs == null) {
				return null;
			}
			while (rs.next()) {
				String name = rs.getString("name");
				String dongHosu = rs.getString("dongHosu");
				String date = rs.getString("date");
				String engine = rs.getString("engine");
				String licensePlate = rs.getString("licensePlate");
				car = new Car(name, dongHosu, date, engine, licensePlate);
			}
		} catch (Exception e) {
			System.out.println("select 오류발생" + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				System.out.println("ps close 오류" + e.getMessage());
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("connection close 오류" + e.getMessage());
			}
		} 
		return car;
	}

	public Car visitorLicensePlate(String visitorLicensePlate) {
		Car car = null;
		this.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from visitorCarTBL where licensePlate = ?";
		
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, visitorLicensePlate);
			rs = ps.executeQuery();
			if (rs == null) {
				return null;
			}
			while (rs.next()) {
				String name = rs.getString("name");
				String reason = rs.getString("reason");
				String date = rs.getString("date");
				String licensePlate = rs.getString("licensePlate");
				car = new Car(name, reason, date, licensePlate);
			}
		} catch (Exception e) {
			System.out.println("select 오류발생" + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				System.out.println("ps close 오류" + e.getMessage());
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("connection close 오류" + e.getMessage());
			}
		} 
		return car;
	}

	public int updateResident(Car c) {
		this.connect();
		PreparedStatement ps = null;
		int returnValue = -1;
		String query = "update residentCarTBL set dongHosu = ?, date = ?, engine = ? where licensePlate = ?";

		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, c.getDongHosu());
			ps.setString(2, c.getDate());
			ps.setString(3, c.getEngine());
			ps.setString(4, c.getLicensePlate());
			returnValue = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("insert 오류발생" + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				System.out.println("ps close 오류" + e.getMessage());
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("connection close 오류" + e.getMessage());
			}
		} 
		return returnValue;
	}

	public int updateVisitor(Car c) {
		this.connect();
		PreparedStatement ps = null;
		int returnValue = -1;
		String query = "update visitorCarTBL set reason = ?, date = ?, licensePlate = ? where name = ?";
		
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, c.getReason());
			ps.setString(2, c.getDate());
			ps.setString(3, c.getLicensePlate());
			ps.setString(4, c.getName());
			returnValue = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("insert 오류발생" + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				System.out.println("ps close 오류" + e.getMessage());
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("connection close 오류" + e.getMessage());
			}
		} 
		return returnValue;
	}

	public ArrayList<Car> selectSortResident() {
		ArrayList<Car> list = new ArrayList<>();
		this.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from residentCarTBL order by date asc";

		try {
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs == null) {
				return null;
			}
			while (rs.next()) {
				String name = rs.getString("name");
				String dongHosu = rs.getString("dongHosu");
				String date = rs.getString("date");
				String engine = rs.getString("engine");
				String licensePlate = rs.getString("licensePlate");
				list.add(new Car(name, dongHosu, date, engine, licensePlate));
			}
		} catch (Exception e) {
			System.out.println("select 오류발생" + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				System.out.println("ps close 오류" + e.getMessage());
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("connection close 오류" + e.getMessage());
			}
		}
		return list;
	}
	
	public ArrayList<Car> selectSortVisitor() {
		ArrayList<Car> list = new ArrayList<>();
		this.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from visitorCarTBL order by date asc";
		
		try {
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			if (rs == null) {
				return null;
			}
			while (rs.next()) {
				String name = rs.getString("name");
				String reason = rs.getString("reason");
				String date = rs.getString("date");
				String licensePlate = rs.getString("licensePlate");
				list.add(new Car(name, reason, date, licensePlate));
			}
		} catch (Exception e) {
			System.out.println("select 오류발생" + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				System.out.println("ps close 오류" + e.getMessage());
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("connection close 오류" + e.getMessage());
			}
		}
		return list;
	}

	public int deleteResident(String deleteResidentData) {
		this.connect();
		PreparedStatement ps = null;
		int returnValue = -1;
		String query = "delete from residentCarTBL where licensePlate = ?";

		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, deleteResidentData);

			returnValue = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("insert 오류발생" + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				System.out.println("ps close 오류" + e.getMessage());
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("connection close 오류" + e.getMessage());
			}
		} 
		return returnValue;
	}

	public int deleteVisitor(String deleteVisitorData) {
		this.connect();
		PreparedStatement ps = null;
		int returnValue = -1;
		String query = "delete from visitorCarTBL where licensePlate = ?";

		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, deleteVisitorData);
			returnValue = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("insert 오류발생" + e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				System.out.println("ps close 오류" + e.getMessage());
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("connection close 오류" + e.getMessage());
			}
		} 
		return returnValue;
	}

}
