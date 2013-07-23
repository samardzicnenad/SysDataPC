/**********************************************************************
 * Created by : Nenad Samardzic
 * Date       : 07/12/2013
 * Description: The class represents data model for CPU utilization data 
 * 				It also defines mapping to the related table in DataGenerator database
 **********************************************************************/
package datareporter

import java.sql.Timestamp

class CPUutil {
	static constraints = {
		tsTime()
		dUser()
		dSystem()
		dNice()
		dIdle()
		dWait()
		dIrq()
		dSoftIrq()
		dStolen()
		dCombined()
	}

	static mapping = {
		table "cpudata"
		version false
		columns {
			id column: "idCPUData"
			tsTime column: "Time"
			dUser column: "User"
			dSystem column: "System"
			dNice column: "Nice"
			dIdle column: "Idle"
			dWait column: "Wait"
			dIrq column: "Irq"
			dSoftIrq column: "SoftIrq"
			dStolen column: "Stolen"
			dCombined column: "Combined"
		}
	}

	double  dUser, dSystem, dNice, dIdle, dWait, dIrq, dSoftIrq, dStolen, dCombined
	Date tsTime
}