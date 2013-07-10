package DataGenerator;

import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.NetStat;

public class NETutil {
	private Sigar mySigar = new Sigar();

	int[] getNETInfo() {
		//private void getNETInfo() {
		int nInboundTCP = 0, nOutboundTCP = 0, nInboundAll = 0, nOutboundAll = 0;

		try {
			NetStat myNetStat = mySigar.getNetStat();
			nInboundTCP = myNetStat.getTcpInboundTotal();
			nOutboundTCP = myNetStat.getTcpOutboundTotal();
			nInboundAll = myNetStat.getAllInboundTotal();
			nOutboundAll = myNetStat.getAllOutboundTotal();
		} catch (SigarException sEx) {
			sEx.printStackTrace();
		}
		/*
		System.out.println("Inbound TCP traffic: " + nInboundTCP);
		System.out.println("Outbound TCP traffic: " + nOutboundTCP);
		System.out.println("Inbound traffic: " + nInboundAll);
		System.out.println("Outbound traffic: " + nOutboundAll);
		*/
		return new int[] {nInboundTCP, nOutboundTCP, nInboundAll, nOutboundAll};
	}
	/*
	public static void main(String[] args) {
		NETutil myNETU = new NETutil();
		myNETU.getNETInfo();
	}
	*/
}