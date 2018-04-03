package com.parsing.XsdToString;

import com.parsing.XsdToString.CheckDataTypes.Address;
import com.parsing.XsdToString.CheckDataTypes.Addresses;
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
import com.parsing.XsdToString.CheckDataTypes.Statuses;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * 
 * 
 */
public class DataStruct_2 {

    public enum RequestRelatives {
        //Name,               //("Name",                    true),
        //Surname,            //("Surname",                 true),
        //Patronymic,         //("Patronymic",              true),
        BirthDate, //("BirthDate",               true,   "BirthDate",        "48.Дата рождения"),
        //Age,                //("Age",                     true),
        TypeRelative, //("RelationDegree",          true,   "TypeRelative",     "49.Степень родства"),
        Member, //("FamilyMember",            true,   "Member",           "50.Член семьи"),
        Dependant, //("Dependent",               true,   "Dependant",        "51.Иждевенец"),
        FIO, //("Individual",              true,   "FIO",              "47.ФИО");

        unknown;
        @SuppressWarnings("compatibility:5506296546758768983")
        private static final long serialVersionUID = 2L;
    }


    /** Enum структура для ViewObject CheckRequest,
     *  формат заполнения: attrName, isSimple
     * */
    public enum CheckRequestEnum {
        // nameAttr
        Bin {
            public Object get(CheckRequest obj) {
                return obj.getIINBIN();
            }

            public CheckRequest set(CheckRequest obj, Object value) {
                obj.setIINBIN(String.valueOf(value));
                return obj;
            }
        },
        BankFilial {
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
        StatusCode(false) {
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
        IsResident {
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
        Founders(false) {
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
        Persons(false) {
            public Object get(CheckRequest obj) {
                return obj.getPersons();
            }

            public CheckRequest set(CheckRequest obj, Object value) {
                obj.setPersons((Persons)value);
                return obj;
            }
        },
        Organizations(false) {
            public Object get(CheckRequest obj) {
                return obj.getOrganizations();
            }

            public CheckRequest set(CheckRequest obj, Object value) {
                obj.setOrganizations((Organizations)value);
                return obj;
            }
        },
        LeaderPersons(false) {
            public Object get(CheckRequest obj) {
                return obj.getLeaderPersons();
            }

            public CheckRequest set(CheckRequest obj, Object value) {
                obj.setLeaderPersons((LeaderPersons)value);
                return obj;
            }
        },
        Addresses(false) {
            public Object get(CheckRequest obj) {
                return obj.getAddresses();
            }

            public CheckRequest set(CheckRequest obj, Object value) {
                obj.setAddresses((Addresses)value);
                return obj;
            }
        };

        @SuppressWarnings("compatibility:-3210332540546813677")
        private static final long serialVersionUID = 1L;

        private boolean isSimple;

        private CheckRequestEnum() {
            this.isSimple = true;
        }

        private CheckRequestEnum(boolean isSimple) {
            this.isSimple = isSimple;
        }

        public abstract Object get(CheckRequest obj);

        public abstract CheckRequest set(CheckRequest obj, Object value);

        public boolean isIsSimple() {
            return isSimple;
        }
    }

    /** Enum структура для ViewObject MainData,
     *  формат заполнения: attrName, isSimple
     * */
    public enum MainData {
        // nameAttr
        EccRelativesId,
        InstanceId,
        Name;

        @SuppressWarnings("compatibility:-4714401462646517766")
        private static final long serialVersionUID = 1L;

    }

    /** Enum структура для ViewObject Founders,
     *  формат заполнения: attrName, isSimple
     * */
    public enum FounderData {
        // nameAttr
        IINBIN {
            public Object get(Founder obj) {
                return obj.getIINBIN();
            }

            public void set(Founder obj, Object value) {
                obj.setIINBIN(value.toString());
            }
        },
        FounderFIO {
            public Object get(Founder obj) {
                return obj.getFounderFIO();
            }

            public void set(Founder obj, Object value) {
                obj.setFounderFIO(value.toString());
            }
        },
        FounderPercent {
            public Object get(Founder obj) {
                return obj.getFounderPercent();
            }

            public void set(Founder obj, Object value) {
                obj.setFounderPercent(value.toString());
            }
        },
        FounderBurnDate {
            public Object get(Founder obj) {
                return obj.getFounderBurnDate();
            }

            public void set(Founder obj, Object value) {
                obj.setFounderBurnDate((XMLGregorianCalendar)value);
            }
        },
        FounderBurnPlace {
            public Object get(Founder obj) {
                return obj.getFounderBurnPlace();
            }

            public void set(Founder obj, Object value) {
                obj.setFounderBurnPlace(value.toString());
            }
        },
        Address(false) {
            public Object get(Founder obj) {
                return obj.getAddress();
            }

            public void set(Founder obj, Object value) {
                obj.setAddress((Address)value);
            }
        },
        Legal {
            public Object get(Founder obj) {
                return obj.isLegal();
            }

            public void set(Founder obj, Object value) {
                obj.setLegal(Boolean.valueOf(value.toString()));
            }
        };

        @SuppressWarnings("compatibility:2809055570542338954")
        private static final long serialVersionUID = 1L;

        private boolean isSimple;

        private FounderData() {
            this.isSimple = true;
        }

        private FounderData(boolean isSimple) {
            this.isSimple = isSimple;
        }

        public abstract Object get(Founder obj);

        public abstract void set(Founder obj, Object value);

        public boolean isIsSimple() {
            return isSimple;
        }
    }

    /** Enum структура для ViewObject Founders,
     *  формат заполнения: attrName, isSimple
     * */
    public enum AddressesData {
        // nameAttr
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
                return obj.getZIPCode();
            }

            public Address set(Address obj, Object value) {
                obj.setZIPCode(String.valueOf(value));
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

        @SuppressWarnings("compatibility:7491783587925370184")
        private static final long serialVersionUID = 1L;

        private boolean isSimple;

        private AddressesData() {
            this.isSimple = true;
        }

        private AddressesData(boolean isSimple) {
            this.isSimple = isSimple;
        }

        public abstract Object get(Address obj);

        public abstract Address set(Address obj, Object value);

        public boolean isIsSimple() {
            return isSimple;
        }
    }

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

        private FIOData() {
            this.isSimple = true;
        }
        private FIOData(boolean isSimple) {
            this.isSimple = isSimple;
        }

        public abstract Object get(FIO obj);

        public abstract FIO set(FIO obj, Object value);

        public boolean isIsSimple() {
            return isSimple;
        }
    }

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

        private OrganizationData() {
            this.isSimple = true;
        }
        private OrganizationData(boolean isSimple) {
            this.isSimple = isSimple;
        }

        public abstract Object get(Organization obj);

        public abstract Organization set(Organization obj, Object value);

        public boolean isIsSimple() {
            return isSimple;
        }
    }

    public enum LeaderPersonData {
        FIO {
            public Object get(LeaderPerson obj) {
                return obj.getFIO();
            }
            public LeaderPerson set(LeaderPerson obj, Object value) {
                obj.setFIO((FIO)value);
                return obj;
            }
        },
        Document {
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

        private LeaderPersonData() {
            this.isSimple = true;
        }
        private LeaderPersonData(boolean isSimple) {
            this.isSimple = isSimple;
        }

        public abstract Object get(LeaderPerson obj);

        public abstract LeaderPerson set(LeaderPerson obj, Object value);

        public boolean isIsSimple() {
            return isSimple;
        }
    }

    public enum PersonData {
        FIO {
            public Object get(Person obj) {
                return obj.getFIO();
            }
            public Person set(Person obj, Object value) {
                obj.setFIO((FIO)value);
                return obj;
            }
        },
        Document {
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
                obj.setBurnDate((XMLGregorianCalendar)value);
                return obj;
            }
        },
        Address {
            public Object get(Person obj) {
                return obj.getAddress();
            }
            public Person set(Person obj, Object value) {
                obj.setAddress((Address)value);
                return obj;
            }
        };

        private boolean isSimple;

        private PersonData() {
            this.isSimple = true;
        }
        private PersonData(boolean isSimple) {
            this.isSimple = isSimple;
        }

        public abstract Object get(Person obj);

        public abstract Person set(Person obj, Object value);

        public boolean isIsSimple() {
            return isSimple;
        }
    }


}
