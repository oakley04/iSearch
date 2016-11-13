package iSearch;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import com.google.gson.*;

public class EMR001 {

	public static String getToken() {
		String endpoint = "http://opensdk.emay.cn:9080/HD_GetAccess_Token.asmx";
		Call call;
		String res = "";
		Service service = new Service();
		String token = "";
		try {
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(endpoint));
			call.setOperationName(new QName("http://tempuri.org/", "GetACCESS_TOKEN"));
			call.addParameter(new QName("http://tempuri.org/", "AppID"), org.apache.axis.encoding.XMLType.XSD_STRING,
					ParameterMode.IN);
			call.addParameter(new QName("http://tempuri.org/", "AppSecret"),
					org.apache.axis.encoding.XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter(new QName("http://tempuri.org/", "Key"), org.apache.axis.encoding.XMLType.XSD_STRING,
					ParameterMode.IN);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setUseSOAPAction(true);
			call.setSOAPActionURI("http://tempuri.org/GetACCESS_TOKEN");
			String AppID = "67F5DC81WEA57W4632WA209WB570C24F4291";
			String AppSecret = "DD1F1E72L33DFL4153LA440L625A714D838B";
			String Key = "4661B22AH560DH475DHB032H05E028660330";
			res = (String) call.invoke(new Object[] { AppID, AppSecret, Key });

			//GetACCESS_TOKEN token1 = Gson
			
			JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
			token = jsonObject.get("access_token").getAsString();

		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return token;
	}

	public static void GetCreditRegistrationDetail(Object[] params) {
		String EMRUrl = "http://opensdk.emay.cn:9080/HD_Check/User_Credit.asmx";
		String res = "";
		try {
			Service service2 = new Service();
			Call call2 = (Call) service2.createCall();
			call2.setTargetEndpointAddress(new java.net.URL(EMRUrl));
			call2.setOperationName(new QName("http://tempuri.org/", "GetCreditRegistrationDetail"));
			call2.addParameter(new QName("http://tempuri.org/", "Phone"), org.apache.axis.encoding.XMLType.XSD_STRING,
					ParameterMode.IN);
			call2.addParameter(new QName("http://tempuri.org/", "cycle"), org.apache.axis.encoding.XMLType.XSD_STRING,
					ParameterMode.IN);
			call2.addParameter(new QName("http://tempuri.org/", "ACCESS_TOKEN"),
					org.apache.axis.encoding.XMLType.XSD_STRING, ParameterMode.IN);
			call2.addParameter(new QName("http://tempuri.org/", "Platform"),
					org.apache.axis.encoding.XMLType.XSD_STRING, ParameterMode.IN);
			call2.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call2.setUseSOAPAction(true);
			call2.setSOAPActionURI("http://tempuri.org/GetCreditRegistrationDetail");
			res = (String) call2.invoke(params);
			System.err.println("==13=>" + res);

		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	public static void main(String[] args) {
		String token = getToken();
		String Phone = "13161849642";
		String cycle = "24";
		String Platform = "0";
		// 1
		Object[] params1 = new Object[] { Phone, cycle, token, Platform };
		GetCreditRegistrationDetail(params1);
	}

}
