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
