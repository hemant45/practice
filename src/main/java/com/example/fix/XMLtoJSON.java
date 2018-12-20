package com.example.fix;

import org.json.JSONObject;
import org.json.XML;

public class XMLtoJSON {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String XML_TEXT = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<FIXML v=\"5.0 SP2\" s=\"20090815\" xv=\"109\" cv=\"CME.0001\">\r\n" + 
				"    <Batch>\r\n" + 
				"        <Hdr SID=\"CME\" TID=\"RYAN\" SSub=\"STP\" TSub=\"RYAN123\"/>\r\n" + 
				"        <TrdCaptRpt RptID=\"141FDE39DC6AP0001C27A105651377\"\r\n" + 
				"        ExecID=\"00000000000000000147\" TrdID2=\"141FDE39DC7AP0001C28A\"\r\n" + 
				"        TrdID=\"100296\" MtchID=\"141FDE39DC7AP0001C288\" LastPx=\"0.9534\"\r\n" + 
				"        TxnTm=\"2013-10-28T10:56:51-05:00\" TxnTm=\"2013-10-28T10:56:51-05:00\"\r\n" + 
				"        LastUpdateTm=\"2013-10-28T10:56:51-05:00\" TrdDt=\"2013-10-28\" BizDt=\"2013-10-28\"\r\n" + 
				"        TransTyp=\"0\" RptTyp=\"101\" TrdRptStat=\"0\" MLegRptTyp=\"1\"\r\n" + 
				"        ReqID=\"ABC123\" LastQty=\"3000000\" TrdTyp=\"0\" VenuTyp=\"P\">\r\n" + 
				"            <Instrmt ID=\"AD\" Src=\"H\" Sym=\"6AH4\" SecTyp=\"FUT\" CFI=\"FFCPSO\"\r\n" + 
				"            MMY=\"20140300\" MatDt=\"2014-03-17\" Exch=\"CME\" Mult=\"100000\"\r\n" + 
				"            PxQteCcy=\"USD\"/>\r\n" + 
				"            <RptSide Side=\"1\" ClOrdID=\"OOO5\" InptSrc=\"TES\" CustCpcty=\"4\">\r\n" + 
				"                <Pty ID=\"719\" R=\"1\"/>\r\n" + 
				"                <Pty ID=\"719\" R=\"4\"/>\r\n" + 
				"                <Pty ID=\"RYANTF\" R=\"7\">\r\n" + 
				"                    <Sub ID=\"Ryan Trading Firm\" Typ=\"5\"/>\r\n" + 
				"                </Pty>\r\n" + 
				"                <Pty ID=\"TET\" R=\"12\"/>\r\n" + 
				"                <Pty ID=\"CME\" R=\"21\"/>\r\n" + 
				"                <Pty ID=\"CME\" R=\"22\"/>   \r\n" + 
				"                <Pty ID=\"RYANACCOUNT\" R=\"24\">\r\n" + 
				"                    <Sub ID=\"2\" Typ=\"26\"/>\r\n" + 
				"                </Pty>                           \r\n" + 
				"            </RptSide>\r\n" + 
				"        </TrdCaptRpt>\r\n" + 
				"    </Batch>\r\n" + 
				"</FIXML>";
		
		
		JSONObject xmlJSONObj = XML.toJSONObject(XML_TEXT);
        String jsonPrettyPrintString = xmlJSONObj.toString(4);
        System.out.println(jsonPrettyPrintString);
	}

}
