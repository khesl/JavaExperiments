package com.parsing.XsdToString.CheckDataTypes;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.DatatypeConfigurationException;

/**
 * This code generated from .java files in directory
 * C:\Users\vassina\Desktop\project\JavaExperiments\DataStructure\src\com\parsing\XsdToString\CheckDataTypes
 * ------------------------------------------------------
 *    Address.java
 *    Addresses.java
 *    CheckDataTypes.java
 *    CheckRequest.java
 *    CheckResponse.java
 *    DataResponse.java
 *    Document.java
 *    FIO.java
 *    Founder.java
 *    Founders.java
 *    LeaderPerson.java
 *    LeaderPersons.java
 *    Organization.java
 *    Organizations.java
 *    Person.java
 *    Persons.java
 *    Status.java
 *    Statuses.java
 * 
 * Generated from 'MyEnumGenerator'
 * created by Vassin Andrey 2017 (c)
 */
public class CheckDataTypes {
    private static final String[] complexElement = {
        "Person Address false",
        "LeaderPerson Document false",
        "Founder Address false",
        "Founders Founder false",
        "Organizations Organization false",
        "Addresses Address false",
        "Persons Person false",
        "Statuses Status false",
        "Person FIO false",
        "CheckRequest Statuses true",
        "LeaderPersons LeaderPerson false",
        "CheckRequest Founders true",
        "CheckResponse DataResponse false",
        "Person Document false",
        "CheckRequest Addresses true",
        "CheckRequest Organizations true",
        "CheckRequest LeaderPersons true",
        "CheckRequest Persons true",
        "LeaderPerson FIO false"};


/**
 * This code generated from .java file
 * Address.java
 * ------------------------------------------------------
 * <p>Enum class for complex @Xml type.
 * 
 * @XmlType(name = "Address")
 * @XmlAccessorType(name = "FIELD")
 * @rapam	Country		typeOf(String)
 * @rapam	ZIPCode		typeOf(String)
 * @rapam	DistrictRu		typeOf(String)
 * @rapam	RegionRu		typeOf(String)
 * @rapam	CityRu		typeOf(String)
 * @rapam	StreetRu		typeOf(String)
 * @rapam	BuildingNumber		typeOf(String)
 * @rapam	CorpusNumber		typeOf(String)
 * @rapam	AppartmentNumber		typeOf(String)
 * 
 * Generated from 'MyEnumGenerator'
 */
    public enum AddressData {
        Country {
            public Object get(Address obj) {
                return obj.getCountry();
            }
            public Address set(Address obj, Object value) {
                obj.setCountry(String.valueOf(value));
                return obj;
            }
        },
        ZIPCode {
            public Object get(Address obj) {
                return obj.getZIPCode();
            }
            public Address set(Address obj, Object value) {
                obj.setZIPCode(String.valueOf(value));
                return obj;
            }
        },
        DistrictRu {
            public Object get(Address obj) {
                return obj.getDistrictRu();
            }
            public Address set(Address obj, Object value) {
                obj.setDistrictRu(String.valueOf(value));
                return obj;
            }
        },
        RegionRu {
            public Object get(Address obj) {
                return obj.getRegionRu();
            }
            public Address set(Address obj, Object value) {
                obj.setRegionRu(String.valueOf(value));
                return obj;
            }
        },
        CityRu {
            public Object get(Address obj) {
                return obj.getCityRu();
            }
            public Address set(Address obj, Object value) {
                obj.setCityRu(String.valueOf(value));
                return obj;
            }
        },
        StreetRu {
            public Object get(Address obj) {
                return obj.getStreetRu();
            }
            public Address set(Address obj, Object value) {
                obj.setStreetRu(String.valueOf(value));
                return obj;
            }
        },
        BuildingNumber {
            public Object get(Address obj) {
                return obj.getBuildingNumber();
            }
            public Address set(Address obj, Object value) {
                obj.setBuildingNumber(String.valueOf(value));
                return obj;
            }
        },
        CorpusNumber {
            public Object get(Address obj) {
                return obj.getCorpusNumber();
            }
            public Address set(Address obj, Object value) {
                obj.setCorpusNumber(String.valueOf(value));
                return obj;
            }
        },
        AppartmentNumber {
            public Object get(Address obj) {
                return obj.getAppartmentNumber();
            }
            public Address set(Address obj, Object value) {
                obj.setAppartmentNumber(String.valueOf(value));
                return obj;
            }
        };

        private boolean isSimple;
        private boolean isArray;

        private AddressData() {
            this.isSimple = true;
            this.isArray = false;
        }
        private AddressData(boolean isSimple) {
            this(isSimple, false);
        }
        private AddressData(boolean isSimple, boolean isArray) {
            this.isSimple = isSimple;
            this.isArray = isArray;
        }

        public abstract Object get(Address obj);

        public abstract Address set(Address obj, Object value);

        public boolean isIsSimple() {
            return isSimple;
        }
        public boolean isIsArray() {
            return isArray;
        }
    }


/**
 * This code generated from .java file
 * Addresses.java
 * ------------------------------------------------------
 * <p>Enum class for complex @Xml type.
 * 
 * @XmlType(name = "Addresses")
 * @XmlAccessorType(name = "FIELD")
 * @rapam	Address		typeOf(List<Address>)
 * 
 * Generated from 'MyEnumGenerator'
 */
    public enum AddressesData {
        Address (false) {
            public Object get(Addresses obj) {
                return obj.getAddress();
            }
            public Addresses set(Addresses obj, Object value) {
                obj.getAddress().add((Address)value);
                return obj;
            }
        };

        private boolean isSimple;
        private boolean isArray;

        private AddressesData() {
            this.isSimple = true;
            this.isArray = false;
        }
        private AddressesData(boolean isSimple) {
            this(isSimple, false);
        }
        private AddressesData(boolean isSimple, boolean isArray) {
            this.isSimple = isSimple;
            this.isArray = isArray;
        }

        public abstract Object get(Addresses obj);

        public abstract Addresses set(Addresses obj, Object value);

        public boolean isIsSimple() {
            return isSimple;
        }
        public boolean isIsArray() {
            return isArray;
        }
    }

//Some error in definitly structure of that file
//CheckDataTypes.java

/**
 * This code generated from .java file
 * CheckRequest.java
 * ------------------------------------------------------
 * <p>Enum class for complex @Xml type.
 * 
 * @XmlRootElement(name = "CheckRequest")
 * @XmlAccessorType(name = "FIELD")
 * @rapam	Filial		typeOf(String)
 * @rapam	Currency		typeOf(String)
 * @rapam	Statuses		typeOf(Statuses)
 * @rapam	NameRu		typeOf(String)
 * @rapam	IINBIN		typeOf(String)
 * @rapam	OrgForm		typeOf(String)
 * @rapam	FormOfLaw		typeOf(String)
 * @rapam	CommerceOrg		typeOf(String)
 * @rapam	TypicalCharter		typeOf(String)
 * @rapam	Ownership		typeOf(String)
 * @rapam	EnterpriseSubj		typeOf(String)
 * @rapam	PrivateEnterpriseType		typeOf(String)
 * @rapam	ForeignInvest		typeOf(String)
 * @rapam	IsRezident		typeOf(String)
 * @rapam	IncCountry		typeOf(String)
 * @rapam	RegDate		typeOf(String)
 * @rapam	HeadOrgBin		typeOf(String)
 * @rapam	IncomeSources		typeOf(String)
 * @rapam	Founders		typeOf(Founders)
 * @rapam	TypeDoerAgency		typeOf(String)
 * @rapam	DoerAgency		typeOf(String)
 * @rapam	NameAgency		typeOf(String)
 * @rapam	Beneficiary		typeOf(String)
 * @rapam	SignPerson		typeOf(String)
 * @rapam	Persons		typeOf(Persons)
 * @rapam	Organizations		typeOf(Organizations)
 * @rapam	LeaderPersons		typeOf(LeaderPersons)
 * @rapam	Addresses		typeOf(Addresses)
 * 
 * Generated from 'MyEnumGenerator'
 */
    public enum CheckRequestData {
        Filial {
            public Object get(CheckRequest obj) {
                return obj.getFilial();
            }
            public CheckRequest set(CheckRequest obj, Object value) {
                obj.setFilial(String.valueOf(value));
                return obj;
            }
        },
        Currency {
            public Object get(CheckRequest obj) {
                return obj.getCurrency();
            }
            public CheckRequest set(CheckRequest obj, Object value) {
                obj.setCurrency(String.valueOf(value));
                return obj;
            }
        },
        Statuses (false, true) {
            public Object get(CheckRequest obj) {
                return obj.getStatuses();
            }
            public CheckRequest set(CheckRequest obj, Object value) {
                obj.setStatuses((Statuses)value);
                return obj;
            }
        },
        NameRu {
            public Object get(CheckRequest obj) {
                return obj.getNameRu();
            }
            public CheckRequest set(CheckRequest obj, Object value) {
                obj.setNameRu(String.valueOf(value));
                return obj;
            }
        },
        IINBIN {
            public Object get(CheckRequest obj) {
                return obj.getIINBIN();
            }
            public CheckRequest set(CheckRequest obj, Object value) {
                obj.setIINBIN(String.valueOf(value));
                return obj;
            }
        },
        OrgForm {
            public Object get(CheckRequest obj) {
                return obj.getOrgForm();
            }
            public CheckRequest set(CheckRequest obj, Object value) {
                obj.setOrgForm(String.valueOf(value));
                return obj;
            }
        },
        FormOfLaw {
            public Object get(CheckRequest obj) {
                return obj.getFormOfLaw();
            }
            public CheckRequest set(CheckRequest obj, Object value) {
                obj.setFormOfLaw(String.valueOf(value));
                return obj;
            }
        },
        CommerceOrg {
            public Object get(CheckRequest obj) {
                return obj.getCommerceOrg();
            }
            public CheckRequest set(CheckRequest obj, Object value) {
                obj.setCommerceOrg(String.valueOf(value));
                return obj;
            }
        },
        TypicalCharter {
            public Object get(CheckRequest obj) {
                return obj.getTypicalCharter();
            }
            public CheckRequest set(CheckRequest obj, Object value) {
                obj.setTypicalCharter(String.valueOf(value));
                return obj;
            }
        },
        Ownership {
            public Object get(CheckRequest obj) {
                return obj.getOwnership();
            }
            public CheckRequest set(CheckRequest obj, Object value) {
                obj.setOwnership(String.valueOf(value));
                return obj;
            }
        },
        EnterpriseSubj {
            public Object get(CheckRequest obj) {
                return obj.getEnterpriseSubj();
            }
            public CheckRequest set(CheckRequest obj, Object value) {
                obj.setEnterpriseSubj(String.valueOf(value));
                return obj;
            }
        },
        PrivateEnterpriseType {
            public Object get(CheckRequest obj) {
                return obj.getPrivateEnterpriseType();
            }
            public CheckRequest set(CheckRequest obj, Object value) {
                obj.setPrivateEnterpriseType(String.valueOf(value));
                return obj;
            }
        },
        ForeignInvest {
            public Object get(CheckRequest obj) {
                return obj.getForeignInvest();
            }
            public CheckRequest set(CheckRequest obj, Object value) {
                obj.setForeignInvest(String.valueOf(value));
                return obj;
            }
        },
        IsRezident {
            public Object get(CheckRequest obj) {
                return obj.getIsRezident();
            }
            public CheckRequest set(CheckRequest obj, Object value) {
                obj.setIsRezident(String.valueOf(value));
                return obj;
            }
        },
        IncCountry {
            public Object get(CheckRequest obj) {
                return obj.getIncCountry();
            }
            public CheckRequest set(CheckRequest obj, Object value) {
                obj.setIncCountry(String.valueOf(value));
                return obj;
            }
        },
        RegDate {
            public Object get(CheckRequest obj) {
                return obj.getRegDate();
            }
            public CheckRequest set(CheckRequest obj, Object value) {
                obj.setRegDate(String.valueOf(value));
                return obj;
            }
        },
        HeadOrgBin {
            public Object get(CheckRequest obj) {
                return obj.getHeadOrgBin();
            }
            public CheckRequest set(CheckRequest obj, Object value) {
                obj.setHeadOrgBin(String.valueOf(value));
                return obj;
            }
        },
        IncomeSources {
            public Object get(CheckRequest obj) {
                return obj.getIncomeSources();
            }
            public CheckRequest set(CheckRequest obj, Object value) {
                obj.setIncomeSources(String.valueOf(value));
                return obj;
            }
        },
        Founders (false, true) {
            public Object get(CheckRequest obj) {
                return obj.getFounders();
            }
            public CheckRequest set(CheckRequest obj, Object value) {
                obj.setFounders((Founders)value);
                return obj;
            }
        },
        TypeDoerAgency {
            public Object get(CheckRequest obj) {
                return obj.getTypeDoerAgency();
            }
            public CheckRequest set(CheckRequest obj, Object value) {
                obj.setTypeDoerAgency(String.valueOf(value));
                return obj;
            }
        },
        DoerAgency {
            public Object get(CheckRequest obj) {
                return obj.getDoerAgency();
            }
            public CheckRequest set(CheckRequest obj, Object value) {
                obj.setDoerAgency(String.valueOf(value));
                return obj;
            }
        },
        NameAgency {
            public Object get(CheckRequest obj) {
                return obj.getNameAgency();
            }
            public CheckRequest set(CheckRequest obj, Object value) {
                obj.setNameAgency(String.valueOf(value));
                return obj;
            }
        },
        Beneficiary {
            public Object get(CheckRequest obj) {
                return obj.getBeneficiary();
            }
            public CheckRequest set(CheckRequest obj, Object value) {
                obj.setBeneficiary(String.valueOf(value));
                return obj;
            }
        },
        SignPerson {
            public Object get(CheckRequest obj) {
                return obj.getSignPerson();
            }
            public CheckRequest set(CheckRequest obj, Object value) {
                obj.setSignPerson(String.valueOf(value));
                return obj;
            }
        },
        Persons (false, true) {
            public Object get(CheckRequest obj) {
                return obj.getPersons();
            }
            public CheckRequest set(CheckRequest obj, Object value) {
                obj.setPersons((Persons)value);
                return obj;
            }
        },
        Organizations (false, true) {
            public Object get(CheckRequest obj) {
                return obj.getOrganizations();
            }
            public CheckRequest set(CheckRequest obj, Object value) {
                obj.setOrganizations((Organizations)value);
                return obj;
            }
        },
        LeaderPersons (false, true) {
            public Object get(CheckRequest obj) {
                return obj.getLeaderPersons();
            }
            public CheckRequest set(CheckRequest obj, Object value) {
                obj.setLeaderPersons((LeaderPersons)value);
                return obj;
            }
        },
        Addresses (false, true) {
            public Object get(CheckRequest obj) {
                return obj.getAddresses();
            }
            public CheckRequest set(CheckRequest obj, Object value) {
                obj.setAddresses((Addresses)value);
                return obj;
            }
        };

        private boolean isSimple;
        private boolean isArray;

        private CheckRequestData() {
            this.isSimple = true;
            this.isArray = false;
        }
        private CheckRequestData(boolean isSimple) {
            this(isSimple, false);
        }
        private CheckRequestData(boolean isSimple, boolean isArray) {
            this.isSimple = isSimple;
            this.isArray = isArray;
        }

        public abstract Object get(CheckRequest obj);

        public abstract CheckRequest set(CheckRequest obj, Object value);

        public boolean isIsSimple() {
            return isSimple;
        }
        public boolean isIsArray() {
            return isArray;
        }
    }


/**
 * This code generated from .java file
 * CheckResponse.java
 * ------------------------------------------------------
 * <p>Enum class for complex @Xml type.
 * 
 * @XmlRootElement(name = "CheckResponse")
 * @XmlAccessorType(name = "FIELD")
 * @rapam	pepID		typeOf(String)
 * @rapam	DataResponse		typeOf(DataResponse)
 * 
 * Generated from 'MyEnumGenerator'
 */
    public enum CheckResponseData {
        pepID {
            public Object get(CheckResponse obj) {
                return obj.getPepID();
            }
            public CheckResponse set(CheckResponse obj, Object value) {
                obj.setPepID(String.valueOf(value));
                return obj;
            }
        },
        DataResponse (false) {
            public Object get(CheckResponse obj) {
                return obj.getDataResponse();
            }
            public CheckResponse set(CheckResponse obj, Object value) {
                obj.setDataResponse((DataResponse)value);
                return obj;
            }
        };

        private boolean isSimple;
        private boolean isArray;

        private CheckResponseData() {
            this.isSimple = true;
            this.isArray = false;
        }
        private CheckResponseData(boolean isSimple) {
            this(isSimple, false);
        }
        private CheckResponseData(boolean isSimple, boolean isArray) {
            this.isSimple = isSimple;
            this.isArray = isArray;
        }

        public abstract Object get(CheckResponse obj);

        public abstract CheckResponse set(CheckResponse obj, Object value);

        public boolean isIsSimple() {
            return isSimple;
        }
        public boolean isIsArray() {
            return isArray;
        }
    }


/**
 * This code generated from .java file
 * DataResponse.java
 * ------------------------------------------------------
 * <p>Enum class for complex @Xml type.
 * 
 * @XmlType(name = "DataResponse")
 * @XmlAccessorType(name = "FIELD")
 * @rapam	StatusResponse		typeOf(boolean)
 * @rapam	Reason		typeOf(String)
 * @rapam	OrderAmound		typeOf(String)
 * @rapam	NumContract		typeOf(date)
 * 
 * Generated from 'MyEnumGenerator'
 */
    public enum DataResponseData {
        StatusResponse {
            public Object get(DataResponse obj) {
                return obj.isStatusResponse();
            }
            public DataResponse set(DataResponse obj, Object value) {
                obj.setStatusResponse(Boolean.valueOf(value.toString()));
                return obj;
            }
        },
        Reason {
            public Object get(DataResponse obj) {
                return obj.getReason();
            }
            public DataResponse set(DataResponse obj, Object value) {
                obj.setReason(String.valueOf(value));
                return obj;
            }
        },
        OrderAmound {
            public Object get(DataResponse obj) {
                return obj.getOrderAmound();
            }
            public DataResponse set(DataResponse obj, Object value) {
                obj.setOrderAmound(String.valueOf(value));
                return obj;
            }
        },
        NumContract {
            public Object get(DataResponse obj) {
                return obj.getNumContract();
            }
            public DataResponse set(DataResponse obj, Object value) {
                obj.setNumContract((XMLGregorianCalendar)b(value.toString()));
                return obj;
            }
        };

        private boolean isSimple;
        private boolean isArray;

        private DataResponseData() {
            this.isSimple = true;
            this.isArray = false;
        }
        private DataResponseData(boolean isSimple) {
            this(isSimple, false);
        }
        private DataResponseData(boolean isSimple, boolean isArray) {
            this.isSimple = isSimple;
            this.isArray = isArray;
        }

        public abstract Object get(DataResponse obj);

        public abstract DataResponse set(DataResponse obj, Object value);

        public boolean isIsSimple() {
            return isSimple;
        }
        public boolean isIsArray() {
            return isArray;
        }
    }


/**
 * This code generated from .java file
 * Document.java
 * ------------------------------------------------------
 * <p>Enum class for complex @Xml type.
 * 
 * @XmlType(name = "Document")
 * @XmlAccessorType(name = "FIELD")
 * @rapam	DocumentType		typeOf(String)
 * @rapam	DocumentNumber		typeOf(String)
 * @rapam	IssuerId		typeOf(String)
 * @rapam	IssueDate		typeOf(date)
 * 
 * Generated from 'MyEnumGenerator'
 */
    public enum DocumentData {
        DocumentType {
            public Object get(Document obj) {
                return obj.getDocumentType();
            }
            public Document set(Document obj, Object value) {
                obj.setDocumentType(String.valueOf(value));
                return obj;
            }
        },
        DocumentNumber {
            public Object get(Document obj) {
                return obj.getDocumentNumber();
            }
            public Document set(Document obj, Object value) {
                obj.setDocumentNumber(String.valueOf(value));
                return obj;
            }
        },
        IssuerId {
            public Object get(Document obj) {
                return obj.getIssuerId();
            }
            public Document set(Document obj, Object value) {
                obj.setIssuerId(String.valueOf(value));
                return obj;
            }
        },
        IssueDate {
            public Object get(Document obj) {
                return obj.getIssueDate();
            }
            public Document set(Document obj, Object value) {
                obj.setIssueDate((XMLGregorianCalendar)b(value.toString()));
                return obj;
            }
        };

        private boolean isSimple;
        private boolean isArray;

        private DocumentData() {
            this.isSimple = true;
            this.isArray = false;
        }
        private DocumentData(boolean isSimple) {
            this(isSimple, false);
        }
        private DocumentData(boolean isSimple, boolean isArray) {
            this.isSimple = isSimple;
            this.isArray = isArray;
        }

        public abstract Object get(Document obj);

        public abstract Document set(Document obj, Object value);

        public boolean isIsSimple() {
            return isSimple;
        }
        public boolean isIsArray() {
            return isArray;
        }
    }


/**
 * This code generated from .java file
 * FIO.java
 * ------------------------------------------------------
 * <p>Enum class for complex @Xml type.
 * 
 * @XmlType(name = "FIO")
 * @XmlAccessorType(name = "FIELD")
 * @rapam	SurName		typeOf(String)
 * @rapam	Name		typeOf(String)
 * @rapam	MiddleName		typeOf(String)
 * 
 * Generated from 'MyEnumGenerator'
 */
    public enum FIOData {
        SurName {
            public Object get(FIO obj) {
                return obj.getSurName();
            }
            public FIO set(FIO obj, Object value) {
                obj.setSurName(String.valueOf(value));
                return obj;
            }
        },
        Name {
            public Object get(FIO obj) {
                return obj.getName();
            }
            public FIO set(FIO obj, Object value) {
                obj.setName(String.valueOf(value));
                return obj;
            }
        },
        MiddleName {
            public Object get(FIO obj) {
                return obj.getMiddleName();
            }
            public FIO set(FIO obj, Object value) {
                obj.setMiddleName(String.valueOf(value));
                return obj;
            }
        };

        private boolean isSimple;
        private boolean isArray;

        private FIOData() {
            this.isSimple = true;
            this.isArray = false;
        }
        private FIOData(boolean isSimple) {
            this(isSimple, false);
        }
        private FIOData(boolean isSimple, boolean isArray) {
            this.isSimple = isSimple;
            this.isArray = isArray;
        }

        public abstract Object get(FIO obj);

        public abstract FIO set(FIO obj, Object value);

        public boolean isIsSimple() {
            return isSimple;
        }
        public boolean isIsArray() {
            return isArray;
        }
    }


/**
 * This code generated from .java file
 * Founder.java
 * ------------------------------------------------------
 * <p>Enum class for complex @Xml type.
 * 
 * @XmlType(name = "Founder")
 * @XmlAccessorType(name = "FIELD")
 * @rapam	IINBIN		typeOf(String)
 * @rapam	FounderFIO		typeOf(String)
 * @rapam	FounderPercent		typeOf(String)
 * @rapam	FounderBurnDate		typeOf(date)
 * @rapam	FounderBurnPlace		typeOf(String)
 * @rapam	Address		typeOf(Address)
 * @rapam	Legal		typeOf(boolean)
 * 
 * Generated from 'MyEnumGenerator'
 */
    public enum FounderData {
        IINBIN {
            public Object get(Founder obj) {
                return obj.getIINBIN();
            }
            public Founder set(Founder obj, Object value) {
                obj.setIINBIN(String.valueOf(value));
                return obj;
            }
        },
        FounderFIO {
            public Object get(Founder obj) {
                return obj.getFounderFIO();
            }
            public Founder set(Founder obj, Object value) {
                obj.setFounderFIO(String.valueOf(value));
                return obj;
            }
        },
        FounderPercent {
            public Object get(Founder obj) {
                return obj.getFounderPercent();
            }
            public Founder set(Founder obj, Object value) {
                obj.setFounderPercent(String.valueOf(value));
                return obj;
            }
        },
        FounderBurnDate {
            public Object get(Founder obj) {
                return obj.getFounderBurnDate();
            }
            public Founder set(Founder obj, Object value) {
                obj.setFounderBurnDate((XMLGregorianCalendar)b(value.toString()));
                return obj;
            }
        },
        FounderBurnPlace {
            public Object get(Founder obj) {
                return obj.getFounderBurnPlace();
            }
            public Founder set(Founder obj, Object value) {
                obj.setFounderBurnPlace(String.valueOf(value));
                return obj;
            }
        },
        Address (false) {
            public Object get(Founder obj) {
                return obj.getAddress();
            }
            public Founder set(Founder obj, Object value) {
                obj.setAddress((Address)value);
                return obj;
            }
        },
        Legal {
            public Object get(Founder obj) {
                return obj.isLegal();
            }
            public Founder set(Founder obj, Object value) {
                obj.setLegal(Boolean.valueOf(value.toString()));
                return obj;
            }
        };

        private boolean isSimple;
        private boolean isArray;

        private FounderData() {
            this.isSimple = true;
            this.isArray = false;
        }
        private FounderData(boolean isSimple) {
            this(isSimple, false);
        }
        private FounderData(boolean isSimple, boolean isArray) {
            this.isSimple = isSimple;
            this.isArray = isArray;
        }

        public abstract Object get(Founder obj);

        public abstract Founder set(Founder obj, Object value);

        public boolean isIsSimple() {
            return isSimple;
        }
        public boolean isIsArray() {
            return isArray;
        }
    }


/**
 * This code generated from .java file
 * Founders.java
 * ------------------------------------------------------
 * <p>Enum class for complex @Xml type.
 * 
 * @XmlType(name = "Founders")
 * @XmlAccessorType(name = "FIELD")
 * @rapam	Founder		typeOf(List<Founder>)
 * 
 * Generated from 'MyEnumGenerator'
 */
    public enum FoundersData {
        Founder (false) {
            public Object get(Founders obj) {
                return obj.getFounder();
            }
            public Founders set(Founders obj, Object value) {
                obj.getFounder().add((Founder)value);
                return obj;
            }
        };

        private boolean isSimple;
        private boolean isArray;

        private FoundersData() {
            this.isSimple = true;
            this.isArray = false;
        }
        private FoundersData(boolean isSimple) {
            this(isSimple, false);
        }
        private FoundersData(boolean isSimple, boolean isArray) {
            this.isSimple = isSimple;
            this.isArray = isArray;
        }

        public abstract Object get(Founders obj);

        public abstract Founders set(Founders obj, Object value);

        public boolean isIsSimple() {
            return isSimple;
        }
        public boolean isIsArray() {
            return isArray;
        }
    }


/**
 * This code generated from .java file
 * LeaderPerson.java
 * ------------------------------------------------------
 * <p>Enum class for complex @Xml type.
 * 
 * @XmlType(name = "LeaderPerson")
 * @XmlAccessorType(name = "FIELD")
 * @rapam	FIO		typeOf(FIO)
 * @rapam	Document		typeOf(Document)
 * @rapam	IIN		typeOf(String)
 * 
 * Generated from 'MyEnumGenerator'
 */
    public enum LeaderPersonData {
        FIO (false) {
            public Object get(LeaderPerson obj) {
                return obj.getFIO();
            }
            public LeaderPerson set(LeaderPerson obj, Object value) {
                obj.setFIO((FIO)value);
                return obj;
            }
        },
        Document (false) {
            public Object get(LeaderPerson obj) {
                return obj.getDocument();
            }
            public LeaderPerson set(LeaderPerson obj, Object value) {
                obj.setDocument((Document)value);
                return obj;
            }
        },
        IIN {
            public Object get(LeaderPerson obj) {
                return obj.getIIN();
            }
            public LeaderPerson set(LeaderPerson obj, Object value) {
                obj.setIIN(String.valueOf(value));
                return obj;
            }
        };

        private boolean isSimple;
        private boolean isArray;

        private LeaderPersonData() {
            this.isSimple = true;
            this.isArray = false;
        }
        private LeaderPersonData(boolean isSimple) {
            this(isSimple, false);
        }
        private LeaderPersonData(boolean isSimple, boolean isArray) {
            this.isSimple = isSimple;
            this.isArray = isArray;
        }

        public abstract Object get(LeaderPerson obj);

        public abstract LeaderPerson set(LeaderPerson obj, Object value);

        public boolean isIsSimple() {
            return isSimple;
        }
        public boolean isIsArray() {
            return isArray;
        }
    }


/**
 * This code generated from .java file
 * LeaderPersons.java
 * ------------------------------------------------------
 * <p>Enum class for complex @Xml type.
 * 
 * @XmlType(name = "LeaderPersons")
 * @XmlAccessorType(name = "FIELD")
 * @rapam	LeaderPerson		typeOf(List<LeaderPerson>)
 * 
 * Generated from 'MyEnumGenerator'
 */
    public enum LeaderPersonsData {
        LeaderPerson (false) {
            public Object get(LeaderPersons obj) {
                return obj.getLeaderPerson();
            }
            public LeaderPersons set(LeaderPersons obj, Object value) {
                obj.getLeaderPerson().add((LeaderPerson)value);
                return obj;
            }
        };

        private boolean isSimple;
        private boolean isArray;

        private LeaderPersonsData() {
            this.isSimple = true;
            this.isArray = false;
        }
        private LeaderPersonsData(boolean isSimple) {
            this(isSimple, false);
        }
        private LeaderPersonsData(boolean isSimple, boolean isArray) {
            this.isSimple = isSimple;
            this.isArray = isArray;
        }

        public abstract Object get(LeaderPersons obj);

        public abstract LeaderPersons set(LeaderPersons obj, Object value);

        public boolean isIsSimple() {
            return isSimple;
        }
        public boolean isIsArray() {
            return isArray;
        }
    }


/**
 * This code generated from .java file
 * Organization.java
 * ------------------------------------------------------
 * <p>Enum class for complex @Xml type.
 * 
 * @XmlType(name = "Organization")
 * @XmlAccessorType(name = "FIELD")
 * @rapam	OrganizationNameRu		typeOf(String)
 * @rapam	BIN		typeOf(String)
 * @rapam	RegisteringDepartment		typeOf(String)
 * @rapam	OKED		typeOf(String)
 * 
 * Generated from 'MyEnumGenerator'
 */
    public enum OrganizationData {
        OrganizationNameRu {
            public Object get(Organization obj) {
                return obj.getOrganizationNameRu();
            }
            public Organization set(Organization obj, Object value) {
                obj.setOrganizationNameRu(String.valueOf(value));
                return obj;
            }
        },
        BIN {
            public Object get(Organization obj) {
                return obj.getBIN();
            }
            public Organization set(Organization obj, Object value) {
                obj.setBIN(String.valueOf(value));
                return obj;
            }
        },
        RegisteringDepartment {
            public Object get(Organization obj) {
                return obj.getRegisteringDepartment();
            }
            public Organization set(Organization obj, Object value) {
                obj.setRegisteringDepartment(String.valueOf(value));
                return obj;
            }
        },
        OKED {
            public Object get(Organization obj) {
                return obj.getOKED();
            }
            public Organization set(Organization obj, Object value) {
                obj.setOKED(String.valueOf(value));
                return obj;
            }
        };

        private boolean isSimple;
        private boolean isArray;

        private OrganizationData() {
            this.isSimple = true;
            this.isArray = false;
        }
        private OrganizationData(boolean isSimple) {
            this(isSimple, false);
        }
        private OrganizationData(boolean isSimple, boolean isArray) {
            this.isSimple = isSimple;
            this.isArray = isArray;
        }

        public abstract Object get(Organization obj);

        public abstract Organization set(Organization obj, Object value);

        public boolean isIsSimple() {
            return isSimple;
        }
        public boolean isIsArray() {
            return isArray;
        }
    }


/**
 * This code generated from .java file
 * Organizations.java
 * ------------------------------------------------------
 * <p>Enum class for complex @Xml type.
 * 
 * @XmlType(name = "Organizations")
 * @XmlAccessorType(name = "FIELD")
 * @rapam	Organization		typeOf(List<Organization>)
 * 
 * Generated from 'MyEnumGenerator'
 */
    public enum OrganizationsData {
        Organization (false) {
            public Object get(Organizations obj) {
                return obj.getOrganization();
            }
            public Organizations set(Organizations obj, Object value) {
                obj.getOrganization().add((Organization)value);
                return obj;
            }
        };

        private boolean isSimple;
        private boolean isArray;

        private OrganizationsData() {
            this.isSimple = true;
            this.isArray = false;
        }
        private OrganizationsData(boolean isSimple) {
            this(isSimple, false);
        }
        private OrganizationsData(boolean isSimple, boolean isArray) {
            this.isSimple = isSimple;
            this.isArray = isArray;
        }

        public abstract Object get(Organizations obj);

        public abstract Organizations set(Organizations obj, Object value);

        public boolean isIsSimple() {
            return isSimple;
        }
        public boolean isIsArray() {
            return isArray;
        }
    }


/**
 * This code generated from .java file
 * Person.java
 * ------------------------------------------------------
 * <p>Enum class for complex @Xml type.
 * 
 * @XmlType(name = "Person")
 * @XmlAccessorType(name = "FIELD")
 * @rapam	FIO		typeOf(FIO)
 * @rapam	Document		typeOf(Document)
 * @rapam	IIN		typeOf(String)
 * @rapam	Gender		typeOf(String)
 * @rapam	National		typeOf(String)
 * @rapam	Citizenship		typeOf(String)
 * @rapam	BurnDate		typeOf(date)
 * @rapam	Address		typeOf(Address)
 * 
 * Generated from 'MyEnumGenerator'
 */
    public enum PersonData {
        FIO (false) {
            public Object get(Person obj) {
                return obj.getFIO();
            }
            public Person set(Person obj, Object value) {
                obj.setFIO((FIO)value);
                return obj;
            }
        },
        Document (false) {
            public Object get(Person obj) {
                return obj.getDocument();
            }
            public Person set(Person obj, Object value) {
                obj.setDocument((Document)value);
                return obj;
            }
        },
        IIN {
            public Object get(Person obj) {
                return obj.getIIN();
            }
            public Person set(Person obj, Object value) {
                obj.setIIN(String.valueOf(value));
                return obj;
            }
        },
        Gender {
            public Object get(Person obj) {
                return obj.getGender();
            }
            public Person set(Person obj, Object value) {
                obj.setGender(String.valueOf(value));
                return obj;
            }
        },
        National {
            public Object get(Person obj) {
                return obj.getNational();
            }
            public Person set(Person obj, Object value) {
                obj.setNational(String.valueOf(value));
                return obj;
            }
        },
        Citizenship {
            public Object get(Person obj) {
                return obj.getCitizenship();
            }
            public Person set(Person obj, Object value) {
                obj.setCitizenship(String.valueOf(value));
                return obj;
            }
        },
        BurnDate {
            public Object get(Person obj) {
                return obj.getBurnDate();
            }
            public Person set(Person obj, Object value) {
                obj.setBurnDate((XMLGregorianCalendar)b(value.toString()));
                return obj;
            }
        },
        Address (false) {
            public Object get(Person obj) {
                return obj.getAddress();
            }
            public Person set(Person obj, Object value) {
                obj.setAddress((Address)value);
                return obj;
            }
        };

        private boolean isSimple;
        private boolean isArray;

        private PersonData() {
            this.isSimple = true;
            this.isArray = false;
        }
        private PersonData(boolean isSimple) {
            this(isSimple, false);
        }
        private PersonData(boolean isSimple, boolean isArray) {
            this.isSimple = isSimple;
            this.isArray = isArray;
        }

        public abstract Object get(Person obj);

        public abstract Person set(Person obj, Object value);

        public boolean isIsSimple() {
            return isSimple;
        }
        public boolean isIsArray() {
            return isArray;
        }
    }


/**
 * This code generated from .java file
 * Persons.java
 * ------------------------------------------------------
 * <p>Enum class for complex @Xml type.
 * 
 * @XmlType(name = "Persons")
 * @XmlAccessorType(name = "FIELD")
 * @rapam	Person		typeOf(List<Person>)
 * 
 * Generated from 'MyEnumGenerator'
 */
    public enum PersonsData {
        Person (false) {
            public Object get(Persons obj) {
                return obj.getPerson();
            }
            public Persons set(Persons obj, Object value) {
                obj.getPerson().add((Person)value);
                return obj;
            }
        };

        private boolean isSimple;
        private boolean isArray;

        private PersonsData() {
            this.isSimple = true;
            this.isArray = false;
        }
        private PersonsData(boolean isSimple) {
            this(isSimple, false);
        }
        private PersonsData(boolean isSimple, boolean isArray) {
            this.isSimple = isSimple;
            this.isArray = isArray;
        }

        public abstract Object get(Persons obj);

        public abstract Persons set(Persons obj, Object value);

        public boolean isIsSimple() {
            return isSimple;
        }
        public boolean isIsArray() {
            return isArray;
        }
    }


/**
 * This code generated from .java file
 * Status.java
 * ------------------------------------------------------
 * <p>Enum class for complex @Xml type.
 * 
 * @XmlType(name = "Status")
 * @XmlAccessorType(name = "FIELD")
 * @rapam	Code		typeOf(String)
 * 
 * Generated from 'MyEnumGenerator'
 */
    public enum StatusData {
        Code {
            public Object get(Status obj) {
                return obj.getCode();
            }
            public Status set(Status obj, Object value) {
                obj.setCode(String.valueOf(value));
                return obj;
            }
        };

        private boolean isSimple;
        private boolean isArray;

        private StatusData() {
            this.isSimple = true;
            this.isArray = false;
        }
        private StatusData(boolean isSimple) {
            this(isSimple, false);
        }
        private StatusData(boolean isSimple, boolean isArray) {
            this.isSimple = isSimple;
            this.isArray = isArray;
        }

        public abstract Object get(Status obj);

        public abstract Status set(Status obj, Object value);

        public boolean isIsSimple() {
            return isSimple;
        }
        public boolean isIsArray() {
            return isArray;
        }
    }


/**
 * This code generated from .java file
 * Statuses.java
 * ------------------------------------------------------
 * <p>Enum class for complex @Xml type.
 * 
 * @XmlType(name = "Statuses")
 * @XmlAccessorType(name = "FIELD")
 * @rapam	Status		typeOf(List<Status>)
 * 
 * Generated from 'MyEnumGenerator'
 */
    public enum StatusesData {
        Status (false) {
            public Object get(Statuses obj) {
                return obj.getStatus();
            }
            public Statuses set(Statuses obj, Object value) {
                obj.getStatus().add((Status)value);
                return obj;
            }
        };

        private boolean isSimple;
        private boolean isArray;

        private StatusesData() {
            this.isSimple = true;
            this.isArray = false;
        }
        private StatusesData(boolean isSimple) {
            this(isSimple, false);
        }
        private StatusesData(boolean isSimple, boolean isArray) {
            this.isSimple = isSimple;
            this.isArray = isArray;
        }

        public abstract Object get(Statuses obj);

        public abstract Statuses set(Statuses obj, Object value);

        public boolean isIsSimple() {
            return isSimple;
        }
        public boolean isIsArray() {
            return isArray;
        }
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