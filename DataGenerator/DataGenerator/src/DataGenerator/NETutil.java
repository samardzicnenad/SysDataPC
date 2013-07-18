/**********************************************************************
 * Created by : Nenad Samardzic
 * Date       : 07/10/2013
 * Description: The class represents generator of network utilization data
 * Idea       : NETutil uses an external library - sigar.jar which provides access
 * 				to the numerous system's information.
 * 				Here are collected and returned the following:
 * 					- TCP and complete inbound statistics
 * 					- TCP and complete outbound statistics
 **********************************************************************/
package DataGenerator;

import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.NetStat;

public class NETutil {
	private Sigar mySigar = new Sigar();

	int[] getNETInfo() throws SigarException {
		int nInboundTCP = 0, nOutboundTCP = 0, nInboundAll = 0, nOutboundAll = 0;

		try {
			NetStat myNetStat = mySigar.getNetStat();
			nInboundTCP = myNetStat.getTcpInboundTotal();
			nOutboundTCP = myNetStat.getTcpOutboundTotal();
			nInboundAll = myNetStat.getAllInboundTotal();
			nOutboundAll = myNetStat.getAllOutboundTotal();
		} catch (SigarException sEx) {
			throw sEx;
		}
		return new int[] {nInboundTCP, nOutboundTCP, nInboundAll, nOutboundAll};
	}
}