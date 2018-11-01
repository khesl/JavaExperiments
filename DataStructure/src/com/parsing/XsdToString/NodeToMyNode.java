package com.parsing.XsdToString;

import java.io.ByteArrayInputStream;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.xml.sax.SAXException;

public class NodeToMyNode {

    private static String text = "<ns2:CheckRequest xmlns:ns2=\"http://openaccount.com03.pep.nitec.kz\" xmlns:ns3=\"http://3005.reports.egp.gbdul.tamur.kz\" xmlns:ns4=\"http://www.w3.org/2000/09/xmldsig#\">\n" + 
    "    <ns2:Requestor>\n" + 
    "        <ns2:BIN>14</ns2:BIN>\n" + 
    "        <ns2:RequestNumber>17</ns2:RequestNumber>\n" + 
    "    </ns2:Requestor>\n" + 
    "    <ns2:RequestDetails>\n" + 
    "        <ns2:BankName>KZX</ns2:BankName>\n" + 
    "        <ns2:BranchName>KZT1</ns2:BranchName>\n" + 
    "        <ns2:MoneyType>KZT</ns2:MoneyType>\n" + 
    "    </ns2:RequestDetails>\n" + 
    "    <ns2:Gbdulfullinfo>\n" + 
    "        <FormOfLaw>20</FormOfLaw>\n" + 
    "        <CommerceOrg>true</CommerceOrg>\n" + 
    "        <TypicalCharter>true</TypicalCharter>\n" + 
    "        <Ownership>15</Ownership>\n" + 
    "        <EnterpriseSubj>false</EnterpriseSubj>\n" + 
    "        <PrivateEnterpriseType>\n" + 
    "			<Code>3</Code>\n" + 
    "			<DisplayRu>Субъект малого предпринимательства</DisplayRu>\n" + 
    "			<DisplayKz>Шағын кәсіпкерлік субъектісі</DisplayKz>\n" + 
    "		</PrivateEnterpriseType>\n" + 
    "        <ForeignInvest>false</ForeignInvest>\n" + 
    "        <registeringDepartment>1930-01</registeringDepartment>\n" + 
    "        <OKED>37000</OKED>\n" + 
    "        <leaderPerson>\n" + 
    "            <FIO>\n" + 
    "                <SurName>k</SurName>\n" + 
    "                <Name>РМ</Name>\n" + 
    "                <MiddleName>КЧ</MiddleName>\n" + 
    "            </FIO>\n" + 
    "            <Document>\n" + 
    "                <DocumentType>002</DocumentType>\n" + 
    "                <DocumentNumber>08</DocumentNumber>\n" + 
    "                <IssueDate>2000-10-01T00:00:00.000+06:00</IssueDate>\n" + 
    "                <IssuerId>1</IssuerId>\n" + 
    "            </Document>\n" + 
    "            <IIN>90</IIN>\n" + 
    "        </leaderPerson>\n" + 
    "        <orgFullNameRu>Товарищество с ограниченной ответственностью \"TestTest2\"</orgFullNameRu>\n" + 
    "        <orgFullNameKz>\"TestTest2\" жауапкершілігі шектеулі серіктестігі</orgFullNameKz>\n" + 
    "        <orgFullNameEn>\"TestTest2\" жауапкершілігі шектеулі серіктестігі</orgFullNameEn>\n" + 
    "        <jurAddress>\n" + 
    "            <BuildingNumber>41</BuildingNumber>\n" + 
    "            <AppartmentNumber>5225</AppartmentNumber>\n" + 
    "            <ZIPCode>010000</ZIPCode>\n" + 
    "            <KATO>311111</KATO>\n" + 
    "            <StreetTypeCode>24</StreetTypeCode>\n" + 
    "            <StreetCode>0003</StreetCode>\n" + 
    "        </jurAddress>\n" + 
    "        <IsRezident>true</IsRezident>\n" + 
    "        <IncCountry>398</IncCountry>\n" + 
    "        <RegDate>2017-10-16+06:00</RegDate>\n" + 
    "        <Agency>\n" + 
    "            <AgencyType>1</AgencyType>\n" + 
    "            <AgencyName>dsfsdsdf</AgencyName>\n" + 
    "        </Agency>\n" + 
    "    </ns2:Gbdulfullinfo>\n" + 
    "    <ns2:GBDFLResponse>\n" + 
    "        <iin>9112</iin>\n" + 
    "        <surname>БА</surname>\n" + 
    "        <name>КН</name>\n" + 
    "        <patronymic>СА</patronymic>\n" + 
    "        <birthDate>1994-05-31</birthDate>\n" + 
    "        <gender>\n" + 
    "            <code>2</code>\n" + 
    "            <nameRu>Женский</nameRu>\n" + 
    "            <nameKz>?йел</nameKz>\n" + 
    "            <changeDate>2013-09-19T09:44:33.000+06:00</changeDate>\n" + 
    "        </gender>\n" + 
    "        <nationality>\n" + 
    "            <code>005</code>\n" + 
    "            <nameRu>КАЗАХ</nameRu>\n" + 
    "            <nameKz>?АЗА?</nameKz>\n" + 
    "            <changeDate>2013-09-19T09:45:37.000+06:00</changeDate>\n" + 
    "        </nationality>\n" + 
    "        <citizenship>\n" + 
    "            <code>398</code>\n" + 
    "            <nameRu>КАЗАХСТАН</nameRu>\n" + 
    "            <nameKz>?АЗА?СТАН</nameKz>\n" + 
    "            <changeDate>2013-09-19T09:45:48.000+06:00</changeDate>\n" + 
    "        </citizenship>\n" + 
    "        <lifeStatus>\n" + 
    "            <code>0</code>\n" + 
    "            <nameRu>Нормальный</nameRu>\n" + 
    "            <nameKz>?алыпты</nameKz>\n" + 
    "            <changeDate>2013-09-19T09:45:30.000+06:00</changeDate>\n" + 
    "        </lifeStatus>\n" + 
    "        <birthPlace>\n" + 
    "            <country>\n" + 
    "                <code>398</code>\n" + 
    "                <nameRu>КАЗАХСТАН</nameRu>\n" + 
    "                <nameKz>?АЗА?СТАН</nameKz>\n" + 
    "                <changeDate>2013-09-19T09:45:48.000+06:00</changeDate>\n" + 
    "            </country>\n" + 
    "            <district>\n" + 
    "                <code>1902</code>\n" + 
    "                <nameRu>АКМОЛИНСКАЯ</nameRu>\n" + 
    "                <nameKz>А?МОЛА</nameKz>\n" + 
    "                <changeDate>2013-09-19T09:45:41.000+06:00</changeDate>\n" + 
    "            </district>\n" + 
    "            <region>\n" + 
    "                <code>1902212</code>\n" + 
    "                <nameRu>ЗЕРЕНДИНСКИЙ РАЙОН</nameRu>\n" + 
    "                <nameKz>ЗЕРЕНДІ АУДАНЫ</nameKz>\n" + 
    "                <changeDate>2013-09-19T09:45:26.000+06:00</changeDate>\n" + 
    "            </region>\n" + 
    "            <city>КЫЗЫЛТАН</city>\n" + 
    "        </birthPlace>\n" + 
    "        <regAddress>\n" + 
    "            <city>КЫН</city>\n" + 
    "            <street>ЖАСТАР</street>\n" + 
    "            <building>3</building>\n" + 
    "            <flat>2</flat>\n" + 
    "        </regAddress>\n" + 
    "        <disappearStatus>\n" + 
    "            <disappear>false</disappear>\n" + 
    "        </disappearStatus>\n" + 
    "        <excludeStatus/>\n" + 
    "        <documents>\n" + 
    "            <document>\n" + 
    "                <type>\n" + 
    "                    <code>002</code>\n" + 
    "                    <nameRu>УДОСТОВЕРЕНИЕ РК</nameRu>\n" + 
    "                    <nameKz>?Р ЖЕКЕ КУ?ЛІГІ</nameKz>\n" + 
    "                    <changeDate>2013-09-19T09:45:40.000+06:00</changeDate>\n" + 
    "                </type>\n" + 
    "                <beginDate>2011-02-05</beginDate>\n" + 
    "                <endDate>2021-02-04</endDate>\n" + 
    "                <issueOrganization>\n" + 
    "                    <code>001</code>\n" + 
    "                    <nameRu>МИНИСТЕРСТВО ЮСТИЦИИ РК</nameRu>\n" + 
    "                    <nameKz>?Р ?ДІЛЕТ МИНИСТРЛІГІ</nameKz>\n" + 
    "                    <changeDate>2013-09-19T09:45:40.000+06:00</changeDate>\n" + 
    "                </issueOrganization>\n" + 
    "                <status>\n" + 
    "                    <code>00</code>\n" + 
    "                    <nameRu>ДОКУМЕНТ ДЕЙСТВИТЕЛЕН</nameRu>\n" + 
    "                    <nameKz>??ЖАТ ЖАРАМДЫ</nameKz>\n" + 
    "                    <changeDate>2013-09-19T09:45:40.000+06:00</changeDate>\n" + 
    "                </status>\n" + 
    "                <surname>БУРКИТБАЕВА</surname>\n" + 
    "                <name>КОРЛАН</name>\n" + 
    "                <patronymic>СЕРИКОВНА</patronymic>\n" + 
    "                <birthDate>1994-05-31</birthDate>\n" + 
    "            </document>\n" + 
    "        </documents>\n" + 
    "        <removed>false</removed>\n" + 
    "    </ns2:GBDFLResponse>\n" + 
    "</ns2:CheckRequest>";

    public NodeToMyNode() {
        super();
    }

    public static void main(String[] args) {

        start(text);
    }

    public static void start(String request) {

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder documentBuilder;
            documentBuilder = dbf.newDocumentBuilder();
            Document doc;
            doc = documentBuilder.parse(new ByteArrayInputStream(request.getBytes("UTF-8")));

            Element rootEl = (Element)doc.getFirstChild();
            System.out.println(rootEl.getNodeName());
            MyNode myNode = new MyNode(rootEl);
            System.out.println(myNode.printNode());

        } catch (IOException e) {
        } catch (SAXException e) {
        } catch (ParserConfigurationException e) {
        }
    }
}
