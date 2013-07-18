/**********************************************************************
 * Created by : Nenad Samardzic
 * Date       : 07/10/2013
 * Description: The class represents generator of CPU utilization data
 * Idea       : CPUutil uses an external library - sigar.jar which provides access
 * 				to the numerous system's information
 * 				Here are collected and returned the following percentages:
 * 					- system and user times
 * 					- wait and idle times
 * 					- interrupt request times etc.
 **********************************************************************/
package DataGenerator;

import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.CpuPerc;

public class CPUutil {
	private Sigar mySigar = new Sigar();
	
	double[] getCPUInfo() throws SigarException {
		double dUser = 0, dSystem = 0, dNice = 0, dIdle = 0, dWait = 0, dIrq = 0, dSoftIrq = 0, dStolen = 0, dCombined = 0;

		try {
			CpuPerc myCpuPerc = mySigar.getCpuPerc();
			dUser = myCpuPerc.getUser();
			dSystem = myCpuPerc.getSys();
			dNice = myCpuPerc.getNice();
			dIdle = myCpuPerc.getIdle();
			dWait = myCpuPerc.getWait();
			dIrq = myCpuPerc.getIrq();
			dSoftIrq = myCpuPerc.getSoftIrq();
			dStolen = myCpuPerc.getStolen();
			dCombined = myCpuPerc.getCombined(); //User + Sys + Nice + Wait
		} catch (SigarException sEx) {
			throw sEx;
		}
		return new double[] {dUser, dSystem, dNice, dIdle, dWait, dIrq, dSoftIrq, dStolen, dCombined};
	}
}