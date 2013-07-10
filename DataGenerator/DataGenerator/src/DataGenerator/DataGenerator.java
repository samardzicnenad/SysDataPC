package DataGenerator;

import java.sql.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import javax.swing.JOptionPane; 

public class DataGenerator {
	static Timer myTimer;
	static String sUsername, sPassword;
	
	private void DBSave(double[] CPUInfo, int[] NETInfo) {
		Connection myConn = null;
		PreparedStatement statement;
		String sSQL;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			myConn = DriverManager.getConnection("jdbc:mysql://localhost/datagenerator", sUsername, sPassword);
			
			Timestamp date = new Timestamp(new Date().getTime());
			
			sSQL = "INSERT INTO cpudata (Time, User, System, Nice, Idle, Wait, Irq, SoftIrq, Stolen, Combined) VALUES" + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			statement = myConn.prepareStatement(sSQL);
			statement.setTimestamp(1, date);
			statement.setDouble(2, CPUInfo[0]);
			statement.setDouble(3, CPUInfo[1]);
			statement.setDouble(4, CPUInfo[2]);
			statement.setDouble(5, CPUInfo[3]);
			statement.setDouble(6, CPUInfo[4]);
			statement.setDouble(7, CPUInfo[5]);
			statement.setDouble(8, CPUInfo[6]);
			statement.setDouble(9, CPUInfo[7]);
			statement.setDouble(10, CPUInfo[8]);
			statement.executeUpdate();
			sSQL = "INSERT INTO netdata (Time, InboundTCP, OutboundTCP, InboundAll, OutboundAll) VALUES" + "(?, ?, ?, ?, ?)";
			statement = myConn.prepareStatement(sSQL);
			statement.setTimestamp(1, date);
			statement.setInt(2, NETInfo[0]);
			statement.setInt(3, NETInfo[1]);
			statement.setInt(4, NETInfo[2]);
			statement.setInt(5, NETInfo[3]);
			statement.executeUpdate();
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			if (myConn != null) {
				try {
					myConn.close();
				}
				catch (SQLException ignore) {}
			}
		}
	}
	
	public static void main(String[] args) {
		sUsername= JOptionPane.showInputDialog("Please provide database username: ");
		sPassword= JOptionPane.showInputDialog("Please provide database password: ");
		DataGenerator myDG = new DataGenerator();
		myTimer = new Timer();
		myTimer.schedule(myDG.new Tick(), 0, 60000);
	}
	
    class Tick extends TimerTask {
		CPUutil myCPUU = new CPUutil();
		NETutil myNETU = new NETutil();
        @Override
        public void run() {
        	DBSave(myCPUU.getCPUInfo(), myNETU.getNETInfo());
        }
    }
}
