/**********************************************************************
 * Created by : Nenad Samardzic
 * Date       : 07/12/2013
 * Description: The class represents data model for network utilization data 
 * 				It also defines mapping to the related table in DataGenerator database
 **********************************************************************/
package datareporter

import java.sql.Timestamp

class NETutil {
	static constraints = {
		tsTime()
		nInboundTCP()
		nOutboundTCP()
		nInboundAll()
		nOutboundAll()
	}
	
	static mapping = {
		table "netdata"
		version false
		columns {
			id column: "idNETData"
			tsTime column: "Time"
			nInboundTCP column: "InboundTCP"
			nOutboundTCP column: "OutboundTCP"
			nInboundAll column: "InboundAll"
			nOutboundAll column: "OutboundAll"
		}
	}

	int nInboundTCP, nOutboundTCP, nInboundAll, nOutboundAll
	Date tsTime
}
