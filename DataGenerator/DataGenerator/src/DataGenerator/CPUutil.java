package DataGenerator;

import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.CpuPerc;

public class CPUutil {
	private Sigar mySigar = new Sigar();
	
	double[] getCPUInfo() {
		//private void getCPUInfo() {
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
			dCombined = myCpuPerc.getCombined();
		} catch (SigarException sEx) {
			sEx.printStackTrace();
		}
		/*
		System.out.println("System CPU user: " + dUser + "%");
		System.out.println("System CPU kernel: " + dSystem + "%");
		System.out.println("System CPU nice: " + dNice + "%");
		System.out.println("System CPU idle: " + dIdle + "%");
		System.out.println("System CPU io wait: " + dWait + "%");
		System.out.println("System CPU servicing interrupts: " + dIrq + "%");
		System.out.println("System CPU servicing soft irqs: " + dSoftIrq + "%");
		System.out.println("System CPU involuntary wait: " + dStolen + "%");
		System.out.println("System CPU User + Sys + Nice + Wait: " + dCombined + "%");
		*/
		return new double[] {dUser, dSystem, dNice, dIdle, dWait, dIrq, dSoftIrq, dStolen, dCombined};
	}
	/*
	public static void main(String[] args) {
		CPUutil myCPUU = new CPUutil();
		myCPUU.getCPUInfo();
	}
	*/
}