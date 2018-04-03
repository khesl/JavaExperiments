package com.parsing.XsdToString;

import com.parsing.XsdToString.CheckDataTypes.Address;
import com.parsing.XsdToString.CheckDataTypes.Addresses;
import com.parsing.XsdToString.CheckDataTypes.CheckDataTypes;
import com.parsing.XsdToString.CheckDataTypes.CheckRequest;

import com.parsing.XsdToString.CheckDataTypes.Document;
import com.parsing.XsdToString.CheckDataTypes.FIO;
import com.parsing.XsdToString.CheckDataTypes.Founder;
import com.parsing.XsdToString.CheckDataTypes.Founders;

import com.parsing.XsdToString.CheckDataTypes.LeaderPerson;
import com.parsing.XsdToString.CheckDataTypes.LeaderPersons;
import com.parsing.XsdToString.CheckDataTypes.Organization;
import com.parsing.XsdToString.CheckDataTypes.Organizations;
import com.parsing.XsdToString.CheckDataTypes.Person;

import com.parsing.XsdToString.CheckDataTypes.Persons;
import com.parsing.XsdToString.CheckDataTypes.Status;

import com.parsing.XsdToString.CheckDataTypes.Statuses;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *  version 20.12.2017 last
 * */
public class ParserXsdToString {
    private static String requestData = "" +
        "<CheckRequest>\n" + 
        "    <Requestor>\n" + 
        "        <BIN>171040000144</BIN>\n" + 
        "        <RequestNumber>10109001090797</RequestNumber>\n" + 
        "    </Requestor>\n" + 
        "    <RequestDetails>\n" + 
        "        <BankName>KZKOKKZKX</BankName>\n" + 
        "        <BranchName>KZKOKKZKX_AST1</BranchName>\n" + 
        "        <MoneyType>KZT</MoneyType>\n" + 
        "    </RequestDetails>\n" + 
        "    <Gbdulfullinfo>\n" + 
        "        <FormOfLaw>20</FormOfLaw>\n" + 
        "        <CommerceOrg>true</CommerceOrg>\n" + 
        "        <TypicalCharter>true</TypicalCharter>\n" + 
        "        <Ownership>15</Ownership>\n" + 
        "        <EnterpriseSubj>false</EnterpriseSubj>\n" + 
        "        <PrivateEnterpriseType>\n" + 
        "			<Code>3</code>\n" + 
        "			<DisplayRu>Субъект малого предпринимательства</DisplayRu>\n" + 
        "			<DisplayKz>Шағын кәсіпкерлік субъектісі</DisplayKz>\n" + 
        "		</PrivateEnterpriseType>\n" + 
        "        <ForeignInvest>false</ForeignInvest>\n" + 
        "        <founders>\n" + 
        "            <founder>\n" + 
        "                <IINBIN>940531451013</IINBIN>\n" + 
        "                <legal>false</legal>\n" + 
        "                <Person>\n" + 
        "                    <FIO>\n" + 
        "                        <SurName>БУРКИТБАЕВА</SurName>\n" + 
        "                        <Name>КОРЛАН</Name>\n" + 
        "                        <MiddleName>СЕРИКОВНА</MiddleName>\n" + 
        "                    </FIO>\n" + 
        "                    <Document>\n" + 
        "                        <DocumentType>002</DocumentType>\n" + 
        "                        <DocumentNumber>030960768</DocumentNumber>\n" + 
        "                        <IssuerId>001</IssuerId>\n" + 
        "                    </Document>\n" + 
        "                    <IIN>940531451013</IIN>\n" + 
        "                </Person>\n" + 
        "                <BirthDate>2017-10-02+06:00</BirthDate>\n" + 
        "                <percentage>100.0</percentage>\n" + 
        "            </founder>\n" + 
        "        </founders>\n" + 
        "        <registeringDepartment>1930-01</registeringDepartment>\n" + 
        "        <OKED>37000</OKED>\n" + 
        "        <leaderPerson>\n" + 
        "            <FIO>\n" + 
        "                <SurName>ЖОЛДАБЕКОВ</SurName>\n" + 
        "                <Name>РУСТЕМ</Name>\n" + 
        "                <MiddleName>КАНАТОВИЧ</MiddleName>\n" + 
        "            </FIO>\n" + 
        "            <Document>\n" + 
        "                <DocumentType>002</DocumentType>\n" + 
        "                <DocumentNumber>035491618</DocumentNumber>\n" + 
        "                <IssueDate>2000-10-01T00:00:00.000+06:00</IssueDate>\n" + 
        "                <IssuerId>1</IssuerId>\n" + 
        "            </Document>\n" + 
        "            <IIN>950110350170</IIN>\n" + 
        "        </leaderPerson>\n" + 
        "        <orgFullNameRu>Товарищество с ограниченной ответственностью \"TestTest2\"</orgFullNameRu>\n" + 
        "        <orgFullNameKz>\"TestTest2\" жауапкершілігі шектеулі серіктестігі</orgFullNameKz>\n" + 
        "        <orgFullNameEn>\"TestTest2\" жауапкершілігі шектеулі серіктестігі</orgFullNameEn>\n" + 
        "        <jurAddress>\n" + 
        "            <BuildingNumber>46</BuildingNumber>\n" + 
        "            <AppartmentNumber>52</AppartmentNumber>\n" + 
        "            <ZIPCode>010000</ZIPCode>\n" + 
        "            <KATO>3510111001</KATO>\n" + 
        "            <StreetTypeCode>24</StreetTypeCode>\n" + 
        "            <StreetCode>002803</StreetCode>\n" + 
        "        </jurAddress>\n" + 
        "        <IsRezident>true</IsRezident>\n" + 
        "        <IncCountry>398</IncCountry>\n" + 
        "        <RegDate>2017-10-16+06:00</RegDate>\n" + 
        "        <Agency>\n" + 
        "            <AgencyType>1</AgencyType>\n" + 
        "            <AgencyName>dsfsdsdf</AgencyName>\n" + 
        "        </Agency>\n" + 
        "    </Gbdulfullinfo>\n" + 
        "    <GBDFLResponse>\n" + 
        "        <iin>940531451013</iin>\n" + 
        "        <surname>БУРКИТБАЕВА</surname>\n" + 
        "        <name>КОРЛАН</name>\n" + 
        "        <patronymic>СЕРИКОВНА</patronymic>\n" + 
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
        "            <city>КЫЗЫЛТАН</city>\n" + 
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
        "    </GBDFLResponse>\n" + 
        "</CheckRequest>";
        
    public ParserXsdToString() {
        super();
    }

    public static void main(String[] args) {
        //ParserXsdToString parserXsdToString = new ParserXsdToString();
        CheckRequest request = parseStringToObject(requestData);
        
        //String out = parseObjectToString(request);
        //System.out.println(out);
    }
    
    public static CheckRequest parseStringToObject(String data){
        MyNode node = parseDexstra(data);
        
        
        CheckRequest request = createRootStringToObject(node); //new CheckRequest();  //
        
        System.out.println(node.printNode());
        //System.out.println(request.getNameRu());
        return request;
    }
    public static String parseObjectToString(CheckRequest request){
        /*CheckRequest request = new CheckRequest();
        Founders founders = new Founders();
        Founder founder = new Founder();
        founder.setFounderFIO("Абдиров  Нуркен   Мухтарович");
        founder.setIINBIN("840827302969");
        founder.setFounderBurnDate(b("1984-08-27"));
        founders.getFounder().add(founder);

        Founder founder2 = new Founder();
        founder2.setFounderFIO("Умаров  Арлан   Бектимурович");
        founder2.setIINBIN("810420301754");
        founder2.setFounderBurnDate(b("1981-04-20"));
        founders.getFounder().add(founder2);
        
        Founder founder3 = new Founder();
        founder3.setFounderFIO("Сергазин    Сергей  Бегалыевич");
        founder3.setIINBIN("771230300042");
        founder3.setFounderBurnDate(b("1977-12-30"));
        Address add2 = new Address();
        add2.setCityRu("kjhgjg");
        add2.setBuildingNumber("kjhkj");
        founder3.setAddress(add2);
        founders.getFounder().add(founder3);
        
        request.setNameRu("Наименование организации");
        request.setFounders(founders);
        request.setIINBIN("771230300042");
        request.setRegDate("1981-04-20");*/
        
        MyNode newNode = createRootObjectToString("CheckRequest", request);
        
        return newNode.printNode(); 
    }
    
    /** Functions for create Object element
     * */    
    private static CheckRequest createRootStringToObject(MyNode node){    
        CheckRequest parent = new CheckRequest();
        MyNode parentNode = node.getChild("CheckRequest");
        for (CheckDataTypes.CheckRequestData val : CheckDataTypes.CheckRequestData.values()){
            if (val.isIsSimple()){
                Object value = null;
                MyNode destNode = parentNode.getChild(val.toString());
                if (destNode != null){
                    value = destNode.getValue();
                    parent = val.set(parent, value);  
                }
            }
        }
        if (parentNode.getChild("Founders") != null) {
            parent = setObjectFoundersArray(parentNode, parent, "Founders", "Founder");
        }
        if (parentNode.getChild("Persons") != null) {
            parent = setObjectPersonsArray(parentNode, parent, "Persons", "Person");
        }
        if (parentNode.getChild("Addresses") != null) {
            parent = setObjectAddressesArray(parentNode, parent, "Addresses", "Address");
        }
        if (parentNode.getChild("Statuses") != null) {
            parent = setObjectStatusesArray(parentNode, parent, "Statuses", "Status");
        }
        if (parentNode.getChild("LeaderPersons") != null) {
            parent = setObjectLeaderPersonsArray(parentNode, parent, "LeaderPersons", "LeaderPerson");
        }
        if (parentNode.getChild("Organizations") != null) {
            parent = setObjectOrganizationsArray(parentNode, parent, "Organizations", "Organization");
        }
    return parent;
    }

    private static CheckRequest setObjectFoundersArray(MyNode rootNode, CheckRequest object, String parent, String child){
        MyNode node = rootNode.getChild("Founders");
        if (!node.isEmpty()){ 
            Founders arrays = new Founders();
            for (MyNode childNode : node){
                Founder array = new Founder();
                for (CheckDataTypes.FounderData val : CheckDataTypes.FounderData.values()){
                    if (val.isIsSimple()){
                        Object value = null;
                        MyNode destNode = childNode.getChild(val.toString());
                        if (destNode != null){
                            value = destNode.getValue();
                            array = val.set(array, value);
                        }
                    }
                }
                array = setComplexObjectAddressType(childNode, array, "Founder", "Address");
                arrays.getFounder().add(array);
            }
            object.setFounders(arrays);
        }
        return object;
    }

    private static Founder setComplexObjectAddressType(MyNode rootNode, Founder object, String parent, String child){
        MyNode node = rootNode.getChild("Address");
        if (node != null) if (!node.isEmpty()){ 
            Address array = new Address();
            for (CheckDataTypes.AddressData val : CheckDataTypes.AddressData.values()){
                if (val.isIsSimple()){
                    Object value = null;
                    MyNode destNode = node.getChild(val.toString());
                    if (destNode != null){
                        value = destNode.getValue();
                        array = val.set(array, value);
                    }
                }
            }
            object.setAddress(array);
        }
        return object;
    }

    private static CheckRequest setObjectPersonsArray(MyNode rootNode, CheckRequest object, String parent, String child){
        MyNode node = rootNode.getChild("Persons");
        if (!node.isEmpty()){ 
            Persons arrays = new Persons();
            for (MyNode childNode : node){
                Person array = new Person();
                for (CheckDataTypes.PersonData val : CheckDataTypes.PersonData.values()){
                    if (val.isIsSimple()){
                        Object value = null;
                        MyNode destNode = childNode.getChild(val.toString());
                        if (destNode != null){
                            value = destNode.getValue();
                            array = val.set(array, value);
                        }
                    }
                }
                array = setComplexObjectAddressType(childNode, array, "Person", "Address");
                array = setComplexObjectDocumentType(childNode, array, "Person", "Document");
                array = setComplexObjectFIOType(childNode, array, "Person", "FIO");
                arrays.getPerson().add(array);
            }
            object.setPersons(arrays);
        }
        return object;
    }

    private static Person setComplexObjectAddressType(MyNode rootNode, Person object, String parent, String child){
        MyNode node = rootNode.getChild("Address");
        if (node != null) if (!node.isEmpty()){ 
            Address array = new Address();
            for (CheckDataTypes.AddressData val : CheckDataTypes.AddressData.values()){
                if (val.isIsSimple()){
                    Object value = null;
                    MyNode destNode = node.getChild(val.toString());
                    if (destNode != null){
                        value = destNode.getValue();
                        array = val.set(array, value);
                    }
                }
            }
            object.setAddress(array);
        }
        return object;
    }

    private static Person setComplexObjectDocumentType(MyNode rootNode, Person object, String parent, String child){
        MyNode node = rootNode.getChild("Document");
        if (node != null) if (!node.isEmpty()){ 
            Document array = new Document();
            for (CheckDataTypes.DocumentData val : CheckDataTypes.DocumentData.values()){
                if (val.isIsSimple()){
                    Object value = null;
                    MyNode destNode = node.getChild(val.toString());
                    if (destNode != null){
                        value = destNode.getValue();
                        array = val.set(array, value);
                    }
                }
            }
            object.setDocument(array);
        }
        return object;
    }


    private static Person setComplexObjectFIOType(MyNode rootNode, Person object, String parent, String child){
        MyNode node = rootNode.getChild("FIO");
        if (node != null) if (!node.isEmpty()){ 
            FIO array = new FIO();
            for (CheckDataTypes.FIOData val : CheckDataTypes.FIOData.values()){
                if (val.isIsSimple()){
                    Object value = null;
                    MyNode destNode = node.getChild(val.toString());
                    if (destNode != null){
                        value = destNode.getValue();
                        array = val.set(array, value);
                    }
                }
            }
            object.setFIO(array);
        }
        return object;
    }


    private static CheckRequest setObjectAddressesArray(MyNode rootNode, CheckRequest object, String parent, String child){
        MyNode node = rootNode.getChild("Addresses");
        if (!node.isEmpty()){ 
            Addresses arrays = new Addresses();
            for (MyNode childNode : node){
                Address array = new Address();
                for (CheckDataTypes.AddressData val : CheckDataTypes.AddressData.values()){
                    if (val.isIsSimple()){
                        Object value = null;
                        MyNode destNode = childNode.getChild(val.toString());
                        if (destNode != null){
                            value = destNode.getValue();
                            array = val.set(array, value);
                        }
                    }
                }
                arrays.getAddress().add(array);
            }
            object.setAddresses(arrays);
        }
        return object;
    }

    private static CheckRequest setObjectStatusesArray(MyNode rootNode, CheckRequest object, String parent, String child){
        MyNode node = rootNode.getChild("Statuses");
        if (!node.isEmpty()){ 
            Statuses arrays = new Statuses();
            for (MyNode childNode : node){
                Status array = new Status();
                for (CheckDataTypes.StatusData val : CheckDataTypes.StatusData.values()){
                    if (val.isIsSimple()){
                        Object value = null;
                        MyNode destNode = childNode.getChild(val.toString());
                        if (destNode != null){
                            value = destNode.getValue();
                            array = val.set(array, value);
                        }
                    }
                }
                arrays.getStatus().add(array);
            }
            object.setStatuses(arrays);
        }
        return object;
    }

    private static CheckRequest setObjectLeaderPersonsArray(MyNode rootNode, CheckRequest object, String parent, String child){
        MyNode node = rootNode.getChild("LeaderPersons");
        if (!node.isEmpty()){ 
            LeaderPersons arrays = new LeaderPersons();
            for (MyNode childNode : node){
                LeaderPerson array = new LeaderPerson();
                for (CheckDataTypes.LeaderPersonData val : CheckDataTypes.LeaderPersonData.values()){
                    if (val.isIsSimple()){
                        Object value = null;
                        MyNode destNode = childNode.getChild(val.toString());
                        if (destNode != null){
                            value = destNode.getValue();
                            array = val.set(array, value);
                        }
                    }
                }
                array = setComplexObjectDocumentType(childNode, array, "LeaderPerson", "Document");
                array = setComplexObjectFIOType(childNode, array, "LeaderPerson", "FIO");
                arrays.getLeaderPerson().add(array);
            }
            object.setLeaderPersons(arrays);
        }
        return object;
    }

    private static LeaderPerson setComplexObjectDocumentType(MyNode rootNode, LeaderPerson object, String parent, String child){
        MyNode node = rootNode.getChild("Document");
        if (node != null) if (!node.isEmpty()){ 
            Document array = new Document();
            for (CheckDataTypes.DocumentData val : CheckDataTypes.DocumentData.values()){
                if (val.isIsSimple()){
                    Object value = null;
                    MyNode destNode = node.getChild(val.toString());
                    if (destNode != null){
                        value = destNode.getValue();
                        array = val.set(array, value);
                    }
                }
            }
            object.setDocument(array);
        }
        return object;
    }


    private static LeaderPerson setComplexObjectFIOType(MyNode rootNode, LeaderPerson object, String parent, String child){
        MyNode node = rootNode.getChild("FIO");
        if (node != null) if (!node.isEmpty()){ 
            FIO array = new FIO();
            for (CheckDataTypes.FIOData val : CheckDataTypes.FIOData.values()){
                if (val.isIsSimple()){
                    Object value = null;
                    MyNode destNode = node.getChild(val.toString());
                    if (destNode != null){
                        value = destNode.getValue();
                        array = val.set(array, value);
                    }
                }
            }
            object.setFIO(array);
        }
        return object;
    }


    private static CheckRequest setObjectOrganizationsArray(MyNode rootNode, CheckRequest object, String parent, String child){
        MyNode node = rootNode.getChild("Organizations");
        if (!node.isEmpty()){ 
            Organizations arrays = new Organizations();
            for (MyNode childNode : node){
                Organization array = new Organization();
                for (CheckDataTypes.OrganizationData val : CheckDataTypes.OrganizationData.values()){
                    if (val.isIsSimple()){
                        Object value = null;
                        MyNode destNode = childNode.getChild(val.toString());
                        if (destNode != null){
                            value = destNode.getValue();
                            array = val.set(array, value);
                        }
                    }
                }
                arrays.getOrganization().add(array);
            }
            object.setOrganizations(arrays);
        }
        return object;
    }






    
    
    /** Functions for create MyNode element
     * */    
     private static MyNode createRootObjectToString(String rootElement, CheckRequest parent){
         MyNode newNode = new MyNode(rootElement);
         for (CheckDataTypes.CheckRequestData val : CheckDataTypes.CheckRequestData.values())
             if (val.isIsSimple()) newNode.addChild(new MyNode(val.toString(), val.get(parent)));
         if (parent.getOrganizations() != null) {
             MyNode parentNode = new MyNode("Organizations");
             setOrganizationsArray(parentNode, parent, "Organizations", "Organization");
             newNode.addChild(parentNode);
         }
         if (parent.getPersons() != null) {
             MyNode parentNode = new MyNode("Persons");
             setPersonsArray(parentNode, parent, "Persons", "Person");
             newNode.addChild(parentNode);
         }
         if (parent.getFounders() != null) {
             MyNode parentNode = new MyNode("Founders");
             setFoundersArray(parentNode, parent, "Founders", "Founder");
             newNode.addChild(parentNode);
         }
         if (parent.getStatuses() != null) {
             MyNode parentNode = new MyNode("Statuses");
             setStatusesArray(parentNode, parent, "Statuses", "Status");
             newNode.addChild(parentNode);
         }
         if (parent.getLeaderPersons() != null) {
             MyNode parentNode = new MyNode("LeaderPersons");
             setLeaderPersonsArray(parentNode, parent, "LeaderPersons", "LeaderPerson");
             newNode.addChild(parentNode);
         }
         if (parent.getAddresses() != null) {
             MyNode parentNode = new MyNode("Addresses");
             setAddressesArray(parentNode, parent, "Addresses", "Address");
             newNode.addChild(parentNode);
         }
     return newNode;
     }
     private static MyNode setOrganizationsArray(MyNode node, CheckRequest object, String parent, String child){
         if (object.getOrganizations() != null)
             if (object.getOrganizations().getOrganization().size() > 0)
                 for (Organization f : object.getOrganizations().getOrganization()){
                     MyNode childNode = new MyNode(child);
                     for (CheckDataTypes.OrganizationData val : CheckDataTypes.OrganizationData.values())
                     if (val.isIsSimple())childNode.addChild(new MyNode(val.toString(), val.get(f)));
                     node.addChild(childNode);
                 }
         return node;
     }
     private static MyNode setPersonsArray(MyNode node, CheckRequest object, String parent, String child){
         if (object.getPersons() != null)
             if (object.getPersons().getPerson().size() > 0)
                 for (Person f : object.getPersons().getPerson()){
                     MyNode childNode = new MyNode(child);
                     for (CheckDataTypes.PersonData val : CheckDataTypes.PersonData.values())
                     if (val.isIsSimple())childNode.addChild(new MyNode(val.toString(), val.get(f)));
                     setComplexAddresstype(childNode, f, "Address");
                     setComplexFIOtype(childNode, f, "FIO");
                     setComplexDocumenttype(childNode, f, "Document");
                     node.addChild(childNode);
                 }
         return node;
     }
     private static MyNode setComplexAddresstype(MyNode node, Person object, String child){
         if (object.getAddress() != null) {
             MyNode childNode = new MyNode("Address");
             Address add = object.getAddress();
             for (CheckDataTypes.AddressData val : CheckDataTypes.AddressData.values())
                 if (val.isIsSimple()) childNode.addChild(new MyNode(val.toString(), val.get(add)));
         node.addChild(childNode);
         }
         return node;
     }
     private static MyNode setComplexFIOtype(MyNode node, Person object, String child){
         if (object.getFIO() != null) {
             MyNode childNode = new MyNode("FIO");
             FIO add = object.getFIO();
             for (CheckDataTypes.FIOData val : CheckDataTypes.FIOData.values())
                 if (val.isIsSimple()) childNode.addChild(new MyNode(val.toString(), val.get(add)));
         node.addChild(childNode);
         }
         return node;
     }
     private static MyNode setComplexDocumenttype(MyNode node, Person object, String child){
         if (object.getDocument() != null) {
             MyNode childNode = new MyNode("Document");
             Document add = object.getDocument();
             for (CheckDataTypes.DocumentData val : CheckDataTypes.DocumentData.values())
                 if (val.isIsSimple()) childNode.addChild(new MyNode(val.toString(), val.get(add)));
         node.addChild(childNode);
         }
         return node;
     }
     private static MyNode setFoundersArray(MyNode node, CheckRequest object, String parent, String child){
         if (object.getFounders() != null)
             if (object.getFounders().getFounder().size() > 0)
                 for (Founder f : object.getFounders().getFounder()){
                     MyNode childNode = new MyNode(child);
                     for (CheckDataTypes.FounderData val : CheckDataTypes.FounderData.values())
                     if (val.isIsSimple())childNode.addChild(new MyNode(val.toString(), val.get(f)));
                     setComplexAddresstype(childNode, f, "Address");
                     node.addChild(childNode);
                 }
         return node;
     }
     private static MyNode setComplexAddresstype(MyNode node, Founder object, String child){
         if (object.getAddress() != null) {
             MyNode childNode = new MyNode("Address");
             Address add = object.getAddress();
             for (CheckDataTypes.AddressData val : CheckDataTypes.AddressData.values())
                 if (val.isIsSimple()) childNode.addChild(new MyNode(val.toString(), val.get(add)));
         node.addChild(childNode);
         }
         return node;
     }
     private static MyNode setStatusesArray(MyNode node, CheckRequest object, String parent, String child){
         if (object.getStatuses() != null)
             if (object.getStatuses().getStatus().size() > 0)
                 for (Status f : object.getStatuses().getStatus()){
                     MyNode childNode = new MyNode(child);
                     for (CheckDataTypes.StatusData val : CheckDataTypes.StatusData.values())
                     if (val.isIsSimple())childNode.addChild(new MyNode(val.toString(), val.get(f)));
                     node.addChild(childNode);
                 }
         return node;
     }
     private static MyNode setLeaderPersonsArray(MyNode node, CheckRequest object, String parent, String child){
         if (object.getLeaderPersons() != null)
             if (object.getLeaderPersons().getLeaderPerson().size() > 0)
                 for (LeaderPerson f : object.getLeaderPersons().getLeaderPerson()){
                     MyNode childNode = new MyNode(child);
                     for (CheckDataTypes.LeaderPersonData val : CheckDataTypes.LeaderPersonData.values())
                     if (val.isIsSimple())childNode.addChild(new MyNode(val.toString(), val.get(f)));
                     setComplexFIOtype(childNode, f, "FIO");
                     setComplexDocumenttype(childNode, f, "Document");
                     node.addChild(childNode);
                 }
         return node;
     }
     private static MyNode setComplexFIOtype(MyNode node, LeaderPerson object, String child){
         if (object.getFIO() != null) {
             MyNode childNode = new MyNode("FIO");
             FIO add = object.getFIO();
             for (CheckDataTypes.FIOData val : CheckDataTypes.FIOData.values())
                 if (val.isIsSimple()) childNode.addChild(new MyNode(val.toString(), val.get(add)));
         node.addChild(childNode);
         }
         return node;
     }
     private static MyNode setComplexDocumenttype(MyNode node, LeaderPerson object, String child){
         if (object.getDocument() != null) {
             MyNode childNode = new MyNode("Document");
             Document add = object.getDocument();
             for (CheckDataTypes.DocumentData val : CheckDataTypes.DocumentData.values())
                 if (val.isIsSimple()) childNode.addChild(new MyNode(val.toString(), val.get(add)));
         node.addChild(childNode);
         }
         return node;
     }
     private static MyNode setAddressesArray(MyNode node, CheckRequest object, String parent, String child){
         if (object.getAddresses() != null)
             if (object.getAddresses().getAddress().size() > 0)
                 for (Address f : object.getAddresses().getAddress()){
                     MyNode childNode = new MyNode(child);
                     for (CheckDataTypes.AddressData val : CheckDataTypes.AddressData.values())
                     if (val.isIsSimple())childNode.addChild(new MyNode(val.toString(), val.get(f)));
                     node.addChild(childNode);
                 }
         return node;
     }
     
    
    /** Parce sting entry to MyNode structure
     * */
    public static MyNode parseDexstra(String string) {
        string = fetchNormalize(string);
        MyNode rootNode = new MyNode("root", null, string);        
        rootNode = parseDextra(rootNode);
        return rootNode;
    }

    private static MyNode parseDextra(MyNode root) {
        if (root.getCurrentString().length() == 0) return root;
        root.setCurrentString(delSpace(root.getCurrentString()));
        //System.out.println("\t" + "current string: '" + root.getCurrentString() + "', and node: " + root);
        int left = root.getCurrentString().indexOf("<");
        int right = root.getCurrentString().indexOf(">", left);
        //System.out.println("'<:" + left + "' '>':" + right + "'");
        //System.out.println(root.getCurrentString().subSequence(left, right+1));
        if (left == 0 &&
            root.getCurrentString().substring(left, left + 2).equals("</")) {
            //nodeStack.pop();
            //System.out.println("pop old1: " + root);
            return root;
        }
        if (left == 0) {
            boolean isEnd = false;
            String endName = "";
            do {
                //System.out.println("new cycle");
                if (root.getCurrentString().length() == 0) return root;
                if (isEmptyNode(root.getCurrentString().substring(left, right + 1))) {
                    endName = root.getCurrentString().substring(left, right+1);
                } else {
                if (isEndNode(root.getCurrentString().substring(left, right + 1)) && root.getName().equals(getClearName(root.getCurrentString().substring(left, right + 1)))){
                    //nodeStack.pop();
                    //System.out.println("pop old1: " + root);
                    break;
                }
                //nodeStack.push(root.getCurrentString().substring(left + 1, right));
               // System.out.println("push new: " + root.getCurrentString().substring(left + 1, right));
                String nameChild = root.getCurrentString().substring(left + 1, right);
                MyNode child = new MyNode(nameChild, null, root.getCurrentString());
                //System.out.println("new childNode " + child + ", for " + root);
                child.setCurrentString(root.getCurrentString().substring(right + 1));
                child = parseDextra(child);
                root.addChild(child);
                root.setCurrentString(child.getCurrentString());
                //System.out.println(root + ", size " + root.size() + ", last node " + root.getLast());
                
                //System.out.println("return, current string: '" + root.getCurrentString() + "'");
                left = root.getCurrentString().indexOf("<");
                right = root.getCurrentString().indexOf(">", left);
                if (left != -1 && right != -1) endName = root.getCurrentString().substring(left, right+1);
                }
                //System.out.println(root.name + " = " + endName + " " + root.name.equals(getClearName(endName)));
                isEnd = root.getName().equals(getClearName(endName));
                
                root.setCurrentString(root.getCurrentString().substring(right + 1));
                //System.out.println("   new return, current string: '" + root.getCurrentString() + "'");
                left = root.getCurrentString().indexOf("<");
                right = root.getCurrentString().indexOf(">", left);
                //System.out.println(">>> new string: '" + root.getCurrentString() + "'");
            } while (!(isEndNode(endName) && isEnd));
            //System.out.println("Exit while ---");
            return root;
        } else{
            root.setValue(root.getCurrentString().substring(0, left));
            //System.out.println("set new value: " + root.getCurrentString().substring(0, left));
            root.setCurrentString(root.getCurrentString().substring(left));
            //System.out.println("return, current string: '" + root.getCurrentString() + "'");
        }
        left = root.getCurrentString().indexOf("<");
        right = root.getCurrentString().indexOf(">", left);

        //System.out.println("     " + root + ", and " + root.getCurrentString().substring(left, right+1));
        //System.out.println(root.name + " = " + root.getCurrentString().substring(left, right+1) + " " + root.name.equals(getClearName(root.getCurrentString().substring(left, right+1))));
        if (!root.getName().equals(getClearName(root.getCurrentString().substring(left, right+1)))){
            root.setCurrentString(root.getCurrentString().substring(right + 1));
            root = parseDextra(root);
        }
        return root;
    }

    private static String fetchNormalize(String response) {
        response = response.replaceAll("&lt;", "<");
        response = response.replaceAll("&gt;", ">");
        return response;
    }
    private static String getClearName(String str) {
        str = str.replace("<", "");
        str = str.replace(">", "");
        str = str.replace("/", "");
        return str;
    }
    private static boolean isEndNode(String str){
        return (str.contains("</") && str.contains(">"));
    }
    private static boolean isEmptyNode(String str){
        return (str.contains("<") && str.contains("/>"));
    }
    private static String delSpace(String string){
        while (string.substring(0, 1).equals(" ") || string.substring(0, 1).equals("\n")) string = string.substring(1);
        return string;
    }
    
    private static XMLGregorianCalendar b(String value){
        String[] values = value.split("-");
        XMLGregorianCalendar x = null;
        try {
            x = DatatypeFactory.newInstance().newXMLGregorianCalendar();
            x.setDay(Integer.valueOf(values[2]));
            x.setMonth(Integer.valueOf(values[1]));
            x.setYear(Integer.valueOf(values[0]));
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        return x;
    }

}
