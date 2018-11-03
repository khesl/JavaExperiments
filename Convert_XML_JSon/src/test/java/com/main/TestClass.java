package com.main;

import org.json.JSONObject;
import org.json.XML;

public class TestClass {
    public static String test_text =
                    "<soapenv:Envelope\n" +
                            "\txmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                            "\t<soapenv:Body>\n" +
                            "\t\t<acb:GetDataResponse\n" +
                            "\t\t\txmlns:acb=\"http://srv-osb.acb.kz/Shtraf_search/\">\n" +
                            "\t\t\t<info>\n" +
                            "\t\t\t\t<ns4:SendMessageResponse\n" +
                            "\t\t\t\t\txmlns:ns4=\"http://bip.bee.kz/SyncChannel/v10/Types\">\n" +
                            "\t\t\t\t\t<response>\n" +
                            "\t\t\t\t\t\t<responseInfo>\n" +
                            "\t\t\t\t\t\t\t<messageId>a75d705c-0465-4042-a14c-ad544826cd58</messageId>\n" +
                            "\t\t\t\t\t\t\t<responseDate>2018-08-23T10:13:44.840+06:00</responseDate>\n" +
                            "\t\t\t\t\t\t\t<status>\n" +
                            "\t\t\t\t\t\t\t\t<code>SCSE001</code>\n" +
                            "\t\t\t\t\t\t\t\t<message>Поясняющее бизнес ошибку</message>\n" +
                            "\t\t\t\t\t\t\t</status>\n" +
                            "\t\t\t\t\t\t</responseInfo>\n" +
                            "\t\t\t\t\t\t<responseData>\n" +
                            "\t\t\t\t\t\t\t<data\n" +
                            "\t\t\t\t\t\t\t\txmlns:ns2=\"http://kz.asoft.ws.pgeg/commons.xsd\"\n" +
                            "\t\t\t\t\t\t\t\txmlns:ns3=\"http://kz.asoft.ws.pgeg/types\">\n" +
                            "\t\t\t\t\t\t\t\t<ns3:responseInfo>\n" +
                            "\t\t\t\t\t\t\t\t\t<ns2:GUID>32342208</ns2:GUID>\n" +
                            "\t\t\t\t\t\t\t\t\t<ns2:dateMessage>2018-08-23T10:13:44.840+06:00</ns2:dateMessage>\n" +
                            "\t\t\t\t\t\t\t\t\t<ns2:totalResults>0</ns2:totalResults>\n" +
                            "\t\t\t\t\t\t\t\t\t<ns2:processingResult>\n" +
                            "\t\t\t\t\t\t\t\t\t\t<ns2:code>404</ns2:code>\n" +
                            "\t\t\t\t\t\t\t\t\t\t<ns2:description_ru>Не удалось найти информацию по входным данным</ns2:description_ru>\n" +
                            "\t\t\t\t\t\t\t\t\t\t<ns2:description_kz>Белгіленген мәліметтер бойынша ақпарат жоқ</ns2:description_kz>\n" +
                            "\t\t\t\t\t\t\t\t\t\t<ns2:isAnswer>false</ns2:isAnswer>\n" +
                            "\t\t\t\t\t\t\t\t\t</ns2:processingResult>\n" +
                            "\t\t\t\t\t\t\t\t</ns3:responseInfo>\n" +
                            "\t\t\t\t\t\t\t</data>\n" +
                            "\t\t\t\t\t\t</responseData>\n" +
                            "\t\t\t\t\t</response>\n" +
                            "\t\t\t\t</ns4:SendMessageResponse>\n" +
                            "\t\t\t</info>\n" +
                            "\t\t</acb:GetDataResponse>\n" +
                            "\t</soapenv:Body>\n" +
                            "</soapenv:Envelope>";
    public static String test_text2 =
            "{\"results\":[\n" +
                    "\t{\t\"messageId\":\"2336346570553535032\",\n" +
                    "\t\t\"to\":\"77771652384\",\n" +
                    "\t\t\"from\":\"AsiaCred\",\n" +
                    "\t\t\"sentAt\":\"2018-08-07T09:37:37.057+0000\",\n" +
                    "\t\t\"doneAt\":\"2018-08-07T09:37:53.898+0000\",\n" +
                    "\t\t\"smsCount\":1,\n" +
                    "\t\t\"mccMnc\":\"null\",\n" +
                    "\t\t\"price\":\n" +
                    "\t\t{\t\"pricePerMessage\":0E-10,\n" +
                    "\t\t\t\"currency\":\"KZT\"\n" +
                    "\t\t},\n" +
                    "\t\t\"status\":\n" +
                    "\t\t{\t\"groupId\":3,\n" +
                    "\t\t\t\"groupName\":\"DELIVERED\",\n" +
                    "\t\t\t\"id\":5,\n" +
                    "\t\t\t\"name\":\"DELIVERED_TO_HANDSET\",\n" +
                    "\t\t\t\"description\":\"Message delivered to handset\"\n" +
                    "\t\t},\n" +
                    "\t\t\"error\":\n" +
                    "\t\t{\t\"groupId\":0,\n" +
                    "\t\t\t\"groupName\":\"OK\",\n" +
                    "\t\t\t\"id\":0,\n" +
                    "\t\t\t\"name\":\"NO_ERROR\",\n" +
                    "\t\t\t\"description\":\"No Error\",\n" +
                    "\t\t\t\"permanent\":false\n" +
                    "\t\t}\n" +
                    "\t}]\n" +
                    "}";

    public static void main(String[] args) {

        //System.out.println(Converter.xmltojson(test_text));

        JSONObject json = XML.toJSONObject(test_text);
        String json_ = json.toString();
        System.out.println(json_);

        //System.out.println(Converter.jsontoxml(test_text2));

    }
}
