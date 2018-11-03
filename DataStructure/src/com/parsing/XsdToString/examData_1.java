package com.parsing.XsdToString;

//import com.parsing.XsdToString.CheckDataTypes_1.CheckRequest;
//import com.parsing.XsdToString.CheckDataTypes_1.FIO;

public class examData_1 {


    public examData_1() {
        super();
    }

    /*public enum AvailableTypes {
        STRING {
            public Object getTypeObject(Object obj) {
                return String.valueOf(obj);
            }
        },
        INTEGER {
            public Object getTypeObject(Object obj) {
                return Integer.valueOf(obj.toString());
            }
        },
        fio {
            public Object getTypeObject(Object obj) {
                return (FIO)obj;
            }
        };
        @SuppressWarnings("compatibility:4763989558872881841")
        private static final long serialVersionUID = 1L;


        public abstract Object getTypeObject(Object obj);
    }*/

    /** более полный от генерации класс
     * 
     * */
    /*private enum AttributesEnum {
        Name {
            public Object get(CheckRequest obj) {
                return obj.getName();
            }

            public void set(CheckRequest obj, Object value) {
                obj.setName(value.toString());
            }
        },
        data2 {
            public Object get(CheckRequest obj) {
                return obj.getSurname();
            }

            public void set(CheckRequest obj, Object value) {
                obj.setSurname(value.toString());
            }
        };
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;
        @SuppressWarnings("compatibility:3199595888084806205")
        private static final long serialVersionUID = 1L;

        public abstract Object get(CheckRequest object);

        public abstract void set(CheckRequest object, Object value);
        
        public int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        public static int firstIndex() {
            return firstIndex;
        }

        public static int count() {
            return AttributesEnum.firstIndex() +
                AttributesEnum.staticValues().length;
        }

        public static AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }*/

    /*public enum ExamData {
        // nameAttr                 (name,                  type),
        Name("Name", AvailableTypes.STRING) {
            public Object get(CheckRequest obj) {
                return obj.getName();
            }

            public void set(CheckRequest obj, Object value) {
                obj.setName(value.toString());
            }
        },
        Surname("Surname", AvailableTypes.STRING) {
            public Object get(CheckRequest obj) {
                return obj.getSurname();
            }

            public void set(CheckRequest obj, Object value) {
                obj.setSurname(value.toString());
            }
        },
        MiddleName("MiddleName", AvailableTypes.STRING) {
            public Object get(CheckRequest obj) {
                return obj.getMiddlename();
            }

            public void set(CheckRequest obj, Object value) {
                obj.setMiddlename(value.toString());
            }
        },
        Fio("Fio", AvailableTypes.fio) {
            public Object get(CheckRequest obj) {
                return obj.getAddresses();
            }

            public void set(CheckRequest obj, Object value) {
                obj.setAddresses((FIO)value);
            }
        },;

        @SuppressWarnings("compatibility:-5219402490897982267")
        private static final long serialVersionUID = 1L;

        private final String name;
        //private final Object value;
        private final AvailableTypes type;

        ExamData(String name, AvailableTypes type) {
            this.name = name;
            this.type = type;
        }

        private AvailableTypes getType() {
            return type;
        }

        //private static ExamData[] vals = null;
        //private static int firstIndex = 0;

        public abstract Object get(CheckRequest object);

        public abstract void set(CheckRequest object, Object value);

        /*public int index() {
            return ExamData.firstIndex() + ordinal();
        }

        public static int firstIndex() {
            return firstIndex;
        }

        public static int count() {
            return ExamData.firstIndex() +
                ExamData.staticValues().length;
        }

        public static ExamData[] staticValues() {
            if (vals == null) {
                vals = ExamData.values();
            }
            return vals;
        }/
    }*/

    /*public static final int NAME = ExamData.Name.index();
    public static final int SURNAME = ExamData.Surname.index();
    public static final int MIDDLENAME = ExamData.MiddleName.index();
    public static final int CONTACTS = ExamData.Contacts.index();*/

}
