/**********************************************************************
 * Created by : Nenad Samardzic
 * Date       : 07/10/2013
 * Description: The class represents tool for saving generated system's utilization data (CPU and NET)
 * Idea       : Data Generator takes 3 user-provided values:
 *              - database user name
 *              - database password
 *                  - makes deposit or withdrawal
 *                  - checks balance
 *                  - interval (in seconds)
 *              The first two provide access to the database in which the collected data is stored
 *              The third one represents the interval for data collection/generation
 *              It uses:
 *              	CPUutil - class for generating CPU utilization data and
 *              	NETutil - class for generating network utilization data
 **********************************************************************/
package DataGenerator;

import java.sql.*;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane; 

import org.hyperic.sigar.SigarException;

public class DataGenerator {
	static String sUsername, sPassword;

	//Saves collected data to the DataGenerator DB
	private void DBSave(double[] CPUInfo, int[] NETInfo) {
		Connection myConn = null;
		PreparedStatement statement;
		String sSQL;

		try {
			//create connection
			Class.forName("com.mysql.jdbc.Driver");
			myConn = DriverManager.getConnection("jdbc:mysql://localhost/datagenerator", sUsername, sPassword);
			//make a time-stamp
			Timestamp date = new Timestamp(new Date().getTime());
			//prepare SQL statements and execute them
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

	//makes one "tick" - collects the data for CPU and NET and saves it to a DB
	private class Tick implements Runnable {
		CPUutil myCPUU = new CPUutil();
		NETutil myNETU = new NETutil();
	    @Override
	    public void run() {
	    	try {
				DBSave(myCPUU.getCPUInfo(), myNETU.getNETInfo());
			} catch (SigarException sEx) {
				sEx.printStackTrace();
			}
	    }
	}

	public static void main(String[] args) {
		int nInterval = 0; //data collecting interval
		boolean secsOK = false; //flag for interval input format
		String sMessage = "Please provide interval for collecting the data (in secs): ";
		//just to avoid hard-coding of user and password
		sUsername= JOptionPane.showInputDialog("Please provide database username: ");
		sPassword= JOptionPane.showInputDialog("Please provide database password: ");
		while (!secsOK){
			try {
				nInterval= Integer.parseInt(JOptionPane.showInputDialog(sMessage));
				secsOK = true;
			}
			catch (Exception ex) {
				sMessage = "Irregular value for interval!\nPlease provide interval for collecting the data (in secs): ";
			}
		}
		DataGenerator myDG = new DataGenerator();
		ScheduledExecutorService mySES = Executors.newScheduledThreadPool(1);
		mySES.scheduleAtFixedRate(myDG.new Tick(), 0, nInterval*1000, TimeUnit.MILLISECONDS);
	}
}
